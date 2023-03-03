package com.example.todolist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.model.ToDoModel
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var todoItems: List<ToDoModel> = listOf()

    interface OnTodoItemClickListener {
        fun onTodoItemClick(position: Int)
        fun onTodoItemLongClick(position: Int)
    }

    fun getItem(position: Int): ToDoModel {
        return todoItems[position]
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todoModel = todoItems[position]

        val todoViewHolder = holder as TodoViewHolder
        todoViewHolder.bind(todoModel)
    }

    class TodoViewHolder(binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        val title = binding.todoTitle
        val description = binding.todoDescription
        val createdDate = binding.todoCreatedDate

        fun bind(todoModel: ToDoModel) {
            title.text = todoModel.title
            description.text = todoModel.description
            createdDate.text = todoModel.createdDate.toDateString("yyyy.MM.dd HH:mm")
        }
    }
}

fun Long.toDateString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format((Date(this)))
}