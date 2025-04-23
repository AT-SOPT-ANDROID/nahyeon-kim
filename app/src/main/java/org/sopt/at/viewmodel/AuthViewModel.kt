package org.sopt.at.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var registeredId = mutableStateOf<String>("")
    var registeredPassword = mutableStateOf<String>("")
}

