package com.example.habittracker.editor

import androidx.lifecycle.ViewModel
import com.example.habittracker.database.AppDatabase
import com.example.habittracker.database.HabitDao
import com.example.habittracker.database.HabitEntity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HabitEditorViewModel(val database: HabitDao, val habitIndex: Int? = null) : ViewModel(),
    CoroutineScope {

    var habit = if (habitIndex == null) HabitEntity() else database.getHabitById(habitIndex)
    val isNewHabit = (habitIndex == null)

    private var viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    fun updateHabit() = launch {
        withContext(Dispatchers.IO) {
            database.update(habit)
        }
    }

    fun addHabit() = launch {
        withContext(Dispatchers.IO) {
            database.insert(habit)
        }
    }
}