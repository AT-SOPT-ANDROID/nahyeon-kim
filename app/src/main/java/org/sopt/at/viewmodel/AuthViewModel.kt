package org.sopt.at.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.api.ServicePool.userService
import org.sopt.at.data.model.RequestSignInDto
import org.sopt.at.data.model.RequestSignUpDto

class AuthViewModel : ViewModel() {

    private val _registeredId: MutableStateFlow<String> = MutableStateFlow("")
    private val _nickname: MutableStateFlow<String> = MutableStateFlow("")
    private val _userId = MutableStateFlow<Long?>(null)
    private val _id = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    val id: StateFlow<String> = _id.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val nickname: StateFlow<String> = _nickname.asStateFlow()


    var registeredPassword = mutableStateOf("")

    fun updateId(newId: String) {
        _id.value = newId
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateNickname(newNickname: String) {
        _nickname.value = newNickname
    }

    fun isValidId(id: String): Boolean {
        return id.matches(Regex("^[A-Za-z0-9]{8,20}$"))
    }

    fun isValidPassword(password: String): Boolean {
        return password.matches(Regex("^[A-Za-z0-9]{8,20}$"))
    }

    fun isValidNickname(nickname: String): Boolean {
        return nickname.matches(Regex("^[가-힣a-zA-Z0-9]{1,20}$"))
    }

    fun setUserId(id: Long) {
        _userId.value = id
    }

    fun registerUser(
        id: String,
        password: String,
        nickname: String,
        onResult: (Boolean, String) -> Unit
    ) = viewModelScope.launch {
        val request = RequestSignUpDto(id, password, nickname)
        runCatching {
            userService.signUp(request)
        }.onSuccess {
            _registeredId.value = id
            registeredPassword.value = password
            onResult(true, "회원가입 성공!")
        }.onFailure {
            onResult(false, "오류 발생")
        }
    }

    fun loginUser(
        id: String,
        password: String,
        onResult: (Boolean, String, Long?) -> Unit
    ) = viewModelScope.launch {
        val request = RequestSignInDto(id, password)
        runCatching {
            userService.signIn(request)
        }.onSuccess { response ->
            if (response.success && response.data != null) {
                val userId = response.data.userId
                _userId.value = userId
                onResult(true, "로그인 성공!", userId)
            } else {
                onResult(false, response.message ?: "로그인 실패", null)
            }
        }.onFailure {
            onResult(false, "네트워크 오류: ${it.message}", null)
        }
    }


    fun fetchNickname(userId: Long) = viewModelScope.launch {
        runCatching {
            userService.getNickname(userId)
        }.onSuccess { response ->
            if (response.success && response.data != null) {
                _nickname.value = response.data.nickname
            } else {
                _nickname.value = "닉네임 조회 실패"
            }
        }.onFailure {
            _nickname.value = "네트워크 오류"
        }

    }

    fun clearInputFields() {
        _id.value = ""
        _password.value = ""
        _nickname.value = ""
    }

}