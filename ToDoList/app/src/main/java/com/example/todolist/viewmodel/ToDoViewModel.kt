package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.ToDoModel
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {
    private val toDoRepository = ToDoRepository(application)
    var todoItems = toDoRepository.getToDoList()

    fun getToDoList(): LiveData<List<ToDoModel>> {
        return todoItems
    }

    fun getLeftToDo(): LiveData<List<ToDoModel>> {
        return todoItems
    }

    fun getDoneToDo(): LiveData<List<ToDoModel>> {
        return todoItems
    }


    fun insert(toDoModel: ToDoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.insert(toDoModel)
        }
    }

    fun delete(toDoModel: ToDoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.delete(toDoModel)
        }
    }

    fun update(toDoModel: ToDoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.update(toDoModel)
        }
    }

    fun completed(toDoModel: ToDoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            if(!toDoModel.completed) toDoModel.completed = true
            toDoRepository.update(toDoModel)
        }
    }
}