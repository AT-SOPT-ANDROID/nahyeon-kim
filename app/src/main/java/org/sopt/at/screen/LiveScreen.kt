package org.sopt.at.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.sopt.at.ui.theme.TivingTheme

@Composable
fun LiveScreen() {
    Text(text = "LIVE", color = TivingTheme.colors.basicWhite, style = TivingTheme.typography.body)
}
