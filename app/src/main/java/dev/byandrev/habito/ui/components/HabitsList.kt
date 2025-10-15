package dev.byandrev.habito.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.byandrev.habito.data.HabitCompletion
import dev.byandrev.habito.data.getDailyHabits
import dev.byandrev.habito.ui.AppViewModelProvider
import dev.byandrev.habito.viewmodel.HabitViewModel
import java.time.LocalDate

@Composable
fun HabitsList(
    today: LocalDate,
    viewModel: HabitViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val habits by viewModel.habits.collectAsState()

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