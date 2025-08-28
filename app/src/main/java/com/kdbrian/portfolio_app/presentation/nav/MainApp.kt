package com.kdbrian.portfolio_app.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.kdbrian.portfolio_app.presentation.ui.screens.GetStarted
import com.kdbrian.portfolio_app.presentation.ui.screens.HomeScreen
import com.kdbrian.portfolio_app.presentation.ui.state.SignInViewModel
import com.kdbrian.portfolio_app.util.Resource
import com.kdbrian.portfolio_app.util.toast
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController(),
) {

    val context = LocalContext.current
    val signInViewModel = koinViewModel<SignInViewModel>()
    val signInState by signInViewModel.signInState.collectAsState()
    val createAccountState by signInViewModel.createAccountState.collectAsState()
    val emailPasswordState by signInViewModel.emailPasswordState.collectAsState()
    val messages by signInViewModel.messages.collectAsState(Resource.Idle())

    val firebaseAuth = koinInject<FirebaseAuth>()

    LaunchedEffect(emailPasswordState, createAccountState) {
        if (
            emailPasswordState is Resource.Success &&
            createAccountState is Resource.Success &&
            firebaseAuth.currentUser != null
        ) {
            context.toast("Logged in successfully")
            navController.navigate(HomeScreen) {
                popUpTo(GetStarted) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }

    }

    LaunchedEffect(messages) {
        if (messages is Resource.Error || messages.message?.isNotEmpty() == true)
            context.toast(message = messages.message ?: messages.data ?: "")
    }


    NavHost(
        navController = navController,
        startDestination = if (firebaseAuth.currentUser != null) HomeScreen else GetStarted
    ) {

        composable<GetStarted> {
            GetStarted(
                emailPasswordState = emailPasswordState,
                onEmailChanged = signInViewModel::onEmailChanged,
                onPasswordChanged = signInViewModel::onPasswordChanged,
                onPasswordVisibilityChanged = signInViewModel::onPasswordVisibilityChanged,
                signInUiState = signInState,
                onLogin = signInViewModel::emailPasswordSignIn,
                onCreateAccount = signInViewModel::emailPasswordSignUp
            )
        }

        composable<HomeScreen> {
            HomeScreen(navController = navController)
        }


    }

}