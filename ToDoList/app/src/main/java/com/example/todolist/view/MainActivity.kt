package com.example.todolist.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.view.adapter.ToDoAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var binding: ActivityMainBinding
//    private val todoItems: ArrayList<ToDoModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        todoAdapter = ToDoAdapter()

        binding.rTodoList.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }
    }
}