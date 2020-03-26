package com.example.habittracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_habit_list.*

class HabitListFragment : Fragment() {

    lateinit var habitAdapter: HabitAdapter
    private var habitType = ""

    companion object {
        const val  GOOD_HABITS = "GOOD_HABITS"
        const val BAD_HABITS = "BAD_HABITS"
        private const val HABIT_TYPE = "HABIT_TYPE"

        fun habitTypeInstance(habitType: String): HabitListFragment {
            return HabitListFragment().apply { arguments = bundleOf(HABIT_TYPE to habitType) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_habit_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            habitType = it.getString(HABIT_TYPE, GOOD_HABITS)
        }

        habitAdapter = when (habitType) {
            GOOD_HABITS -> HabitAdapter(MainActivity.habitsList.filter { habit -> habit.type == "Good" })
            else ->  HabitAdapter(MainActivity.habitsList.filter { habit -> habit.type == "Bad" })
        }

        habitRecycleView.layoutManager = LinearLayoutManager(context)
        habitRecycleView.adapter = habitAdapter
    }

    override fun onResume() {
        super.onResume()
        habitAdapter.notifyDataSetChanged()
    }


}
