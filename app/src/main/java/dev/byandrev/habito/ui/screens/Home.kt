package dev.byandrev.habito.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.ui.components.HabitsList
import dev.byandrev.habito.ui.theme.HabitoTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen() {
    val today: LocalDate = LocalDate.now()

    Surface(
        modifier = Modifier.padding(0.dp)
    ) {
        Column {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${today.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM"))}",
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = HabitoTheme.dimens.paddingMedium),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { 50 / 100f },
                    modifier = Modifier.width(100.dp).height(100.dp),
                    strokeCap = StrokeCap.Round
                )

                Text(
                    text = "50%",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = HabitoTheme.dimens.paddingLarge),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Great job! You´re on track for today.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            HabitsList(today)
        }
    }
}