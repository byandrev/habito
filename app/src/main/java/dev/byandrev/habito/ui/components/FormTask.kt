package dev.byandrev.habito.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.utils.Converters
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTask(
    onDismissRequest: () -> Unit,
    onConfirmation: (textInput: String, inputDescription: String, date: LocalDateTime?) -> Unit,
    dialogTitle: String

) {
    val textInput = remember { mutableStateOf("") }
    val inputDescription = remember { mutableStateOf("") }
    val dateState = rememberDatePickerState()
    val timeState = rememberTimePickerState()
    var showDialog by remember { mutableStateOf(false) }

    val timestamp = dateState.selectedDateMillis

    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column {
                TextField(
                    value = textInput.value,
                    modifier = Modifier.padding(bottom = 10.dp),
                    label = {
                        Text("Name")
                    },
                    onValueChange = { value -> textInput.value = value }
                )

                TextField(
                    value = inputDescription.value,
                    modifier = Modifier.padding(bottom = 10.dp),
                    label = {
                        Text("Description")
                    },
                    onValueChange = { value -> inputDescription.value = value }
                )

                TimePicker(state = timeState)

                Button(
                    onClick = { showDialog = true }
                ) {
                    Text(text = "Select Date")
                }

                if (showDialog) {
                    DatePickerDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            Button(
                                onClick = { showDialog = false }
                            ) {
                                Text(text = "OK")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false }
                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    ) {
                        DatePicker(
                            state = dateState,
                            showModeToggle = true
                        )
                    }
                }
            }
        },

        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val dateMillis = dateState.selectedDateMillis
                    if (dateMillis != null) {
                        val localDate = Instant.ofEpochMilli(dateMillis)
                            .atZone(ZoneOffset.UTC)
                            .toLocalDate()

                        val hour = timeState.hour
                        val minute = timeState.minute

                        val dateTime = localDate.atTime(hour, minute)
                        onConfirmation(textInput.value, inputDescription.value, dateTime)
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}