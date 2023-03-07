package com.example.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDo")
data class ToDoModel (
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "createdDate")
    var createdDate: Long
){
    constructor(): this(null, "", "", -1)
}