package dev.byandrev.habito.data

import android.content.Context
import dev.byandrev.habito.data.repositories.OfflineTasksRepository
import dev.byandrev.habito.data.repositories.TasksRepository

interface AppContainer {
    val tasksRepository: TasksRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val tasksRepository: TasksRepository by lazy {
        OfflineTasksRepository(AppDatabase.getDatabase(context).taskDao())
    }
}
