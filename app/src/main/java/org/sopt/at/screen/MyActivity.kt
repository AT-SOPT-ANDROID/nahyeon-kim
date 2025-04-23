package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.component.BackButton
import org.sopt.at.component.NoRippleInteractionSource

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "my_screen") {
                composable("my_screen") {
                    MyScreen(
                        userId = "사용자",
                        onLogout = {
                            navController.navigate("sign_in_screen") {
                                popUpTo("my_screen") { inclusive = true }
                            }
                        })
                }


                composable("sign_in_screen") {
                    SignInScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen(
    userId: String, onLogout: () -> Unit
) {
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

        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .padding(end = 16.dp)
                )

                Column {
                    Text("프로필", color = Color.White, fontSize = 18.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(text = userId, color = Color.White, fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Black, contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.DarkGray),
            interactionSource = NoRippleInteractionSource
        ) {
            Text("로그아웃", color = Color.LightGray)
        }
    }
}

@Composable
fun SignInScreen() {
    Text(text = "Sign In Screen")
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
fun PreviewMyScreen() {
    MyScreen(
        userId = "preview_user", onLogout = {})
}
