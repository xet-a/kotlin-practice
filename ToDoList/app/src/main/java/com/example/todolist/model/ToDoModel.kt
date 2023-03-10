package com.example.todolist.model

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist.R
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "ToDo")
data class ToDoModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "dueDate")
    var dueDate: String,
    @ColumnInfo(name = "completed")
    var completed: Boolean = false
):Parcelable {
    fun imageResource(): Int = if(completed) R.drawable.checked else R.drawable.unchecked
}