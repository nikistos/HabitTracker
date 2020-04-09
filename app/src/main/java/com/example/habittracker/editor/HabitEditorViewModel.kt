package com.example.habittracker.editor

import androidx.lifecycle.ViewModel
import com.example.habittracker.database.HabitDao
import com.example.habittracker.database.HabitEntity

class HabitEditorViewModel(val database: HabitDao, val habitIndex: Int? = null) : ViewModel() {

    var habit = if (habitIndex == null) HabitEntity() else database.getHabitById(habitIndex)
    val isNewHabit = (habitIndex == null)

    fun updateHabit() {
        database.update(habit)
    }

    fun addHabit() {
        database.insert(habit)
    }
}