package com.example.todolist.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.viewmodel.ToDoViewModel
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment: Fragment() {
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private val args by navArgs<UpdateFragmentArgs>()
    private var _updateBinding: FragmentUpdateBinding? = null
    private val updateBinding get() = _updateBinding!!
    val cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _updateBinding = FragmentUpdateBinding.inflate(inflater)

        val btnModify: Button = updateBinding.btnModify
        val btnClose: Button = updateBinding.btnClose
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(pick: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "yy.MM.dd"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                updateBinding.modifyDate.setText(sdf.format(cal.time))
            }
        }

        updateBinding.modifyDate.inputType = InputType.TYPE_NULL
        updateBinding.modifyDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(dateview: View?) {
                val datePickerDialog = DatePickerDialog(context!!, dateSetListener,
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).apply {
                    datePicker.minDate = System.currentTimeMillis()
                }
                datePickerDialog.show()
            }
        })

        updateBinding.modifyTitle.setText(args.todoItem.title)
        updateBinding.modifyContent.setText(args.todoItem.description)
        updateBinding.modifyDate.setText(args.todoItem.dueDate)

        btnModify.setOnClickListener {
            updateToDo()
        }
        btnClose.setOnClickListener {
            Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

        return updateBinding.root
    }

    private fun updateToDo(){
        val updateTitle = updateBinding.modifyTitle.text.toString()
        val updateContent = updateBinding.modifyContent.text.toString()
        val updateDate = updateBinding.modifyDate.text.toString()

        if(inputCheck(updateTitle, updateContent)) {
            val updateToDo = ToDoModel(args.todoItem.id, updateTitle, updateContent, updateDate, false)
            toDoViewModel.update(updateToDo)

            Toast.makeText(context, "수정되었습니다.", Toast.LENGTH_SHORT).show()

            findNavController().navigateUp()
        } else {
            Toast.makeText(context, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, content: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}