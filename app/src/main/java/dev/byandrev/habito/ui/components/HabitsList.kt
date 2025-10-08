package dev.byandrev.habito.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.data.HabitCompletion
import dev.byandrev.habito.data.getDailyHabits
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun HabitsList(
    today: LocalDate
) {
    val habits = remember {
        listOf(
            Habit(id = 1, name = "Beber 2 litros de agua", schedule = setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)),
            Habit(id = 2, name = "Rutina de ejercicios 30m", schedule = setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)),
            Habit(id = 3, name = "Leer 10 páginas", schedule = setOf(DayOfWeek.SUNDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY))
        )
    }

    var completions by remember { mutableStateOf(listOf(
        HabitCompletion(habitId = 1, date = LocalDate.of(2025, 10, 6))
    )) }

    val onCompleteHabit: (HabitCompletion) -> Unit = { completion ->
        completions = completions + completion
    }

    val dailyHabits = getDailyHabits(habits, today)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        dailyHabits.forEach { habit ->
            HabitItem(
                habit = habit,
                completions = completions,
                onComplete = onCompleteHabit,
                currentDate = today
            )

            HorizontalDivider(
                modifier = Modifier.height(1.dp).fillMaxWidth(),
                thickness = DividerDefaults.Thickness, color = MaterialTheme.colorScheme.outline
            )
        }
    }
}