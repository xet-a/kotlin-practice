package com.example.todolist.view.fragment

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.databinding.FragmentAddBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.view.helper.NotificationHelper
import com.example.todolist.viewmodel.ToDoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddFragment: BottomSheetDialogFragment() {
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private lateinit var notificationHelper: NotificationHelper
    private var _addBinding: FragmentAddBinding? = null
    private val addBinding get() = _addBinding!!
    val cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _addBinding = FragmentAddBinding.inflate(inflater)
        val btnOk: Button = addBinding.btnOk
        val btnClose: Button = addBinding.btnClose
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(pick: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "yy.MM.dd"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                addBinding.addDate.setText(sdf.format(cal.time))
            }
        }

        addBinding.addDate.inputType = InputType.TYPE_NULL
        addBinding.addDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(dateview: View?) {
                val datePickerDialog = DatePickerDialog(context!!, dateSetListener,
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).apply {
                        datePicker.minDate = System.currentTimeMillis()
                }
                datePickerDialog.show()
            }
        })


        notificationHelper = NotificationHelper(activity)

        btnOk.setOnClickListener {
            var id: Int? = null
            val title = addBinding.addTitle.text.toString()
            val content = addBinding.addContent.text.toString()
            val date = addBinding.addDate.text.toString()
            val toDoModel = ToDoModel(id, title, content, date, false)
            toDoViewModel.insert(toDoModel)

            showNotification(title, content)
            Toast.makeText(context, "등록되었습니다.", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        btnClose.setOnClickListener {
            Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return addBinding.root
    }
    private fun showNotification(title: String, message: String) {
        val nb: NotificationCompat.Builder = notificationHelper.getChannelNotification("할 일 추가! [" + title + "]", "내용 : " + message)

        notificationHelper.getManager().notify(1, nb.build())
    }
}