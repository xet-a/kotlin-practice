package com.example.todolist.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.view.fragment.ListFragmentDirections
import com.example.todolist.view.helper.OnToDoItemClickListener
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter(val deleteItemClick: (ToDoModel) -> Unit)
    : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(), OnToDoItemClickListener {
    private var todoItems = ArrayList<ToDoModel>()

    override fun onItemMove(from: Int, to: Int): Boolean{
        val todo: ToDoModel = todoItems[from]
        todoItems.remove(todoItems[from])
        todoItems.add(to, todo)
        setTodoItems(todoItems)
        notifyItemMoved(from, to)
        // TODO: 아직 room에는 반영이 안 됨
        return true
    }

    override fun onItemSwipe(position: Int) {
        deleteItemClick(todoItems[position])
        notifyItemRemoved(position)
    }

    inner class ToDoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: ToDoModel) {
            binding.todoTitle.text = todoModel.title
            binding.todoDescription.text = todoModel.description
            binding.todoCreatedDate.text = todoModel.createdDate.toDateString("yyyy.MM.dd HH:mm")

            binding.todoRow.setOnClickListener{
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(todoModel)
                binding.root.findNavController().navigate(action)
            }
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
        this.todoItems = todoItems as ArrayList<ToDoModel>
        Log.e("Set", "todoItem setTodoItems !!: " + todoItems.size);
        notifyDataSetChanged()
    }

}

fun Long.toDateString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format((Date(this)))
}