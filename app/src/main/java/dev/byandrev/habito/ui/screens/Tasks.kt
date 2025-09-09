package dev.byandrev.habito.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.R

// TODO: create file
data class Task(
    val name: String,
    var checked: Boolean
)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen() {
    var countCompleted = remember { mutableStateOf(0) }
    val tasksState = remember { mutableStateListOf(
        Task("Estudiar Kotlin", false),
        Task("Hacer ejercicio", true),
        Task("Leer un libro", false))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { onClick() },
                shape = CircleShape,
                modifier = Modifier.size(60.dp).absoluteOffset(y = (50).dp)
            ) {
                Icon(Icons.Filled.Add, "Large floating action button")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        Column() {
            Text(text = stringResource(R.string.tasks), modifier = Modifier.padding(bottom = 20.dp))
            Text(text = countCompleted.value.toString())

            tasksState.forEachIndexed { index, task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.checked,
                        onCheckedChange = {  isChecked ->
                            countCompleted.value = countCompleted.value + 1
                            tasksState[index].checked = isChecked
                        }
                    )

                    Text(text = task.name)
                }
            }
        }
    }
}

fun onClick() {}