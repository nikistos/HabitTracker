package com.example.habittracker.database

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.R


@Dao
interface HabitDao {
    @Insert
    fun insert(Habit: HabitEntity)

    @Update
    fun update(Habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE id = :key")
    fun getHabitById(key: Int): HabitEntity

    @Query("SELECT * FROM habits")
    fun getAllHabits(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE type = 'Bad'")
    fun getBadHabits(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE type = 'Good'")
    fun getGoodHabits(): List<HabitEntity>
}