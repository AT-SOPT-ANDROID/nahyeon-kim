package org.sopt.at.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.at.data.api.ServicePool.userService

class SearchViewModel : ViewModel() {
    private val _searchResult = MutableStateFlow<List<String>>(emptyList())
    val searchResult: StateFlow<List<String>> = _searchResult.asStateFlow()

    fun searchNickname(keyword: String) = viewModelScope.launch {
        val requestKeyword = if (keyword.isBlank()) null else keyword

        runCatching {
            userService.searchNickname(requestKeyword)
        }.onSuccess { response ->
            _searchResult.value = response.data.nicknameList
        }.onFailure {
            _searchResult.value = emptyList()
        }
    }

}