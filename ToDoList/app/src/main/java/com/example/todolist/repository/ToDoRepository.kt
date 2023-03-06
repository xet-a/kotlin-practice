package com.example.todolist.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todolist.database.ToDoDAO
import com.example.todolist.database.ToDoDatabase
import com.example.todolist.model.ToDoModel

class ToDoRepository(application: Application) {
    private var toDoDatabase: ToDoDatabase = ToDoDatabase.getInstance(application)!!
    private var todoDao: ToDoDAO = toDoDatabase.todoDao()
    private var todoItems: LiveData<List<ToDoModel>> = todoDao.getToDoList()

    fun getToDoList(): LiveData<List<ToDoModel>> {
        return todoItems
    }

    fun insert(toDoModel: ToDoModel){
        try {
            val thread = Thread(Runnable {
                todoDao.insert(toDoModel)
            }).start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun delete(toDoModel: ToDoModel) {
        try {
            val thread = Thread(Runnable {
                todoDao.delete(toDoModel)
            }).start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}