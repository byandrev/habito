package dev.byandrev.habito.data

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import dev.byandrev.habito.data.repositories.HabitsRepository
import dev.byandrev.habito.data.repositories.OfflineTasksRepository
import dev.byandrev.habito.data.repositories.TasksRepository

interface AppContainer {
    val tasksRepository: TasksRepository
    val habitsRepository: HabitsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val tasksRepository: TasksRepository by lazy {
        OfflineTasksRepository(AppDatabase.getDatabase(context).taskDao())
    }

    override val habitsRepository: HabitsRepository by lazy {
        HabitsRepository(FirebaseDatabase.getInstance())
    }
}
