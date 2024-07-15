package com.profitable.profit.uikit.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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


// TODO ("Replace ProfitTextField")
/**
 * A composable function representing a test text field styled according to the Profit theme.
 *
 * This text field is used for testing purposes.
 *
 * @param value The current value of the text field.
 * @param onValueChange Callback function to be invoked when the value of the text field changes.
 * @param modifier Optional modifier for customizing the text field's layout and appearance.
 * @param enabled Whether the text field is currently enabled for user input.
 * @param placeholder The placeholder text to be displayed when the text field is empty.
 * @param trailingIcon An optional composable function to display an icon at the end of the text field.
 * @param keyboardActions Keyboard actions for handling keyboard interactions.
 * @param keyboardOptions Keyboard options for configuring the behavior of the keyboard.
 * @param singleLine Whether the text field should be limited to a single line.
 * @param maxLines The maximum number of lines the text field can have.
 * @param isVisible Whether the text should be displayed in plaintext or hidden (for password fields).
 */
@Composable
fun TestProfitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    isVisible: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember {
        FocusRequester()
    }
    Column {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            textStyle = ProfitTheme.typography.bodyLarge,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) {
                if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
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
                        .focusRequester(focusRequester),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 32.dp, vertical = 16.dp),
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = ProfitTheme.typography.bodyLarge,
                                color = Color.White,
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            innerTextField()
                        }
                    }
                    if (trailingIcon != null) {
                        trailingIcon()
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                }
            }
        )
    }
}


@Preview
@Composable
private fun TestProfitTextFieldPreview() {
    ProfitableTogetherAndroidTheme {
        TestProfitTextField(
            value = "",
            onValueChange = {},
            placeholder = "E-mail",
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_email),
                    contentDescription = "Email input",
                    tint = ProfitTheme.colorScheme.primary,
                )
            }
        )
    }
}

@Preview
@Composable
private fun TestProfitTextFieldInputPreview() {
    ProfitableTogetherAndroidTheme {
        TestProfitTextField(
            value = "example@gmail.com",
            onValueChange = {},
            placeholder = "E-mail",
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_email),
                    contentDescription = "Email input",
                    tint = ProfitTheme.colorScheme.primary,
                )
            }
        )
    }
}
