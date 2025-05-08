package org.sopt.at.viewmodel

import androidx.lifecycle.ViewModel
import org.sopt.at.data.model.RequestModNicknameDto
import org.sopt.at.data.model.ResponseModNicknameDto
import org.sopt.at.data.api.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    fun changeNickname(
        userId: Long,
        newNickname: String,
        onResult: (Boolean, String) -> Unit
    ) {
        val request = RequestModNicknameDto(newNickname)

        ServicePool.userService.changeNickname(userId, request)
            .enqueue(object : Callback<ResponseModNicknameDto> {
                override fun onResponse(
                    call: Call<ResponseModNicknameDto>,
                    response: Response<ResponseModNicknameDto>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body?.success == true) {
                        onResult(true, "닉네임 변경 성공!")
                    } else {
                        onResult(false, body?.message ?: "닉네임 변경 실패")
                    }
                }

                override fun onFailure(call: Call<ResponseModNicknameDto>, t: Throwable) {
                    onResult(false, "네트워크 오류: ${t.message}")
                }
            })
    }
}
