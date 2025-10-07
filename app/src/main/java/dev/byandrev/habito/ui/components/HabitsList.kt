package dev.byandrev.habito.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.data.Habit
import dev.byandrev.habito.ui.theme.HabitoTheme

@Composable
fun HabitsList(habits: List<Habit>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        habits.forEach { habit ->
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
                        )

                        habit.description?.let {
                            Text(
                                text = it,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(HabitoTheme.dimens.iconSizeMedium)
                        .clip(RoundedCornerShape(HabitoTheme.dimens.iconSizeMedium))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = "Check icon",
                        modifier = Modifier.padding(HabitoTheme.dimens.paddingNormal),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.height(1.dp).fillMaxWidth(),
                thickness = DividerDefaults.Thickness, color = MaterialTheme.colorScheme.outline
            )
        }
    }
}