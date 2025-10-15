package dev.byandrev.habito.data.repositories

import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.data.dao.HabitDao
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    fun getAllHabitsStream(): Flow<List<Habit>>
    suspend fun insertHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
}


class OfflineHabitsRepository(private val habitDao: HabitDao) : HabitsRepository {
    override fun getAllHabitsStream(): Flow<List<Habit>> = habitDao.getAllHabits()
    override suspend fun insertHabit(habit: Habit) = habitDao.insert(habit)
    override suspend fun updateHabit(habit: Habit) = habitDao.update(habit)
}
