package dev.byandrev.habito.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userId: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    val currentUser = auth.currentUser
    private val _isLogged = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser

        if (user != null) {
            _isLogged.value = true

            if (_authState.value !is AuthState.Success) {
                _authState.value = AuthState.Success(user.uid)
            }
        } else {
            _isLogged.value = false
            _authState.value = AuthState.Idle
            Log.d("AUTH", "SignOut successful and state updated.")
        }
    }

    init {
        auth.addAuthStateListener(authStateListener)
    }

    override fun onCleared() {
        super.onCleared()
        auth.removeAuthStateListener(authStateListener)
    }

    fun signIn(email: String, password: String) {
        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    _authState.value = AuthState.Error(
                        task.exception?.message ?: "Error desconocido"
                    )
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }
}