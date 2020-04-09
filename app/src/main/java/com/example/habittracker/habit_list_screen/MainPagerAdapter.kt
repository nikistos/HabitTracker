package com.example.habittracker.habit_list_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val TAB_COUNT = 2

    override fun getItemCount(): Int = TAB_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HabitListFragment.habitTypeInstance(
                HabitListFragment.GOOD_HABITS)
            else -> HabitListFragment.habitTypeInstance(
                HabitListFragment.BAD_HABITS)
        }
    }
}