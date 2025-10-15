package dev.byandrev.habito.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.byandrev.habito.data.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert
    suspend fun insert(habit: Habit)


    @Update
    suspend fun update(habit: Habit)

    suspend fun updateWithTimestamp(habit: Habit) {
        insert(habit.apply{
            updatedAt = System.currentTimeMillis()
        })
    }

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habits ORDER BY created_at DESC")
    fun getAllHabits(): Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getHabitById(id: Int): Habit?
}
