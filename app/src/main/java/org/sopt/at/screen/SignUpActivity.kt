package org.sopt.at.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.component.CommonTextField
import org.sopt.at.component.PasswordTextField

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen(
                onSignUpSuccess = { finish() }
            )
        }
    }
}

@Composable
fun SignUpScreen(onSignUpSuccess: () -> Unit) {
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
        if (step == 1) {
            Text("아이디를 입력해주세요.", fontSize = 20.sp, color = Color.White)

            Spacer(Modifier.height(20.dp))

            CommonTextField(
                value = id,
                onValueChange = { id = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Text("영문 소문자 또는 영문 소문자, 숫자 조합 6~12 자리", color = Color.White)

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
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.DarkGray)
            ) {
                Text("다음", color = Color.LightGray)
            }
        } else {
            Text("비밀번호를 입력해주세요.", fontSize = 20.sp, color = Color.White)

            Spacer(Modifier.height(20.dp))

            PasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Text("영문, 숫자, 특수문자(~!@#\$%^&*) 조합 8~15자리", color = Color.White)

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = {
                    if (password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#\$%^&*])[A-Za-z\\d~!@#\$%^&*]{8,15}\$"))) {
                        registeredId = id
                        registeredPassword = password
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
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Text("완료", color = Color.DarkGray)
            }
        }
    }
}
