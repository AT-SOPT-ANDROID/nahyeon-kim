package org.sopt.at.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.at.component.CommonTextField
import org.sopt.at.ui.theme.TivingTheme
import org.sopt.at.viewmodel.SearchViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = remember { SearchViewModel() }) {
    var keyword by remember { mutableStateOf("") }
    val searchResult by viewModel.searchResult.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        CommonTextField(
            value = keyword,
            onValueChange = {
                keyword = it
                viewModel.searchNickname(keyword)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchResult.isEmpty()) {
            Text("검색 결과가 없습니다.",
                color = TivingTheme.colors.basicWhite,
                style = TivingTheme.typography.body)
        } else {
            searchResult.forEach { nickname ->
                Text(text = nickname,
                    color = TivingTheme.colors.basicWhite,
                    style = TivingTheme.typography.body)
            }
        }
    }
}
