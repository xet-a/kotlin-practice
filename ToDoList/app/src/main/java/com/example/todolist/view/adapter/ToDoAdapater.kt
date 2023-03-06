package com.example.todolist.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.model.ToDoModel
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter: RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    private var todoItems = emptyList<ToDoModel>()

    inner class ToDoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: ToDoModel) {
            binding.todoTitle.text = todoModel.title
            binding.todoDescription.text = todoModel.description
            binding.todoCreatedDate.text = todoModel.createdDate.toDateString("yyyy.MM.dd HH:mm")
        }
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoViewHolder = holder
        toDoViewHolder.bind(todoItems[position])
    }

    fun setTodoItems(todoItems: List<ToDoModel>) {
        this.todoItems = todoItems
        Log.e("MainActivity", "todoItem setTodoItems !!: " + todoItems.size);
        notifyDataSetChanged()
    }

}

fun Long.toDateString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format((Date(this)))
}