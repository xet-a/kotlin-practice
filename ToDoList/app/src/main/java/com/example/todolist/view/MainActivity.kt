package com.example.todolist.view

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.DialogAddBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.view.adapter.ToDoAdapter
import com.example.todolist.viewmodel.ToDoViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var dialogBinding: DialogAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        initViewModel()
        initRecyclerView()
        mainBinding.btnAddTodo.setOnClickListener {
            showAddToDoDialog()
        }
    }

    private fun initViewModel() {
        toDoViewModel.getToDoList().observe(this, androidx.lifecycle.Observer {
            toDoAdapter.setTodoItems(it)
        })
    }

    private fun initRecyclerView() {
        toDoAdapter = ToDoAdapter()
        mainBinding.rTodoList.adapter = toDoAdapter
        mainBinding.rTodoList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
    }

    private fun showAddToDoDialog() {
        dialogBinding = DialogAddBinding.inflate(layoutInflater)
        val addTitle: EditText by lazy {
            dialogBinding.addTitle
        }
        val addContent: EditText by lazy {
            dialogBinding.addContent
        }
        var builder = AlertDialog.Builder(this)
        val dialog = builder.setTitle("ToDo item add").setView(dialogBinding.root)
            .setPositiveButton("확인") { dialogInterface, i ->
                var id: Long? = null
                val title = addTitle.text.toString()
                val content = addContent.text.toString()
                val createdDate = Date().time
                val toDoModel = ToDoModel(id, title, content, createdDate)
                toDoViewModel.insert(toDoModel)
            }
            .setNegativeButton("취소", null)
            .create()
        dialog.show()
    }
}