package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.model.ToDoModel

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM ToDo ORDER BY createdDate ASC")
    fun getToDoList(): LiveData<List<ToDoModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toDoModel: ToDoModel)

    @Delete
    fun delete(toDoModel: ToDoModel)
}