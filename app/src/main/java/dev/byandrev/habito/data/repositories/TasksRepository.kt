package dev.byandrev.habito.data.repositories

import dev.byandrev.habito.data.Task
import dev.byandrev.habito.data.TaskDao
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getAllTasksStream(): Flow<List<Task>>

    suspend fun insertTask(item: Task)
}


class OfflineTasksRepository(private val itemDao: TaskDao) : TasksRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = itemDao.getAllTasks()
    override suspend fun insertTask(item: Task) = itemDao.insert(item)
}
