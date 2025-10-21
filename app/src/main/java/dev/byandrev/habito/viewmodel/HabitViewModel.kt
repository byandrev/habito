package dev.byandrev.habito.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.data.repositories.HabitsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(private val repository: HabitsRepository) : ViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    var habits: StateFlow<List<Habit>> = repository
        .allHabits
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            repository.addNewHabit(habit)
        }
    }
}
