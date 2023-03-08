package com.example.todolist.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
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
):Parcelable{
    constructor(): this(null, "", "", -1)
}