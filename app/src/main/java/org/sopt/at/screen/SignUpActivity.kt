package org.sopt.at.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.component.BackButton
import org.sopt.at.component.CommonTextField
import org.sopt.at.component.NoRippleInteractionSource
import org.sopt.at.component.PasswordTextField
import org.sopt.at.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignUpScreen(onSignUpSuccess: () -> Unit, authViewModel: AuthViewModel) {
    var step by remember { mutableStateOf(1) }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BackButton(
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (step == 1) {
            Text("아이디를 입력해주세요.", fontSize = 20.sp, color = Color.White)

            Spacer(Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                CommonTextField(
                    value = id, onValueChange = { id = it }, modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    "영문 소문자 또는 영문 소문자, 숫자 조합 6~12 자리",
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = {
                    if (id.matches(Regex("^[a-z0-9]{6,12}$"))) {
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
                    containerColor = Color.Black, contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.DarkGray),
                interactionSource = NoRippleInteractionSource
            ) {
                Text("다음", color = Color.LightGray)
            }
        } else {
            Text("비밀번호를 입력해주세요.", fontSize = 20.sp, color = Color.White)

            Spacer(Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    "영문, 숫자, 특수문자(~!@#\$%^&*) 조합 8~15자리",
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = {
                    if (password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#\$%^&*])[A-Za-z\\d~!@#\$%^&*]{8,15}\$"))) {
                        authViewModel.registeredId.value = id
                        authViewModel.registeredPassword.value = password
                        Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                        onSignUpSuccess()
                    } else {
                        Toast.makeText(context, "비밀번호 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Black, contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.DarkGray),
                interactionSource = NoRippleInteractionSource
            ) {
                Text("완료", color = Color.LightGray)
            }

        }
    }
}
