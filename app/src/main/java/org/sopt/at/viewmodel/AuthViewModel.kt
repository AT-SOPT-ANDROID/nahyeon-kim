package org.sopt.at.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val _registeredId: MutableStateFlow<String> = MutableStateFlow("")
    val registeredId: StateFlow<String> = _registeredId.asStateFlow()

    var registeredPassword = mutableStateOf("")

    fun isValidId(id: String): Boolean {
        return id.matches(Regex("^[a-z0-9]{6,12}$"))
    }

    fun isValidPassword(password: String): Boolean {
        return password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#\$%^&*])[A-Za-z\\d~!@#\$%^&*]{8,15}\$"))
    }

    fun registerUser(id: String, password: String): Boolean {
        if (_registeredId.value != id || registeredPassword.value != password) {
            _registeredId.value = id
            registeredPassword.value = password
            return true
        }
        return false
    }
}
