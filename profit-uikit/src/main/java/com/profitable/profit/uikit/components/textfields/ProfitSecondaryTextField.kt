package com.profitable.profit.uikit.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.R
import com.profitable.profit.uikit.theme.Ochre
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme
import com.profitable.profit.uikit.theme.Silver


/**
 * A composable function representing a secondary text field styled according to the Profit theme.
 *
 * This text field is typically used for secondary inputs, such as search fields or additional information.
 *
 * @param value The current value of the text field.
 * @param onValueChange Callback function to be invoked when the value of the text field changes.
 * @param modifier Optional modifier for customizing the text field's layout and appearance.
 * @param enabled Whether the text field is currently enabled for user input.
 * @param placeholder The placeholder text to be displayed when the text field is empty.
 * @param leadingIcon An optional composable function to display an icon at the beginning of the text field.
 * @param keyboardOptions Keyboard options for configuring the behavior of the keyboard.
 * @param keyboardActions Keyboard actions for handling keyboard interactions.
 * @param borderColor The color of the text field's border.
 */
@Composable
fun ProfitSecondaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    borderColor: Color = ProfitTheme.colorScheme.primary,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember {
        FocusRequester()
    }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        textStyle = ProfitTheme.typography.bodyLarge,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
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
                        color = borderColor,
                        shape = RoundedCornerShape(40.dp),
                    )
                    .focusRequester(focusRequester)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                leadingIcon?.let {
                    it()
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = ProfitTheme.typography.bodyLarge,
                            color = Silver,
                        )
                    }
                    Box {
                        innerTextField()
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun ProfitSecondaryTextFieldPreview() {
    ProfitableTogetherAndroidTheme {
        ProfitSecondaryTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Find home...",
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_stroked),
                    contentDescription = null,
                    tint = ProfitTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp),
                )
            },
            borderColor = Ochre,
        )
    }
}
