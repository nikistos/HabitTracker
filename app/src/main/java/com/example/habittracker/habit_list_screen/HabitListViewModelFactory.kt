package com.example.habittracker.habit_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker.database.HabitDao

class HabitListViewModelFactory(private val database: HabitDao): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HabitListViewModel(database) as T
    }
}