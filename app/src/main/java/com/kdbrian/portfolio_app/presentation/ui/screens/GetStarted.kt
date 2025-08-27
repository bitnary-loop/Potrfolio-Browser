package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.presentation.ui.composables.CustomOutlinedTextField
import com.kdbrian.portfolio_app.presentation.ui.state.SignInUiState
import com.kdbrian.portfolio_app.util.Resource
import com.kdbrian.portfolio_app.util.toast


@Composable
fun GetStarted(
    emailPasswordState: Resource<Boolean> = Resource.Idle(),
    signInUiState: SignInUiState = SignInUiState(),
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onPasswordVisibilityChanged: () -> Unit = {},
    onLogin: () -> Unit = {}
) {

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(emailPasswordState) {
        if (emailPasswordState is Resource.Error && emailPasswordState.message.isNotEmpty())
            context.toast(message = emailPasswordState.message, apply = {
                this.setGravity(
                    android.view.Gravity.NO_GRAVITY,
                    0,
                    0
                )
            })

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(12.dp)
        ) {

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
                },
                value = signInUiState.email,
                onValueChange = onEmailChanged,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
            )

            CustomOutlinedTextField(
                value = signInUiState.password,
                onValueChange = onPasswordChanged,
                trailingIcon = {
                    IconButton(onClick = onPasswordVisibilityChanged) {
                        AnimatedContent(
                            targetState = signInUiState.isPasswordVisible,
                            label = "password visibility"
                        ) {
                            if (it) {
                                Icon(
                                    imageVector = Icons.Rounded.VisibilityOff,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Rounded.Visibility,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(text = "Enter your password")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            if (emailPasswordState is Resource.Loading)
                CircularProgressIndicator()
            else
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