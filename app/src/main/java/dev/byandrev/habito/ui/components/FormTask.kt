package dev.byandrev.habito.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun FormTask(
    onDismissRequest: () -> Unit,
    onConfirmation: (textInput: String) -> Unit,
    dialogTitle: String

) {
    val textInput = remember { mutableStateOf("") }

    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            TextField(value = textInput.value, onValueChange = { value -> textInput.value = value })
        },

        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(textInput.value)
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