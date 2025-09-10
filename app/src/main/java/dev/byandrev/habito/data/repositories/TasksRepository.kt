package dev.byandrev.habito.data.repositories

import dev.byandrev.habito.data.Task
import dev.byandrev.habito.data.TaskDao
import dev.byandrev.habito.ui.components.Filters
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getAllTasksStream(): Flow<List<Task>>
    fun searchTasks(isChecked: Boolean): Flow<List<Task>>
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
}


class OfflineTasksRepository(private val taskDao: TaskDao) : TasksRepository {
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()
    override fun searchTasks(isChecked: Boolean): Flow<List<Task>> = taskDao.searchTasks(isChecked)
    override suspend fun insertTask(task: Task) = taskDao.insert(task)
    override suspend fun updateTask(task: Task) = taskDao.update(task)
}
