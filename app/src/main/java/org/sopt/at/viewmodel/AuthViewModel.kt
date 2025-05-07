package org.sopt.at.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.data.RequestSignUpDto
import org.sopt.at.data.ResponseSignUpDto
import org.sopt.at.data.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {

    private val _registeredId: MutableStateFlow<String> = MutableStateFlow("")
    val registeredId: StateFlow<String> = _registeredId.asStateFlow()

    var registeredPassword = mutableStateOf("")

    fun isValidId(id: String): Boolean {
        return id.matches(Regex("^[A-Za-z0-9]{8,20}$"))
    }

    fun isValidPassword(password: String): Boolean {
        return password.matches(Regex("^[A-Za-z0-9]{8,20}$"))
    }

    fun isValidNickname(nickname: String): Boolean {
        return nickname.matches(Regex("^[가-힣a-zA-Z0-9]{1,20}$"))
    }

    fun registerUser(id: String, password: String, nickname: String, onResult: (Boolean, String) -> Unit) {
        val request = RequestSignUpDto(id, password, nickname)

        ServicePool.userService.signUp(request).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                val body = response.body()
                if (response.isSuccessful && body?.success == true) {
                    _registeredId.value = id
                    registeredPassword.value = password
                    onResult(true, "회원가입 성공!")
                } else {
                    onResult(false, body?.message ?: "오류 발생")
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                onResult(false, "네트워크 오류: ${t.message}")
            }
        })
    }
}