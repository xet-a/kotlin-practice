package com.example.todolist.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentListBinding
import com.example.todolist.model.ToDoModel
import com.example.todolist.view.adapter.ToDoAdapter
import com.example.todolist.view.fragment.AddFragment
import com.example.todolist.view.helper.ToDoTouchHelperCallback
import com.example.todolist.viewmodel.ToDoViewModel

class MainActivity : AppCompatActivity() {
    private val toDoViewModel by viewModels<ToDoViewModel>()
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var mainBinding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        mainBinding = FragmentListBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        val addFragment = AddFragment(applicationContext)

        initViewModel()
        initRecyclerView()
        mainBinding.btnAddTodo.setOnClickListener {
            addFragment.show(supportFragmentManager, addFragment.tag)
        }
    }

    private fun initViewModel() {
        toDoViewModel.getToDoList().observe(this, androidx.lifecycle.Observer {
            toDoAdapter.setTodoItems(it)
        })
    }

    private fun initRecyclerView() {
        toDoAdapter = ToDoAdapter({ todo -> deleteDialog(todo) })
        mainBinding.rTodoList.adapter = toDoAdapter
        mainBinding.rTodoList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        itemTouchHelper = ItemTouchHelper(ToDoTouchHelperCallback(toDoAdapter))
        itemTouchHelper.attachToRecyclerView(mainBinding.rTodoList)
    }

    private fun deleteDialog(toDoModel: ToDoModel) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("[" + toDoModel.title.toString()+"] 삭제할까요? ")
            .setNegativeButton("취소") { _, _ -> }
            .setPositiveButton("확인") { _, _ ->
                toDoViewModel.delete(toDoModel)
            }
            .create()
        builder.show()
    }
}