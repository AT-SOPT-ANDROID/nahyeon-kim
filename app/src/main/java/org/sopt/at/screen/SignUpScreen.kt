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
    var step by remember { mutableStateOf(1) }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
            text = if (step == 1) "아이디를 입력해주세요." else "비밀번호를 입력해주세요.",
            color = TivingTheme.colors.basicWhite,
            style = TivingTheme.typography.title,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        if (step == 1) {
            Column(modifier = Modifier.fillMaxWidth()) {
                CommonTextField(
                    value = id, onValueChange = { id = it }, modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "영문 소문자 또는 영문 소문자+숫자 조합 6~12 자리",
                    color = TivingTheme.colors.gray02,
                    style = TivingTheme.typography.caption,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            val isIdNotBlank = id.isNotBlank()
            OutlinedButton(
                onClick = {
                    if (authViewModel.isValidId(id)) {
                        step = 2
                    } else {
                        Toast.makeText(context, "아이디 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isIdNotBlank) TivingTheme.colors.brandRed else TivingTheme.colors.gray03,
                    contentColor = TivingTheme.colors.basicWhite
                ),
                border = BorderStroke(1.dp, TivingTheme.colors.gray04),
                interactionSource = NoRippleInteractionSource
            ) {
                Text(
                    text = "다음",
                    style = TivingTheme.typography.button,
                    color = TivingTheme.colors.basicWhite
                )
            }
        } else {
            Column(modifier = Modifier.fillMaxWidth()) {
                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "영문, 숫자, 특수문자(~!@#\$%^&*) 조합 8~15자리",
                    color = TivingTheme.colors.gray02,
                    style = TivingTheme.typography.caption,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            val isPasswordNotBlank = password.isNotBlank()
            OutlinedButton(
                onClick = {
                    if (authViewModel.isValidPassword(password)) {
                        if (authViewModel.registerUser(id, password)) {
                            Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                            onSignUpSuccess()
                        }
                    } else {
                        Toast.makeText(context, "비밀번호 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isPasswordNotBlank) TivingTheme.colors.brandRed else TivingTheme.colors.gray03,
                    contentColor = TivingTheme.colors.basicWhite
                ),
                border = BorderStroke(1.dp, TivingTheme.colors.gray04),
                interactionSource = NoRippleInteractionSource
            ) {
                Text(
                    text = "완료",
                    style = TivingTheme.typography.button,
                    color = TivingTheme.colors.basicWhite
                )
            }
        }
    }
}