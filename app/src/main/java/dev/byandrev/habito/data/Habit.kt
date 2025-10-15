package dev.byandrev.habito.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Habit structure
 * @param id
 * @param name Habit name.
 * @param schedule Day of week.
 */
@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String? = null,
//    val schedule: Set<DayOfWeek>,
    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP") var createdAt: Long? = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at", defaultValue = "CURRENT_TIMESTAMP") var updatedAt: Long? = System.currentTimeMillis()
)

data class HabitCompletion(
    val habitId: Int,
    val date: LocalDate
)

// TODO: create file
fun isHabitDueToday(habit: Habit, currentDate: LocalDate): Boolean {
//    return habit.schedule.contains(currentDate.dayOfWeek)
    return true
}

// TODO: create file
fun isHabitCompletedToday(
    completions: List<HabitCompletion>,
    habitId: Int,
    currentDate: LocalDate
): Boolean {
    return completions.any { it.habitId == habitId && it.date.isEqual(currentDate) }
}

// TODO: create file
fun calculateWeeklyCompletionPercentage(
    habit: Habit,
    completions: List<HabitCompletion>,
    currentDate: LocalDate
): Float {
    val startOfWeek = currentDate.with(DayOfWeek.MONDAY)

    var scheduledCount = 0
    var completedCount = 0

    var date = startOfWeek
    while (!date.isAfter(currentDate)) {

        if (isHabitDueToday(habit, date)) {
            scheduledCount++

            if (isHabitCompletedToday(completions, habit.id, date)) {
                completedCount++
            }
        }

        date = date.plusDays(1)
    }

    return (if (scheduledCount == 0) {
        0.0f
    } else {
        (completedCount.toFloat() / scheduledCount.toFloat()) * 100f
    })
}

fun getDailyHabits(allHabits: List<Habit>, currentDate: LocalDate): List<Habit> {
    return allHabits.filter { habit ->
        isHabitDueToday(habit, currentDate)
    }
}