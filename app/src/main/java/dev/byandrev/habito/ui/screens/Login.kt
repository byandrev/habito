package dev.byandrev.habito.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.byandrev.habito.viewmodel.AuthViewModel

@Composable
fun LoginScreen(authViewModel: AuthViewModel = viewModel()) {
    val inputName = remember { mutableStateOf("") }
    val inputPassword = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.padding(20.dp)
    ) {
        Column {
            TextField(
                value = inputName.value,
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                label = {
                    Text("Email")
                },
                onValueChange = { value -> inputName.value = value }
            )

            TextField(
                value = inputPassword.value,
                modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth(),
                label = {
                    Text("Password")
                },
                onValueChange = { value -> inputPassword.value = value }
            )

            Button(
                onClick = { authViewModel.signIn(inputName.value, inputPassword.value) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }
        }
    }
}