package org.sopt.at.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.sopt.at.component.BackButton
import org.sopt.at.component.CommonTextField
import org.sopt.at.component.NoRippleInteractionSource
import org.sopt.at.component.PasswordTextField
import org.sopt.at.ui.theme.TivingTheme
import org.sopt.at.viewmodel.AuthViewModel

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: (Long?) -> Unit,
    authViewModel: AuthViewModel
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val isInputValid = id.isNotBlank() && password.isNotBlank()
    fun handleLogin() {
        if (!isInputValid) {
            scope.launch {
                snackbarHostState.showSnackbar("아이디와 비밀번호를 입력해 주세요.")
            }
            return
        }

        authViewModel.loginUser(id, password) { isSuccess, message, userId ->
            scope.launch {
                if (isSuccess) {
                    onLoginSuccess(userId)
                } else {
                    snackbarHostState.showSnackbar(message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = TivingTheme.colors.basicBlack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues)
        ) {
            BackButton(modifier = Modifier.align(Alignment.Start))
            Spacer(Modifier.height(32.dp))

            Text(
                text = "TVING ID 로그인",
                style = TivingTheme.typography.title,
                color = TivingTheme.colors.basicWhite,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            CommonTextField(
                value = id,
                onValueChange = { id = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            PasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))

            OutlinedButton(
                onClick = { handleLogin() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isInputValid) TivingTheme.colors.brandRed else TivingTheme.colors.gray03,
                    contentColor = TivingTheme.colors.basicWhite
                ),
                border = BorderStroke(1.dp, TivingTheme.colors.gray04),
                interactionSource = NoRippleInteractionSource
            ) {
                Text("로그인하기", style = TivingTheme.typography.button)
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "아이디 찾기",
                    color = TivingTheme.colors.gray02,
                    style = TivingTheme.typography.body
                )
                VerticalDivider(
                    modifier = Modifier.height(22.dp),
                    color = TivingTheme.colors.gray04,
                    thickness = 1.5.dp
                )
                Text(
                    "비밀번호 찾기",
                    color = TivingTheme.colors.gray02,
                    style = TivingTheme.typography.body
                )
                VerticalDivider(
                    modifier = Modifier.height(22.dp),
                    color = TivingTheme.colors.gray04,
                    thickness = 1.5.dp
                )
                Text(
                    text = "회원가입",
                    color = TivingTheme.colors.gray02,
                    style = TivingTheme.typography.body,
                    modifier = Modifier.clickable(
                        interactionSource = NoRippleInteractionSource, indication = null
                    ) { onSignUpClick() }
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                buildAnnotatedString {
                    append("이 사이트는 Google reCAPTCHA로 보호되며,\nGoogle ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("개인정보 처리방침")
                    }
                    append("과 ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("서비스 약관")
                    }
                    append("이 적용됩니다.")
                },
                color = TivingTheme.colors.gray04,
                textAlign = TextAlign.Center,
                style = TivingTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
