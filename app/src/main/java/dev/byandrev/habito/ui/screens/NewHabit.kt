package dev.byandrev.habito.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.ui.AppViewModelProvider
import dev.byandrev.habito.ui.components.CustomButton
import dev.byandrev.habito.ui.theme.HabitoTheme
import dev.byandrev.habito.viewmodel.HabitViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewHabitScreen(
    viewModel: HabitViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scope = rememberCoroutineScope()

    var isDialogTimeOpen by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    val frequency = remember { mutableStateListOf(false, false, false, false, false, false, false) }
    val namesDaysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    Surface(
        modifier = Modifier.padding(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text("Habit name") }
            )

            Text(
                modifier = Modifier.padding(vertical = HabitoTheme.dimens.paddingMedium),
                text = "Frequency",
                fontWeight = FontWeight.Bold
            )

            Column (
                verticalArrangement = Arrangement.spacedBy(HabitoTheme.dimens.paddingNormal),
                modifier = Modifier.padding(bottom = HabitoTheme.dimens.paddingLarge)
            ) {
                CustomButton(
                    onTap = {},
                    text = "Daily",
                    textColor = MaterialTheme.colorScheme.surface,
                    buttonColor = MaterialTheme.colorScheme.primary,
                )

                CustomButton(
                    onTap = {},
                    text = "Specific days",
                    textColor = MaterialTheme.colorScheme.primary,
                    buttonColor = MaterialTheme.colorScheme.surfaceDim,
                )
            }

            Text(
                modifier = Modifier.padding(bottom = HabitoTheme.dimens.paddingMedium),
                text = "Reminder",
                fontWeight = FontWeight.Bold
            )

            Column (
                modifier = Modifier.clickable {
                    isDialogTimeOpen = true
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HabitoTheme.dimens.buttonHeightNormal)
                        .clip(RoundedCornerShape(HabitoTheme.dimens.roundedShapeNormal))
                        .background(MaterialTheme.colorScheme.surfaceDim)
                        .padding(HabitoTheme.dimens.paddingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Time",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "" + timePickerState.hour + ":" + timePickerState.minute,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            if (isDialogTimeOpen) {
                AlertDialog(
                    text = {
                        TimePicker(
                            state = timePickerState,

                            )
                    },
                    onDismissRequest = {
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                isDialogTimeOpen = false
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                isDialogTimeOpen = false
                            }
                        ) {
                            Text("Dismiss")
                        }
                    }
                )
            }

            Column (
                modifier = Modifier.padding(top = HabitoTheme.dimens.paddingNormal)
            ) {
                CustomButton(
                    onTap = {
                        viewModel.addHabit(Habit(
                            name = name
                        ))
                    },
                    text = "Save",
                    textColor = MaterialTheme.colorScheme.surface,
                    buttonColor = MaterialTheme.colorScheme.primary,
                )
            }

        }
    }
}
