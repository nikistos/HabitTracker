package com.example.habittracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main_pager.*

/**
 * A simple [Fragment] subclass.
 */
class MainViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPager.adapter = MainPagerAdapter(this)

        addHabitButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mainViewFragment_to_habitEditorFragment)
        )

        TabLayoutMediator(tabLayout, mainPager) { tab, position ->
            tab.text = when (position) {
                0 -> "GOOD"
                else -> "BAD"
            }
        }.attach()

    }

}
