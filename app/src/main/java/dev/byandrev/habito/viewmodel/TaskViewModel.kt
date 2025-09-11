package dev.byandrev.habito.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.byandrev.habito.data.Task
import dev.byandrev.habito.data.repositories.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    private val _filter = MutableStateFlow<Boolean?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    var tasks: StateFlow<List<Task>> =
        _filter
            .flatMapLatest { filter ->
                when (filter) {
                    null -> tasksRepository.getAllTasksStream()
                    true -> tasksRepository.searchTasks(true)
                    else -> tasksRepository.searchTasks(false)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun setFilter(isChecked: Boolean?) {
        _filter.value = isChecked
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            tasksRepository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            tasksRepository.updateTask(task)
        }
    }
}
