package com.example.habittracker.habit_list_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.R
import com.example.habittracker.database.AppDatabase
import com.example.habittracker.database.HabitType
import kotlinx.android.synthetic.main.fragment_habit_list.*

class HabitListFragment : Fragment() {

    lateinit var habitAdapter: HabitAdapter
    private var habitType = ""
    lateinit var viewModel: HabitListViewModel

    companion object {
        const val GOOD_HABITS = "GOOD_HABITS"
        const val BAD_HABITS = "BAD_HABITS"
        private const val HABIT_TYPE = "HABIT_TYPE"

        fun habitTypeInstance(habitType: String): HabitListFragment {
            return HabitListFragment()
                .apply { arguments = bundleOf(HABIT_TYPE to habitType) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val database = AppDatabase.getInstance(requireContext()).habitDao()
        val factory = HabitListViewModelFactory(database)
        viewModel = ViewModelProviders.of(requireActivity(), factory).get(HabitListViewModel::class.java)

        return inflater.inflate(R.layout.fragment_habit_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            habitType = it.getString(
                HABIT_TYPE,
                GOOD_HABITS
            )
        }

        viewModel.habits.observe(viewLifecycleOwner, Observer { habits ->
            habitRecycleView.also {
                habitRecycleView.layoutManager = LinearLayoutManager(context)
                it.adapter = when (habitType) {
                    GOOD_HABITS -> HabitAdapter(viewModel.getSortedList(HabitType.Good))
                    else -> HabitAdapter(viewModel.getSortedList(HabitType.Bad))
                }
            }
        })

        viewModel.filterText.observe(viewLifecycleOwner, Observer {
            habitRecycleView.also {
                habitRecycleView.layoutManager = LinearLayoutManager(context)
                it.adapter = when (habitType) {
                    GOOD_HABITS -> HabitAdapter(viewModel.getSortedList(HabitType.Good))
                    else -> HabitAdapter(viewModel.getSortedList(HabitType.Bad))
                }
            }
        })

        viewModel.isSortByAscendingIds.observe(viewLifecycleOwner, Observer {
            habitRecycleView.also {
                habitRecycleView.layoutManager = LinearLayoutManager(context)
                it.adapter = when (habitType) {
                    GOOD_HABITS -> HabitAdapter(viewModel.getSortedList(HabitType.Good))
                    else -> HabitAdapter(viewModel.getSortedList(HabitType.Bad))
                }
            }
        })

    }
}
