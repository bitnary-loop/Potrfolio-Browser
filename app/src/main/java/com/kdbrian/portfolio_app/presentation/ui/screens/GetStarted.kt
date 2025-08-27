package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.presentation.ui.composables.CustomOutlinedTextField


@Composable
fun GetStarted(
    onLogin: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(12.dp)
        ){

            Text(
                text = "Get Started",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontFamily = com.kdbrian.portfolio_app.LocalFontFamily.current
                ),
                modifier = Modifier.padding(12.dp)
            )

            CustomOutlinedTextField(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.AlternateEmail,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(text = "Enter your email")
                }
            )

            CustomOutlinedTextField(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(text = "Enter your password")
                }
            )

            Button(onClick = onLogin) {
                Text(text = "Login")
            }

        }

    }
}

@Preview
@Composable
private fun GetStartedPrev() {
    App {
        GetStarted()
    }
}