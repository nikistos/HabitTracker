package com.example.habittracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "habits")
@TypeConverters(EnumConverter::class)
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String = "",
    var description: String = "",
    var priority: String = "",
    var type: HabitType = HabitType.Good,
    var repeatCount: Int = 0,
    var period: String = ""
)

enum class HabitType(i: Int) {
    Good(0), Bad(1)
}

class EnumConverter {
    @TypeConverter
    fun fromEnum(enum: HabitType): String {
        return enum.toString()
    }

    @TypeConverter
    fun toEnum(data: String): HabitType {
        return HabitType.valueOf(data)
    }
}