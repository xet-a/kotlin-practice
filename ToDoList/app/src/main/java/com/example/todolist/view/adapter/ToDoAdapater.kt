package com.example.todolist.view.adapter

import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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

    override fun onItemMove(from: Int, to: Int): Boolean {
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

    inner class ToDoViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todoModel: ToDoModel) {
            binding.todoTitle.text = todoModel.title
            binding.todoDescription.text = todoModel.description
            binding.todoCreatedDate.text = todoModel.dueDate

            if (todoModel.completed) {
                binding.todoTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.todoDescription.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.todoCreatedDate.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            binding.completeButton.setImageResource(todoModel.imageResource())
            binding.completeButton.setOnClickListener {
                listener?.onItemClick(binding.root, todoModel, adapterPosition)
                binding.todoCreatedDate.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            binding.todoRow.setOnClickListener {
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoViewHolder = holder
        toDoViewHolder.bind(todoItems[position])
    }

    fun setTodoItems(todoItems: List<ToDoModel>) {
        this.todoItems = todoItems as ArrayList<ToDoModel>
        Log.e("Set", "todoItem setTodoItems !!: " + todoItems.size);
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ToDoModel, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        Log.d("Adapter", "setonitemclicklistener")
        this.listener = listener
    }
}