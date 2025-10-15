package dev.byandrev.habito.ui

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.byandrev.habito.HabitoApplication
import dev.byandrev.habito.viewmodel.TaskViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import dev.byandrev.habito.viewmodel.HabitViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            TaskViewModel(habitoApplication().container.tasksRepository)
        }

        initializer {
            HabitViewModel(habitoApplication().container.habitsRepository)
        }
    }
}

fun CreationExtras.habitoApplication(): HabitoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as HabitoApplication)