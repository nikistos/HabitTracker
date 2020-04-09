package com.example.habittracker.habit_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittracker.database.HabitDao
import com.example.habittracker.database.HabitEntity

class HabitListViewModel(database: HabitDao) : ViewModel() {

    private val dataSource = database
    private val _habits = MutableLiveData<List<HabitEntity>>()

    val habits: LiveData<List<HabitEntity>>
        get() = _habits


    var filterText = MutableLiveData<String>("")

    init {
        _habits.value = dataSource.getAllHabits()
    }
}