package com.kdbrian.portfolio_app.presentation.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App

@Composable
fun SurfacedButton(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onClick: () -> Unit = {}
) {

    Surface(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(50)
    ){

        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            leadingIcon()

            trailingIcon()
        }

    }

}

@Preview
@Composable
private fun SurfacedButtonPrev() {
    App {
        SurfacedButton()
    }
}