package com.example.habittracker.habit_list_screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.habittracker.R
import com.example.habittracker.database.AppDatabase
import kotlinx.android.synthetic.main.fragment_fitration_bottom_sheet.*

class FiltrationFragment : Fragment() {

    private lateinit var viewModel: HabitListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val database = AppDatabase.getInstance(requireContext()).habitDao()
        val factory = HabitListViewModelFactory(database)
        viewModel = ViewModelProviders.of(requireActivity(), factory).get(HabitListViewModel::class.java)

        return inflater.inflate(R.layout.fragment_fitration_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        habitFilter.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filterText.value = s.toString()
            }

        })
    }
}