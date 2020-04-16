package com.example.habittracker.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker.database.HabitDao

class HabitEditorViewModelFactory(private val database: HabitDao, private val habitIndex: Int? = null):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HabitEditorViewModel(database, habitIndex) as T
    }
}