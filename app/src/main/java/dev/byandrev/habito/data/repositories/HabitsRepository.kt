package dev.byandrev.habito.data.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.byandrev.habito.data.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HabitsRepository(private val database: FirebaseDatabase) {
    private val _allHabits = MutableStateFlow<List<Habit>>(emptyList())
    val allHabits: StateFlow<List<Habit>> = _allHabits

    private val habitsRef = database.getReference("habits")

    init {
        listenForHabitChanges()
    }

    private fun listenForHabitChanges() {
        habitsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newHabits = mutableListOf<Habit>()
                snapshot.children.forEach { childSnapshot ->
                    val habit = childSnapshot.getValue(Habit::class.java)
                    habit?.let { newHabits.add(it) }
                }

                _allHabits.value = newHabits
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error de Base de Datos: ${error.message}")
            }
        })
    }

    fun addNewHabit(habit: Habit) {
        val habitId = habitsRef.push().key ?: return
        habitsRef.child(habitId).setValue(habit)
    }
}