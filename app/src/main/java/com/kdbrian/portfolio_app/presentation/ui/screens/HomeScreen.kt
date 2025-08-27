package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.presentation.nav.Create
import com.kdbrian.portfolio_app.presentation.nav.Explore
import com.kdbrian.portfolio_app.presentation.nav.Profile


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
) {

    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Create,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Create> {
                Create()
            }

            composable<Profile> {
                Profile()
            }

            composable<Explore> {
                Explore()
            }


        }
    }


}

@Preview
@Composable
private fun HomeScreenPrev() {
    App {
        HomeScreen()
    }
}