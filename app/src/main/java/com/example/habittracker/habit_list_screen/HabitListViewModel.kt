package com.example.habittracker.habit_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habittracker.database.HabitDao
import com.example.habittracker.database.HabitEntity
import com.example.habittracker.database.HabitType

class HabitListViewModel(database: HabitDao) : ViewModel() {

    private val dataSource = database
    private val _habits = MutableLiveData<List<HabitEntity>>()

    val habits: LiveData<List<HabitEntity>>
        get() = _habits


    var filterText = MutableLiveData<String>("")
    var isSortByAscendingIds = MutableLiveData<Boolean>(true)

    init {
        _habits.value = dataSource.getAllHabits()
    }

    fun getSortedList(type: HabitType): List<HabitEntity> {
        var list = habits.value!!
        list = if (type == HabitType.Good) list.filter { n -> n.type == HabitType.Good }
            else list.filter{n -> n.type == HabitType.Bad}
        list = if (isSortByAscendingIds.value!!) list.sortedBy { n -> n.id }
            else list.sortedByDescending { n -> n.id }
        list = list.filter { n -> n.name.trim().startsWith(filterText.value.toString()) }

        return list
    }
}