package dev.byandrev.habito.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

enum class Filters(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    ALL("all", "All", Icons.AutoMirrored.Filled.List, "All"),
    TODO("todo", "To Do", Icons.Default.DateRange, "To Do"),
    COMPLETED("completed", "Completed", Icons.Default.Check, "Completed"),
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabsTasks(onChangeTab: (tab: String) -> Unit) {
    val startDestination = Filters.ALL
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    PrimaryTabRow(selectedTabIndex = selectedDestination) {
        Filters.entries.forEachIndexed { index, destination ->
            Tab(
                selected = selectedDestination == index,
                onClick = {
                    onChangeTab(destination.route)
                    selectedDestination = index
                },
                text = {
                    Text(
                        text = destination.label,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}