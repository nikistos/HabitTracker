package com.example.habittracker

class Habit () {

    var name: String = ""
    var description: String = ""
    var priority: String = ""
    var type: String = ""
    var repeatCount: Int = 0
    var period: String = ""

    companion object {
        val priorities = arrayOf("Not high", "High", "Very high")
        val periodicity = arrayOf("Day", "Week", "Month")
    }
}
