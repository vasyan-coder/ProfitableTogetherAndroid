package com.profitable.profit.uikit.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profitable.profit.uikit.theme.ProfitTheme
import com.profitable.profit.uikit.theme.ProfitableTogetherAndroidTheme


/**
 * A composable function representing a text field for questionnaire inputs styled according to the Profit theme.
 *
 * This text field is typically used for capturing questionnaire responses.
 *
 * @param value The current value of the text field.
 * @param onValueChange Callback function to be invoked when the value of the text field changes.
 * @param modifier Optional modifier for customizing the text field's layout and appearance.
 * @param enabled Whether the text field is currently enabled for user input.
 */
@Composable
fun ProfitQuestionnaireTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember {
        FocusRequester()
    }
    /*TODO("Make bullet list")*/
    Column {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.padding(16.dp),
            enabled = enabled,
            textStyle = ProfitTheme.typography.titleMedium,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions.Default,
            maxLines = 20,
            singleLine = false,
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .background(ProfitTheme.colorScheme.primary)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(40.dp),
                        )
                        .padding(
                            horizontal = 24.dp,
                            vertical = 24.dp,
                        )
                        .focusRequester(focusRequester)
                ) {
                    innerTextField()
                }
            },
        )
    }
}

@Preview
@Composable
private fun ProfitQuestionnaireTextFieldPrev() {
    ProfitableTogetherAndroidTheme {
        ProfitQuestionnaireTextField(
            value = "123331321321123",
            onValueChange = {},
        )
    }
}

@Composable
fun BulletListTextField() {
    var textFieldValue by remember { mutableStateOf(TextFieldValue("12312\n123")) }

    val bulletListString = buildAnnotatedString {
        textFieldValue.text.lines().forEach { line ->
            append("\u2022 $line\n")  // \u2022 - это символ маркера
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = "Маркированный список:")

        BasicTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 8.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = bulletListString,
            style = LocalTextStyle.current.copy(textAlign = TextAlign.Start)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InteractiveBulletListPreview() {
    BulletListTextField()
}
