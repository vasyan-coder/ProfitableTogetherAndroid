package com.profitable.profit.uikit.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme

/**
 * A composable function representing a text field styled according to the Profit theme.
 *
 * This text field is typically used for various inputs within the application.
 *
 * @param modifier Optional modifier for customizing the text field's layout and appearance.
 * @param value The current value of the text field.
 * @param onValueChange Callback function to be invoked when the value of the text field changes.
 * @param placeholder The placeholder text to be displayed when the text field is empty.
 * @param imageVector An optional vector image to be displayed at the end of the text field.
 * @param contentDescription The content description for the image vector.
 * @param keyboardActions Keyboard actions for handling keyboard interactions.
 * @param keyboardOptions Keyboard options for configuring the behavior of the keyboard.
 */
@Composable
fun ProfitTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    imageVector: ImageVector? = null,
    contentDescription: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var isFocused by rememberSaveable {
        mutableStateOf(false)
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = ProfitTheme.typography.bodyLarge,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        modifier = modifier
            .onFocusChanged { isFocused = it.isFocused },
    ) { innerTextField ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = ProfitTheme.colorScheme.surfaceContainerHighest,
                    shape = RoundedCornerShape(40.dp),
                )
                .border(
                    width = 1.dp,
                    color = ProfitTheme.colorScheme.primary,
                    shape = RoundedCornerShape(40.dp),
                )
                .padding(horizontal = 32.dp, vertical = 16.dp),
        ) {
            if (!isFocused && value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = ProfitTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.weight(0.9f),
                )
            } else {
                Box(modifier = Modifier.weight(0.9f)) {
                    innerTextField()
                }
            }
            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    tint = ProfitTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(24.dp)
                        .weight(0.1f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfitTextFieldPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitTextField(
            value = "",
            onValueChange = { /* no-op */ },
            placeholder = "E-mail",
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_email),
        )
    }
}
