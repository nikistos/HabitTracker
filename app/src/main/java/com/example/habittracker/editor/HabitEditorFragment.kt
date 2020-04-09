package com.example.habittracker.editor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_habit_editor.*
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.habittracker.R
import com.example.habittracker.database.AppDatabase
import com.example.habittracker.database.HabitType
import com.example.habittracker.databinding.FragmentHabitEditorBinding


class HabitEditorFragment : Fragment() {

    var habitIndex: Int? = null
    private lateinit var viewModel: HabitEditorViewModel

    companion object {
        private const val HABIT_INDEX = "HABIT_INDEX"

        fun createBundleWithIndex(habitIndex: Int): Bundle {
            return bundleOf(HABIT_INDEX to habitIndex)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (arguments!!.containsKey(HABIT_INDEX)) {
            habitIndex = arguments!!.getInt(HABIT_INDEX)
        }

        val database = AppDatabase.getInstance(requireContext()).habitDao()
        val factory = HabitEditorViewModelFactory(database, habitIndex)
        viewModel = ViewModelProviders.of(this, factory).get(HabitEditorViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentHabitEditorBinding>(inflater,
            R.layout.fragment_habit_editor, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillUiControllers()

        addHabitButton.setOnClickListener { view: View ->
            readHabitFromUI()
            viewModel.addHabit()
            view.findNavController().navigate(R.id.action_habitEditorFragment_to_mainViewFragment)
        }

        saveHabitButton.setOnClickListener { view ->
            readHabitFromUI()
            viewModel.updateHabit()
            view.findNavController().navigate(R.id.action_habitEditorFragment_to_mainViewFragment)
        }
    }

    private fun readHabitFromUI() {
        viewModel.habit.name = habitNameText.text.toString()
        viewModel.habit.description = habitDescriptionText.text.toString()
        viewModel.habit.priority = habitPriorityText.text.toString()
        viewModel.habit.type = HabitType.valueOf(
            getView()!!.findViewById<RadioButton>(habitTypeRadio.checkedRadioButtonId).text.toString())
        val repeatCount = repeatCountText.text.toString()
        viewModel.habit.repeatCount = if (repeatCount.trim().isEmpty()) 1 else repeatCount.toInt()
        viewModel.habit.period = habitPeriodicity.text.toString()
    }

    private fun fillUiControllers() {

        var adapter = ArrayAdapter(context!!, R.layout.dropdown_menu_popup_item, resources.getStringArray(R.array.priority))
        habitPriorityText.setAdapter(adapter)
        adapter = ArrayAdapter(context!!, R.layout.dropdown_menu_popup_item, resources.getStringArray(R.array.periodicity))
        habitPeriodicity.setAdapter(adapter)

        when(viewModel.habit.type.toString()) {
            getString(R.string.habit_type1) -> habitTypeRadio.check(
                R.id.doSomethingButton
            )
            getString(R.string.habit_type2) -> habitTypeRadio.check(
                R.id.doNotDoSomethingButton
            )
        }
    }
}
