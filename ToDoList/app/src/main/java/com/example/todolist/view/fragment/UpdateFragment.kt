package com.example.todolist.view.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.viewmodel.ToDoViewModel
import java.util.*

class UpdateFragment: Fragment() {
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var updateBinding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateBinding = FragmentUpdateBinding.inflate(inflater)

        val btnModify: Button = updateBinding.btnModify
        val btnClose: Button = updateBinding.btnClose

        updateBinding.modifyTitle.setText(args.todoItem.title)
        updateBinding.modifyContent.setText(args.todoItem.description)

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

        if(inputCheck(updateTitle, updateContent)) {
            val updateToDo = ToDoModel(args.todoItem.id, updateTitle, updateContent, args.todoItem.createdDate)
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