package dev.byandrev.habito

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import dev.byandrev.habito.ui.components.NavigationBar
import dev.byandrev.habito.ui.screens.LoadingScreen
import dev.byandrev.habito.ui.screens.LoginScreen
import dev.byandrev.habito.ui.theme.HabitoTheme
import dev.byandrev.habito.viewmodel.AuthState
import dev.byandrev.habito.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitoTheme(dynamicColor = false) {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(viewModel: AuthViewModel = viewModel()) {
    val authState by viewModel.authState.collectAsState()

    when (authState) {
        is AuthState.Success -> {
            NavigationBar()
        }

        is AuthState.Error -> {
            LoginScreen()
        }

        is AuthState.Loading -> {
            LoadingScreen()
        }

        is AuthState.Idle -> {
            LoginScreen()
        }
    }
}
