package dev.byandrev.habito.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.byandrev.habito.R

@Composable
fun Tasks() {
    Column(
        Modifier.padding(20.dp)
    ) {
        Text(
            text = stringResource(R.string.tasks)
        )

        LargeFloatingActionButton(
            onClick = { onClick() },
            shape = CircleShape,
            modifier = Modifier.padding()
        ) {
            Icon(Icons.Filled.Add, "Large floating action button")
        }
    }
}

fun onClick() {

}