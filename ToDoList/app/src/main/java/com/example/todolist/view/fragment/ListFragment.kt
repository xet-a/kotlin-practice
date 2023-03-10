package com.example.todolist.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.databinding.FragmentListBinding
import com.example.todolist.databinding.NaviHeaderBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.view.adapter.ToDoAdapter
import com.example.todolist.view.helper.ToDoTouchHelperCallback
import com.example.todolist.viewmodel.ToDoViewModel


class ListFragment: Fragment() {
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private var _listBinding: FragmentListBinding? = null
    private val listBinding get() = _listBinding!!
    private lateinit var headerBinding: NaviHeaderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _listBinding = FragmentListBinding.inflate(inflater)
        headerBinding = NaviHeaderBinding.inflate(inflater)

        initViewModel()
        initRecyclerView()

        listBinding.btnAddTodo.setOnClickListener{
            findNavController().navigate(R.id.addFragment)
        }

        listBinding.todoTitle.setOnClickListener {
            val left = toDoViewModel.getLeftToDo().value?.size.toString()
            headerBinding.navItem.text = "this"
            listBinding.drawerLayout.openDrawer(GravityCompat.START)
        }

        toDoAdapter.setOnItemClickListener(object : ToDoAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: ToDoModel, pos: Int) {
                toDoViewModel.completed(data)
            }
        })

        return listBinding.root
    }
    private fun initViewModel() {
        toDoViewModel.getToDoList().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            toDoAdapter.setTodoItems(it)
        })
    }

    private fun initRecyclerView() {
        toDoAdapter = ToDoAdapter { todo -> deleteDialog(todo) }
        listBinding.rTodoList.adapter = toDoAdapter
        listBinding.rTodoList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        itemTouchHelper = ItemTouchHelper(ToDoTouchHelperCallback(toDoAdapter))
        itemTouchHelper.attachToRecyclerView(listBinding.rTodoList)
    }

    private fun deleteDialog(toDoModel: ToDoModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("[" + toDoModel.title.toString()+"] ???????????????? ")
            .setNegativeButton("??????") { _, _ -> }
            .setPositiveButton("??????") { _, _ ->
                toDoViewModel.delete(toDoModel)
            }
            .create()
        builder.show()
    }
}