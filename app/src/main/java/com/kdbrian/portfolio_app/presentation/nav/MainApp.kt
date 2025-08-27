package com.kdbrian.portfolio_app.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kdbrian.portfolio_app.presentation.ui.screens.GetStarted
import com.kdbrian.portfolio_app.presentation.ui.screens.HomeScreen

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = GetStarted
    ){

        composable<GetStarted>{
            GetStarted()
        }

        composable<HomeScreen>{
            HomeScreen()
        }


    }

}