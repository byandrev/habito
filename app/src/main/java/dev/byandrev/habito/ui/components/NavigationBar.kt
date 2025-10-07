package dev.byandrev.habito.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.byandrev.habito.ui.screens.HomeScreen
import dev.byandrev.habito.ui.screens.SettingsScreen
import dev.byandrev.habito.ui.screens.TasksScreen

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("habits", "Habits", Icons.Default.DateRange, "Habits"),
    TASKS("tasks", "Tasks", Icons.Default.List, "Tasks"),
    SETTINGS("settings", "Settings", Icons.Default.Settings, "Settings"),
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview()
@Composable
fun NavigationBar() {
    val navController = rememberNavController()
    val startDestination = Destination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        topBar = {
            Header()
        },
        bottomBar = {
            Column() {
                HorizontalDivider(
                    modifier = Modifier.height(1.dp).fillMaxWidth(),
                    thickness = DividerDefaults.Thickness, color = MaterialTheme.colorScheme.outline
                )

                NavigationBar(
                    windowInsets = NavigationBarDefaults.windowInsets,
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    Destination.entries.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestination == index,
                            onClick = {
                                navController.navigate(route = destination.route)
                                selectedDestination = index
                            },
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDescription
                                )
                            },
                            label = { Text(destination.label) },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                selectedIconColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        }
    ) {
        innerPadding ->

        NavHost(navController = navController, startDestination = Destination.HOME.route, modifier = Modifier.padding(innerPadding)) {
        composable(route = Destination.HOME.route) {
            HomeScreen()
        }
        composable(route = Destination.TASKS.route) {
            TasksScreen()
        }
        composable(route = Destination.SETTINGS.route) {
            SettingsScreen()
        }
    }
    }
}