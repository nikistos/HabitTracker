package com.example.habittracker.habit_list_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.database.HabitEntity
import com.example.habittracker.databinding.FragmentHabitListBinding
import com.example.habittracker.editor.HabitEditorFragment

class HabitAdapter(val habitList: List<HabitEntity>) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val habitName: TextView = itemView.findViewById(R.id.habit_name_item)
        val habitDescription: TextView = itemView.findViewById(R.id.description_item)
        val habitPriority: TextView = itemView.findViewById(R.id.priorityItem)
        val habitType: TextView = itemView.findViewById(R.id.typeItem)
        val habitPeriod: TextView = itemView.findViewById(R.id.periodicityItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_habitlist_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = habitList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.habitName.text = habitList[position].name
        holder.habitDescription.text = habitList[position].description
        holder.habitPeriod.text = "Repeat ${habitList[position].repeatCount} times per ${habitList[position].period}"
        holder.habitPriority.text = "Priority: ${habitList[position].priority}"
        holder.habitType.text = habitList[position].type.toString()

        holder.itemView.setOnClickListener() { v: View? ->
            v!!.findNavController().navigate(
                R.id.action_mainViewFragment_to_habitEditorFragment,
                HabitEditorFragment.createBundleWithIndex(habitList[position].id))
        }
    }
}