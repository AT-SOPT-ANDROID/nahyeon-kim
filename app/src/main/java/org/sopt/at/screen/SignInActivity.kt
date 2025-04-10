package org.sopt.at.screen

import androidx.compose.ui.Alignment
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.MyActivity
import org.sopt.at.component.CommonTextField
import org.sopt.at.component.PasswordTextField


var registeredId: String? = null
var registeredPassword: String? = null

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignInScreen(
                onSignUpClick = {
                    startActivity(Intent(this, SignUpActivity::class.java))
                },
                onLoginSuccess = {
                    startActivity(Intent(this, MyActivity::class.java))
                },
                loginFailed = {
                    Toast.makeText(this, "로그인 실패: ID 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    loginFailed: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    /*LaunchedEffect(Unit) {
        registeredId?.let {
            Toast.makeText(context, "회원가입 성공! 입력한 정보로 로그인하세요.", Toast.LENGTH_SHORT).show()
        }
    }*/

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Text(
                "TVING ID 로그인",
                fontSize = 24.sp,
                color = Color.White,
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

            Button(
                onClick = {
                    if (id == registeredId && password == registeredPassword) {
                        onLoginSuccess()
                    } else {
                        loginFailed()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                )
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
                Text(
                    text = "아이디 찾기",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )

                VerticalDivider(
                    modifier = Modifier.height(22.dp),
                    color = Color.DarkGray,
                    thickness = 1.5.dp
                )

                Text(
                    text = "비밀번호 찾기",
                    color = Color.LightGray,
                    fontSize = 16.sp
                )

                VerticalDivider(
                    modifier = Modifier.height(22.dp),
                    color = Color.DarkGray,
                    thickness = 1.5.dp
                )

                Text(
                    text = "회원가입",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }
        }
    }