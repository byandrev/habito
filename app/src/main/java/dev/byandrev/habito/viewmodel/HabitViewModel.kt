package dev.byandrev.habito.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.data.repositories.HabitsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(private val habitsRepository: HabitsRepository) : ViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    var habits: StateFlow<List<Habit>> = habitsRepository
        .getAllHabitsStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            habitsRepository.insertHabit(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            habitsRepository.updateHabit(habit)
        }
    }
}
