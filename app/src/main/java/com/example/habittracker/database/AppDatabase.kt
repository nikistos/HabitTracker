package com.example.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HabitEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(applicationContext: Context) : AppDatabase {
            var instance =
                INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "habit_database"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            INSTANCE = instance
            return instance
        }
    }
}