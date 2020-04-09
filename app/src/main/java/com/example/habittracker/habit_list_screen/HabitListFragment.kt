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

        habitRecycleView.layoutManager = LinearLayoutManager(context)

        viewModel.habits.observe(viewLifecycleOwner, Observer { habits ->
            habitRecycleView.also {
                it.adapter = when (habitType) {
                    GOOD_HABITS -> HabitAdapter(habits.filter { n -> n.type == HabitType.Good })
                    else -> HabitAdapter(habits.filter { n -> n.type == HabitType.Bad })
                }
            }
        })

        viewModel.filterText.observe(viewLifecycleOwner, Observer {
            habitRecycleView.also {
                it.adapter = when (habitType) {
                    GOOD_HABITS -> HabitAdapter(viewModel.habits.value!!.filter { n ->
                        n.type == HabitType.Good && n.name.trim().startsWith(viewModel.filterText.value!!)})
                    else -> HabitAdapter(viewModel.habits.value!!.filter { n ->
                        n.type == HabitType.Bad && n.name.trim().startsWith(viewModel.filterText.value!!)})
                }
            }
        })

    }
}
