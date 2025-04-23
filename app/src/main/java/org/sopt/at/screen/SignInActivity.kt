package org.sopt.at.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.component.BackButton
import org.sopt.at.component.CommonTextField
import org.sopt.at.component.NoRippleInteractionSource
import org.sopt.at.component.PasswordTextField
import org.sopt.at.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues)
        ) {

            BackButton(
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "TVING ID 로그인",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            CommonTextField(
                value = id, onValueChange = { id = it }, modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            PasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            OutlinedButton(
                onClick = {
                    if (id == authViewModel.registeredId.value && password == authViewModel.registeredPassword.value) {
                        onLoginSuccess()
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("ID 또는 비밀번호가 일치하지 않습니다.")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.LightGray, contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.DarkGray),
                interactionSource = NoRippleInteractionSource
            ) {
                Text("로그인하기")
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("아이디 찾기", color = Color.LightGray, fontSize = 16.sp)

                VerticalDivider(
                    modifier = Modifier.height(22.dp), color = Color.DarkGray, thickness = 1.5.dp
                )

                Text("비밀번호 찾기", color = Color.LightGray, fontSize = 16.sp)

                VerticalDivider(
                    modifier = Modifier.height(22.dp), color = Color.DarkGray, thickness = 1.5.dp
                )

                Text(
                    text = "회원가입",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable(
                        interactionSource = NoRippleInteractionSource, indication = null
                    ) {
                        onSignUpClick()
                    })
            }
            Spacer(Modifier.height(16.dp))

            Text(
                text = buildAnnotatedString {
                    append("이 사이트는 Google reCAPTCHA로 보호되며,\n")
                    append("Google ")

                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("개인정보 처리방침")
                    }

                    append("과 ")

                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("서비스 약관")
                    }

                    append("이 적용됩니다.")
                },
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
    }
}
