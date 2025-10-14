package dev.byandrev.habito.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.ui.components.CustomButton
import dev.byandrev.habito.ui.theme.HabitoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewHabitScreen() {
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    val frequency = remember { mutableStateListOf(false, false, false, false, false, false, false) }
    val namesDaysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

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
                modifier = Modifier.padding(top = HabitoTheme.dimens.paddingMedium),
                text = "Frequency",
                fontWeight = FontWeight.Bold
            )

            Column (
                modifier = Modifier.fillMaxWidth(),
            ) {
                frequency.forEachIndexed { index, isChecked ->
                   Row (
                       verticalAlignment = Alignment.CenterVertically,
                   ){
                       Checkbox(
                           checked = isChecked,
                           onCheckedChange = {
                               frequency[index] = it

                               Log.d("Checkbox", "Day ${index + 1} checked: $it")
                           }
                       )
                       Text(text = namesDaysOfWeek[index])
                   }
               }
            }

            CustomButton(
                onTap = {},
                text = "Save",
                textColor = MaterialTheme.colorScheme.surface,
                buttonColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
