package org.sopt.at.viewmodel

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import org.sopt.at.screen.SignUpScreen

class AuthViewModel : ViewModel() {

    var registeredId = mutableStateOf("")
    var registeredPassword = mutableStateOf("")

    fun isValidId(id: String): Boolean {
        return id.matches(Regex("^[a-z0-9]{6,12}$"))
    }

    fun isValidPassword(password: String): Boolean {
        return password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#\$%^&*])[A-Za-z\\d~!@#\$%^&*]{8,15}\$"))
    }

    fun registerUser(id: String, password: String): Boolean {
        registeredId.value = id
        registeredPassword.value = password
        return true
    }
}
