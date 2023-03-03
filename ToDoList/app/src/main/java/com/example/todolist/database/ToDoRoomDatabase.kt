package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.ToDoModel

@Database(entities = [ToDoModel::class], version = 1)
abstract class ToDoRoomDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDAO

    companion object {
        @Volatile
        private var INSTANCE: ToDoRoomDatabase? = null

        fun getInstance(context: Context): ToDoRoomDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ToDoRoomDatabase::class.java, "ToDo.db").build()
    }
}