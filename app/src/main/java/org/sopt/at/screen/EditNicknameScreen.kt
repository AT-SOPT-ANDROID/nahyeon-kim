package org.sopt.at.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.component.NoRippleInteractionSource
import org.sopt.at.ui.theme.TivingTheme
import org.sopt.at.viewmodel.MyViewModel

@Composable
fun EditNicknameScreen(
    userId: Long,
    onNicknameChanged: () -> Unit,
    viewModel: MyViewModel = MyViewModel()
) {
    var nickname by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val isInputValid = nickname.trim().isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("닉네임 변경", color = Color.White, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("새 닉네임", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.DarkGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                viewModel.changeNickname(userId, nickname.trim()) { success, msg ->
                    message = msg
                    if (success) onNicknameChanged()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            enabled = isInputValid,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isInputValid) TivingTheme.colors.brandRed else TivingTheme.colors.gray03,
                contentColor = TivingTheme.colors.basicWhite
            ),
            border = BorderStroke(1.dp, TivingTheme.colors.gray04),
            interactionSource = NoRippleInteractionSource
        ) {
            Text("변경하기", color = TivingTheme.colors.basicWhite, style = TivingTheme.typography.button)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (message.isNotBlank()) {
            Text(text = message, color = Color.LightGray)
        }
    }
}
