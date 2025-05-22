package org.sopt.at.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.sopt.at.R
import org.sopt.at.ui.theme.TivingTheme

@Composable
fun PasswordTextField(
    value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("비밀번호",
            color = TivingTheme.colors.basicWhite,
            style =  TivingTheme.typography.caption) },
        singleLine = true,
        modifier = modifier,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                val icon = if (passwordVisible) R.drawable.ic_visible else R.drawable.ic_invisible
                val contentDesc = if (passwordVisible) "비밀번호 숨기기" else "비밀번호 보기"
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = contentDesc,
                    tint = TivingTheme.colors.basicWhite
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = TivingTheme.colors.gray05,
            unfocusedContainerColor = TivingTheme.colors.gray05,
            focusedBorderColor = TivingTheme.colors.basicWhite,
            unfocusedBorderColor = TivingTheme.colors.gray05,
            focusedTextColor = TivingTheme.colors.basicWhite,
            unfocusedTextColor = TivingTheme.colors.basicWhite,
            cursorColor = TivingTheme.colors.basicWhite
        )
    )
}
