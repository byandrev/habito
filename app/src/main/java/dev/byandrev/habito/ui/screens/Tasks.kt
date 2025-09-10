package dev.byandrev.habito.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.byandrev.habito.R
import dev.byandrev.habito.ui.AppViewModelProvider
import dev.byandrev.habito.ui.components.FormTask
import dev.byandrev.habito.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import dev.byandrev.habito.ui.components.TabsTasks

// TODO: create file
data class Task(
    val name: String,
    var checked: Boolean
)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen(
    viewModel: TaskViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { openAlertDialog.value = true },
                shape = CircleShape,
                modifier = Modifier.size(60.dp).absoluteOffset(y = (50).dp)
            ) {
                Icon(Icons.Filled.Add, "Large floating action button")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        Column() {
            when {
                openAlertDialog.value -> {
                    FormTask(
                        onDismissRequest = { openAlertDialog.value = false },
                        onConfirmation = {
                            value ->
                            openAlertDialog.value = false

                            val task = Task(name = value, checked = false)

                            scope.launch {
                                viewModel.addTask(name = task.name, checked = task.checked)
                            }
                        },
                        dialogTitle = stringResource(R.string.add_task)
                    )
                }
            }

            TabsTasks(
                onChangeTab = { tab ->
                    var isChecked: Boolean? = null

                    if (tab == "completed") isChecked = true
                    if (tab == "todo") isChecked = false

                    scope.launch {
                        viewModel.setFilter(isChecked)
                    }
                }
            )

            LazyColumn (modifier = Modifier.padding(top = 20.dp)) {
                items(tasks) { task ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = task.checked,
                            onCheckedChange = {  isChecked ->
                                scope.launch {
                                    val taskUpdated = task.copy()
                                    taskUpdated.checked = isChecked
                                    viewModel.updateTask(taskUpdated)
                                }
                            }
                        )

                        Text(text = task.name)
                    }
                }
            }
        }
    }
}
