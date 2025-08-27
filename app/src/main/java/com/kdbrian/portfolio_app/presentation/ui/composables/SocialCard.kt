package com.kdbrian.portfolio_app.presentation.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App

@Composable
fun SocialCard(
    modifier: Modifier = Modifier,
            painter : Int
) {

    Surface(
        modifier = modifier.size(50.dp),
        shape = CircleShape
    ) {


    }
}

@Preview
@Composable
private fun SocialCardPrev() {
    App {
//        SocialCard()
    }
}