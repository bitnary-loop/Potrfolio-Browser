package com.kdbrian.portfolio_app.presentation.ui.composables


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = true,
    onValueChange: ((String) -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = null
        )
    },
    keyboardActions: KeyboardActions = KeyboardActions(onSearch = {}),
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    placeholder: @Composable () -> Unit = { Text(text = "Search Items") },
    shape: Shape = RoundedCornerShape(12.dp),
    trailingIcon: (@Composable () -> Unit)? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.LightGray,
        errorBorderColor = Color.LightGray,
        disabledBorderColor = Color.LightGray,
        unfocusedBorderColor = Color.LightGray,
        errorContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,

        ),
    visualTransformation: VisualTransformation= VisualTransformation.None
) {


    OutlinedTextField(
        enabled = enabled,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        value = value,
        onValueChange = {
            onValueChange?.invoke(it)
        },
        textStyle = MaterialTheme.typography.titleLarge.copy(
            fontFamily = LocalFontFamily.current
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = colors,
        leadingIcon = leadingIcon,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        placeholder = placeholder,
        shape = shape,
        minLines = if (singleLine) 1 else 3,
        trailingIcon = trailingIcon
    )

}

@Preview
@Composable
private fun CustomOutlinedTextFieldPrev() {
    App {
        CustomOutlinedTextField()
    }
}