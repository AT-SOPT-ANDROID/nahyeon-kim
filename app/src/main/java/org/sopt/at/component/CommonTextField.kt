package org.sopt.at.component

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.at.ui.theme.TivingTheme

@Composable
fun CommonTextField(
    value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text ("아이디",
            color = TivingTheme.colors.basicWhite,
           style =  TivingTheme.typography.caption
            )
                      },
        singleLine = true,
        modifier = modifier,
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

