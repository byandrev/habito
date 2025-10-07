package dev.byandrev.habito.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.ui.components.HabitsList

@Composable
fun HomeScreen() {
    val habits = listOf<Habit>(
        Habit(name = "Run", description = "Every morning"),
        Habit(name = "Read", description = "Every evening"),
    )

    Surface(
        modifier = Modifier.padding(0.dp)
    ) {
        HabitsList(habits)
    }
}