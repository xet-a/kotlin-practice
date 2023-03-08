package com.example.todolist.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
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
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var listBinding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}