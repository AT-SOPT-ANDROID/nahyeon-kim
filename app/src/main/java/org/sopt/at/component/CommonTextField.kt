package org.sopt.at.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.graphics.Color

@Composable
fun CommonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("아이디", color = Color.White) },
        singleLine = true,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.DarkGray,
            focusedTextColor = Color.White,      // 입력 텍스트 색상
            unfocusedTextColor = Color.White,    // 입력 텍스트 색상
            cursorColor = Color.White            // 커서 색상
        )
    )
}

