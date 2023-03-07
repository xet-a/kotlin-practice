package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todolist.model.ToDoModel
import com.example.todolist.repository.ToDoRepository

class ToDoViewModel(application: Application): AndroidViewModel(application) {
    private val toDoRepository = ToDoRepository(application)
    var todoItems = toDoRepository.getToDoList()

    fun getToDoList(): LiveData<List<ToDoModel>> {
        return todoItems
    }

    fun insert(toDoModel: ToDoModel) {
        toDoRepository.insert(toDoModel)
    }

    fun delete(toDoModel: ToDoModel) {
        toDoRepository.delete(toDoModel)
    }

    fun update(toDoModel: ToDoModel) {
        toDoRepository.update(toDoModel)
    }
}