package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.ToDoModel

@Database(entities = [ToDoModel::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDAO

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context).also { INSTANCE = it }
                }
            }
            return INSTANCE
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ToDoDatabase::class.java, "ToDo.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}