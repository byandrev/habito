package dev.byandrev.habito.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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

    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("📕", "🍎", "🏀", "💵", "🛒", "🏢")


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
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(bottom = HabitoTheme.dimens.paddingMedium),
                value = name,
                onValueChange = { name = it },
                label = { Text("Habit name") }
            )

            Text(
                modifier = Modifier.padding(bottom = HabitoTheme.dimens.paddingNormal),
                text = "Reminder",
                fontWeight = FontWeight.Bold
            )

            Column (
                modifier = Modifier.clickable {
                    isDialogTimeOpen = true
                }
                    .padding(bottom = HabitoTheme.dimens.paddingNormal)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HabitoTheme.dimens.buttonHeightNormal)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(HabitoTheme.dimens.roundedShapeNormal)
                        )
                        .padding(HabitoTheme.dimens.paddingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Time",
                    )
                    Text(
                        text = "" + timePickerState.hour + ":" + timePickerState.minute,
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

            Text(
                modifier = Modifier.padding(bottom = HabitoTheme.dimens.paddingNormal),
                text = "Customize",
                fontWeight = FontWeight.Bold
            )

            SingleChoiceSegmentedButtonRow (
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = options.size
                        ),
                        onClick = { selectedIndex = index },
                        selected = index == selectedIndex,
                        label = { Text(label) }
                    )
                }
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
