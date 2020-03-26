package com.example.habittracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_habit_editor.*
import android.widget.RadioButton
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.navigation.findNavController


class HabitEditorFragment : Fragment() {

    companion object {
        private const val HABIT_INDEX = "HABIT_INDEX"

        fun createBundleWithIndex(habitIndex: Int): Bundle {
            return bundleOf(HABIT_INDEX to habitIndex)
        }
    }

    var habitIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_habit_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!!.containsKey(HABIT_INDEX)) {
            habitIndex = arguments!!.getInt(HABIT_INDEX)
            saveHabitButton.visibility = View.VISIBLE
            addHabitButton.visibility = View.INVISIBLE
            fillHabitData()
        }

        var adapter = ArrayAdapter(context!!, R.layout.dropdown_menu_popup_item, Habit.priorities)
        habitPriorityText.setAdapter(adapter)
        adapter = ArrayAdapter(context!!, R.layout.dropdown_menu_popup_item, Habit.periodicity)
        habitPeriodicity.setAdapter(adapter)

        addHabitButton.setOnClickListener { view: View ->
            MainActivity.habitsList.add(createHabit())
            view.findNavController().navigate(R.id.action_habitEditorFragment_to_mainViewFragment)
        }

        saveHabitButton.setOnClickListener { view ->
            MainActivity.habitsList[habitIndex] = createHabit()
            view.findNavController().navigate(R.id.action_habitEditorFragment_to_mainViewFragment)
        }

    }

    private fun createHabit(): Habit {
        val habit = Habit()
        habit.name = habitNameText.text.toString()
        habit.description = habitDescriptionText.text.toString()
        habit.priority = habitPriorityText.text.toString()
        habit.type = getView()!!.findViewById<RadioButton>(habitTypeRadio.checkedRadioButtonId).text.toString()
        val repeatCount = repeatCountText.text.toString()
        habit.repeatCount = if (repeatCount.trim().isEmpty()) 1 else repeatCount.toInt()
        habit.period = habitPeriodicity.text.toString()
        return habit
    }

    private fun fillHabitData() {
        val habit = MainActivity.habitsList[habitIndex]
        habitNameText.setText(habit.name)
        habitDescriptionText.setText(habit.description)
        habitPriorityText.setText(habit.priority)
        when(habit.type) {
            getString(R.string.habit_type1) -> habitTypeRadio.check(R.id.doSomethingButton)
            getString(R.string.habit_type2) -> habitTypeRadio.check(R.id.doNotDoSomethingButton)
        }
        repeatCountText.setText(habit.repeatCount.toString())
        habitPeriodicity.setText(habit.period)
    }

}
