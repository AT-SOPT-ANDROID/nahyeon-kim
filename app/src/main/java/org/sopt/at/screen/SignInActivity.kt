package org.sopt.at.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.MyActivity
import org.sopt.at.component.BackButton
import org.sopt.at.component.CommonTextField
import org.sopt.at.component.NoRippleInteractionSource
import org.sopt.at.component.PasswordTextField

var registeredId: String? = null
var registeredPassword: String? = null

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = this
            SignInScreen(onSignUpClick = {
                startActivity(Intent(this, SignUpActivity::class.java))
            }, onLoginSuccess = {
                val intent = Intent(context, MyActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            })
        }
    }
}


@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, containerColor = Color.Black
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

            Button(
                onClick = {
                    if (id == registeredId && password == registeredPassword) {
                        onLoginSuccess()
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("ID 또는 비밀번호가 일치하지 않습니다.")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray, contentColor = Color.Black
                ),
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(onSignUpClick = {}, onLoginSuccess = {})
}
