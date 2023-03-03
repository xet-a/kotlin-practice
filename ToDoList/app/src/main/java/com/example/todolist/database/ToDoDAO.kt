package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.model.ToDoModel

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM ToDo ORDER BY createdDate ASC")
    fun getToDoList(): LiveData<List<ToDoModel>>

    @Insert
    fun insertToDo(toDoModel: ToDoModel)
}