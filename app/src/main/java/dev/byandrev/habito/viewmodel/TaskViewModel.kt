package dev.byandrev.habito.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.byandrev.habito.data.Task
import dev.byandrev.habito.data.repositories.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    val tasks: StateFlow<List<Task>> =
        tasksRepository.getAllTasksStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun addTask(name: String, checked: Boolean) {
        viewModelScope.launch {
            val newTask = Task(name = name, checked = checked)
            tasksRepository.insertTask(newTask)
        }
    }
}
