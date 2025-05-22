package org.sopt.at.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.component.BackButton
import org.sopt.at.util.NoRippleInteractionSource
import org.sopt.at.ui.theme.TivingTheme


@Composable
fun MyScreen(
    userId: Long?,
    nickname: String,
    onLogout: () -> Unit,
    onClickEditNickname: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TivingTheme.colors.basicBlack)
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
                .background(TivingTheme.colors.gray05, RoundedCornerShape(12.dp))
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

                Column(modifier = Modifier.weight(1f)) {
                    Text("프로필", color = TivingTheme.colors.basicWhite, style = TivingTheme.typography.subTitle)
                    Spacer(Modifier.height(4.dp))
                    Text(text = nickname, color = TivingTheme.colors.basicWhite, style = TivingTheme.typography.body)
                }

                OutlinedButton(
                    onClick = onClickEditNickname,
                    border = BorderStroke(1.dp, color = TivingTheme.colors.gray01),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = TivingTheme.colors.basicWhite
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("설정", style = TivingTheme.typography.button)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = TivingTheme.colors.basicBlack, contentColor = TivingTheme.colors.basicWhite
            ),
            border = BorderStroke(1.dp, color = TivingTheme.colors.gray05),
            interactionSource = NoRippleInteractionSource
        ) {
            Text("로그아웃", color = TivingTheme.colors.gray01, style = TivingTheme.typography.button)
        }
    }
}
