package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.ToDoModel

@Database(entities = [ToDoModel::class], version = 5)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDAO

    // DB 인스턴스를 싱글톤으로 사용하기 위한 companion object
    // Room DB 객체 생성은 비용이 많이 들기 때문에 싱글톤으로 구현함
    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        // synchronized로 설정해 여러 스레드가 접근하지 못 함
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