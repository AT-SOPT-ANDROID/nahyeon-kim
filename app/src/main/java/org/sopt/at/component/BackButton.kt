package org.sopt.at.component

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.at.R

@Composable
fun BackButton(
    modifier: Modifier = Modifier, onClick: (() -> Unit)? = null
) {
    val activity = LocalActivity.current as? ComponentActivity

    Box(
        modifier = modifier
            .padding(top = 25.dp)
            .clickable(
                interactionSource = NoRippleInteractionSource, indication = null
            ) {
                onClick?.invoke() ?: activity?.finish()
            }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "뒤로가기",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}
