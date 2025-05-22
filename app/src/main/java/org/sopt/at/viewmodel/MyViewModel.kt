package org.sopt.at.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.data.api.ServicePool.userService
import org.sopt.at.data.model.RequestModNicknameDto

class MyViewModel : ViewModel() {

    fun changeNickname(
        useId: Long,
        newNickname: String,
        onResult: (Boolean, String) -> Unit
    ) = viewModelScope.launch {
        val request = RequestModNicknameDto(newNickname)
        runCatching {
            userService.changeNickname(useId, request)
        }.onSuccess { response ->
            if (response.success) {
                onResult(true, "닉네임 변경 성공!")
            } else {
                onResult(false, response.message ?: "닉네임 변경 실패")
            }
        }.onFailure { throwable ->
            onResult(false, "네트워크 오류: ${throwable.message}")
        }
    }
}


