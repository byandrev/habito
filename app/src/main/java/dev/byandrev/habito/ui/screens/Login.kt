package dev.byandrev.habito.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.byandrev.habito.ui.components.CustomButton
import dev.byandrev.habito.ui.components.Logo
import dev.byandrev.habito.utils.isValidPassword
import dev.byandrev.habito.viewmodel.AuthViewModel

@Composable
fun LoginScreen(authViewModel: AuthViewModel = viewModel()) {
    val inputName = remember { mutableStateOf("") }
    val inputPassword = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    val icon = if (passwordVisible)
        Icons.Outlined.Visibility
    else
        Icons.Filled.VisibilityOff

    Surface(
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth().align(Alignment.CenterHorizontally)
                    .padding(vertical = 20.dp)
            ) {
                Logo()
            }

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
                onValueChange = { value ->
                    if (isValidPassword(value) || (inputPassword.value.isNotEmpty() && value === "")) {
                        inputPassword.value = value
                    }
                },
                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = description)
                    }
                },
            )

            CustomButton(
                text = "Login",
                textColor = MaterialTheme.colorScheme.surface,
                buttonColor = MaterialTheme.colorScheme.primary,
                onTap = { authViewModel.signIn(inputName.value, inputPassword.value) }
            )
        }
    }
}