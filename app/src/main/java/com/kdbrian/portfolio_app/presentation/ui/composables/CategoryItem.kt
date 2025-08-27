package com.kdbrian.portfolio_app.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    text: String = "Category",
) {

    Surface(
        shape = RoundedCornerShape(50),
        shadowElevation = 3.dp,
        modifier = modifier.padding(4.dp)
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = LocalFontFamily.current
            ),
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 6.dp
            )
        )

    }

}

@Preview
@Composable
private fun CategoryItemPrev() {
    App {
        Column {
            repeat(12){
                CategoryItem()
            }
        }
    }
}