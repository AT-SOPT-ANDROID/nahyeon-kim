package org.sopt.at.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.at.data.ResponseNicknameListDto
import org.sopt.at.data.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private val _searchResult = MutableStateFlow<List<String>>(emptyList())
    val searchResult: StateFlow<List<String>> = _searchResult.asStateFlow()

    fun searchNickname(keyword: String) {
        val requestKeyword = if (keyword.isBlank()) null else keyword

        ServicePool.userService.searchNickname(requestKeyword)
            .enqueue(object : Callback<ResponseNicknameListDto> {
                override fun onResponse(
                    call: Call<ResponseNicknameListDto>,
                    response: Response<ResponseNicknameListDto>
                ) {
                    val body = response.body()
                    if (response.isSuccessful && body?.success == true) {
                        _searchResult.value = body.data.nicknameList
                    } else {
                        _searchResult.value = emptyList()
                    }
                }
                override fun onFailure(call: Call<ResponseNicknameListDto>, t: Throwable) {
                    _searchResult.value = emptyList()
                }

            })
    }
}