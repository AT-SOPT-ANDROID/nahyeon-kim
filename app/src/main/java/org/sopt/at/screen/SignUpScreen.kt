package org.sopt.at.screen

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.sopt.at.component.BackButton
import org.sopt.at.component.CommonTextField
import org.sopt.at.component.NoRippleInteractionSource
import org.sopt.at.component.PasswordTextField
import org.sopt.at.ui.theme.TivingTheme
import org.sopt.at.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(onSignUpSuccess: () -> Unit, authViewModel: AuthViewModel) {
    var step by remember { mutableIntStateOf(1) }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TivingTheme.colors.basicBlack)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButton(modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = when (step) {
                1 -> "아이디를 입력해주세요."
                2 -> "비밀번호를 입력해주세요."
                else -> "닉네임을 입력해주세요."
            },
            color = TivingTheme.colors.basicWhite,
            style = TivingTheme.typography.title,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        when (step) {
            1 -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    CommonTextField(
                        value = id,
                        onValueChange = { id = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "아이디는 대문자/소문자/숫자만 사용 가능하며 8자 ~ 20자 이어야 합니다.",
                        color = TivingTheme.colors.gray02,
                        style = TivingTheme.typography.caption,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
            2 -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    PasswordTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "비밀번호는 대문자/소문자/숫자만 사용 가능하며 8자 ~ 20자 이어야 합니다.",
                        color = TivingTheme.colors.gray02,
                        style = TivingTheme.typography.caption,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
            3 -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    CommonTextField(
                        value = nickname,
                        onValueChange = { nickname = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "닉네임은 한글/영어/숫자만 사용 가능하며 1자 ~ 20자 이어야 합니다.",
                        color = TivingTheme.colors.gray02,
                        style = TivingTheme.typography.caption,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val isStepValid = when (step) {
            1 -> id.isNotBlank()
            2 -> password.isNotBlank()
            3 -> nickname.isNotBlank()
            else -> false
        }

        OutlinedButton(
            onClick = {
                when (step) {
                    1 -> {
                        if (authViewModel.isValidId(id)) step = 2
                        else Toast.makeText(context, "아이디 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        if (authViewModel.isValidPassword(password)) step = 3
                        else Toast.makeText(context, "비밀번호 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        if (authViewModel.isValidNickname(nickname)) {
                            authViewModel.registerUser(id, password, nickname) { success, message ->
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                if (success) onSignUpSuccess()
                            }
                        } else {
                            Toast.makeText(context, "닉네임 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (isStepValid) TivingTheme.colors.brandRed else TivingTheme.colors.gray03,
                contentColor = TivingTheme.colors.basicWhite
            ),
            border = BorderStroke(1.dp, TivingTheme.colors.gray04),
            interactionSource = NoRippleInteractionSource
        ) {
            Text(
                text = if (step < 3) "다음" else "완료",
                style = TivingTheme.typography.button,
                color = TivingTheme.colors.basicWhite
            )
        }
    }
}
