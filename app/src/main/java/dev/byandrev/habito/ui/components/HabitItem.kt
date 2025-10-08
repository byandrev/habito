package dev.byandrev.habito.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.data.HabitCompletion
import dev.byandrev.habito.data.calculateWeeklyCompletionPercentage
import dev.byandrev.habito.data.isHabitCompletedToday
import dev.byandrev.habito.data.isHabitDueToday
import dev.byandrev.habito.ui.theme.HabitoTheme
import java.time.LocalDate

@Composable
fun HabitItem(
    habit: Habit,
    completions: List<HabitCompletion>,
    onComplete: (HabitCompletion) -> Unit,
    currentDate: LocalDate = LocalDate.now()
) {
    val isDue = isHabitDueToday(habit, currentDate)
    val isCompleted = isHabitCompletedToday(completions, habit.id, currentDate)

    val weeklyPercentageFloat = calculateWeeklyCompletionPercentage(habit, completions, currentDate)
    val weeklyPercentageFormatted = String.format("%.0f", weeklyPercentageFloat)
    val progress = weeklyPercentageFloat / 100f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = HabitoTheme.dimens.paddingLarge, vertical = HabitoTheme.dimens.paddingNormal),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(HabitoTheme.dimens.spacerMedium)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .size(HabitoTheme.dimens.iconSizeMedium)
                    .clip(RoundedCornerShape(HabitoTheme.dimens.roundedShapeNormal))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Habit icon",
                    modifier = Modifier.padding(HabitoTheme.dimens.paddingNormal),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column {
                Text(
                    text = habit.name,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium
                )

                habit.description?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    modifier = Modifier.padding(top = HabitoTheme.dimens.spacerNormal),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(HabitoTheme.dimens.spacerNormal)
                ) {
                    LinearProgressIndicator(
                        progress = { if (weeklyPercentageFloat == 0f) 0f else progress },
                        modifier = Modifier.width(150.dp),
                        color = if (weeklyPercentageFloat <= 0.0F) Color.Transparent else MaterialTheme.colorScheme.primary,
                        strokeCap = StrokeCap.Round,
                        drawStopIndicator = {},
                        gapSize = 0.dp,
                    )

                    Text(
                        text = "$weeklyPercentageFormatted%",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.surfaceDim
                    )
                }
            }
        }

        IconButton(
            onClick = {
                val completion = HabitCompletion(habitId = habit.id, date = currentDate)
                onComplete(completion)
            },
            enabled = isDue,
            modifier = Modifier
                .size(HabitoTheme.dimens.iconSizeMedium)
                .clip(RoundedCornerShape(HabitoTheme.dimens.iconSizeMedium))
                .background(if (isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = "Check icon",
                modifier = Modifier.padding(HabitoTheme.dimens.paddingNormal),
                tint = if (isCompleted) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
            )
        }
    }
}