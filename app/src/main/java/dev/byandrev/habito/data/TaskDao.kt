package dev.byandrev.habito.data

import androidx.room.*
import dev.byandrev.habito.ui.components.Filters
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)


    @Update
    suspend fun update(task: Task)

    suspend fun updateWithTimestamp(task: Task) {
        insert(task.apply{
            updatedAt = System.currentTimeMillis()
        })
    }

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM tasks ORDER BY created_at DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks where checked = :isChecked ORDER BY created_at DESC")
    fun searchTasks(isChecked: Boolean): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?
}
