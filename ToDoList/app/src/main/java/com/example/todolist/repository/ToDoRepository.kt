package com.example.todolist.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todolist.database.ToDoDAO
import com.example.todolist.database.ToDoRoomDatabase
import com.example.todolist.model.ToDoModel

class ToDoRepository(private val toDoDAO: ToDoDAO) {
    val readAllData: LiveData<List<ToDoModel>> = toDoDAO.getToDoList()

    suspend fun addToDo(toDoModel: ToDoModel){
        toDoDAO.insertToDo(toDoModel)
    }
}