package com.example.todolist.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.FragmentListBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.view.adapter.ToDoAdapter
import com.example.todolist.view.helper.ToDoTouchHelperCallback
import com.example.todolist.viewmodel.ToDoViewModel


class ListFragment: Fragment() {
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var listBinding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentListBinding.inflate(inflater)
        view.btnAddTodo.setOnClickListener{
            findNavController().navigate(R.id.addFragment)
        }

        initViewModel()
        initRecyclerView()

        return view.root
    }

    private fun initViewModel() {
        toDoViewModel.getToDoList().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            toDoAdapter.setTodoItems(it)
        })
    }

    private fun initRecyclerView() {
        toDoAdapter = ToDoAdapter { todo -> deleteDialog(todo) }
        listBinding = FragmentListBinding.inflate(layoutInflater)
        listBinding.rTodoList.adapter = toDoAdapter
        listBinding.rTodoList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        itemTouchHelper = ItemTouchHelper(ToDoTouchHelperCallback(toDoAdapter))
        itemTouchHelper.attachToRecyclerView(listBinding.rTodoList)
    }

    private fun deleteDialog(toDoModel: ToDoModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("[" + toDoModel.title.toString()+"] 삭제할까요? ")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("확인") { _, _ ->
                toDoViewModel.delete(toDoModel)
            }
            .create()
        builder.show()
    }
}