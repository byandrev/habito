package dev.byandrev.habito.data.repositories

import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.data.dao.HabitDao
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    fun getAllTasksStream(): Flow<List<Habit>>
    suspend fun insertTask(habit: Habit)
    suspend fun updateTask(habit: Habit)
}


class OfflineHabitsRepository(private val habitDao: HabitDao) : HabitsRepository {
    override fun getAllTasksStream(): Flow<List<Habit>> = habitDao.getAllHabits()
    override suspend fun insertTask(habit: Habit) = habitDao.insert(habit)
    override suspend fun updateTask(habit: Habit) = habitDao.update(habit)
}
