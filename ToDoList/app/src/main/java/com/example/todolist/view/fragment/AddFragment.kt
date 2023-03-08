package com.example.todolist.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.todolist.databinding.FragmentAddBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.viewmodel.ToDoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class AddFragment: BottomSheetDialogFragment() {
    private val toDoViewModel by viewModels<ToDoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentAddBinding.inflate(inflater)
        val btnOk: Button = view.btnOk
        val btnClose: Button = view.btnClose

        val addTitle: EditText by lazy {
            view.addTitle
        }
        val addContent: EditText by lazy {
            view.addContent
        }

        btnOk.setOnClickListener {
            var id: Long? = null
            val title = addTitle.text.toString()
            val content = addContent.text.toString()
            val createdDate = Date().time
            val toDoModel = ToDoModel(id, title, content, createdDate)
            toDoViewModel.insert(toDoModel)

            Toast.makeText(context, "등록되었습니다.", Toast.LENGTH_SHORT).show()
            dismiss()
            // fragment에 남아있는 값 reset하기
        }
        btnClose.setOnClickListener {
            Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return view.root
    }
}