package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.presentation.nav.Create
import com.kdbrian.portfolio_app.presentation.nav.Explore
import com.kdbrian.portfolio_app.presentation.nav.ForYou
import com.kdbrian.portfolio_app.presentation.nav.GetStarted
import com.kdbrian.portfolio_app.presentation.nav.Profile
import com.kdbrian.portfolio_app.presentation.nav.Route
import com.kdbrian.portfolio_app.presentation.nav.Search
import com.kdbrian.portfolio_app.presentation.nav.ViewSolution
import timber.log.Timber


data class BottomBarItem(
    val label: String,
    val route: Route,
    @param:DrawableRes val icon: Int? = null,
    val vector: ImageVector? = null
) {

    companion object {
        val defaults = listOf(
            BottomBarItem(
                label = "Explore",
                route = Explore,
                vector = Icons.Rounded.Explore
            ),
            BottomBarItem(
                label = "Create",
                route = Create,
                vector = Icons.Rounded.AddCircleOutline
            ),
            BottomBarItem(
                label = "Profile",
                route = Profile,
                vector = Icons.Rounded.AccountCircle
            )

        )
    }
}


@Composable
fun HomeScreen(
    navController: NavHostController,
) {


    val homeNavController = rememberNavController()
    val backStackEntryAsState by homeNavController.currentBackStackEntryAsState()
    val moveToScreen: (Route) -> Unit = {
        homeNavController.navigate(it) {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val route = remember {
        backStackEntryAsState?.destination?.route
    }

    LaunchedEffect(backStackEntryAsState) {
        Timber.d("route: $route")
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomBarItem.defaults.forEach { barItem ->
                    NavigationBarItem(
                        selected = route == barItem.route.toString(),
                        onClick = {
                            moveToScreen(barItem.route)
                        },
                        label = {
                            if (route == barItem.route.toString())
                                Text(
                                    text = barItem.label
                                )
                        },
                        icon = {
                            barItem.vector?.let {
                                androidx.compose.material3.Icon(
                                    imageVector = it,
                                    contentDescription = null
                                )
                            } ?: run {
                                barItem.icon?.let {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = it),
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = Color.LightGray,
                            selectedIconColor = Color.White,
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = homeNavController,
            startDestination = Create,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Create> {
                Create()
            }

            composable<Profile> {
                Profile(
                    onLogout ={
                        Timber.d("onLogout")
                        navController.navigate(GetStarted){
                            popUpTo(0){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<Explore> {
                Explore(
                    onExpand = { id -> homeNavController.navigate(ViewSolution) },
                    onSearch = {
                        homeNavController.navigate(Search)
                    }
                )
            }

            composable<Search> {
                Search(
                    onClose = {
                        homeNavController.popBackStack()
                    }
                )
            }

            composable<ForYou> {
                ForYou()
            }

            composable<ViewSolution> {
                ViewSolution(
                    onClose = {
                        homeNavController.popBackStack()
                    }
                )
            }


        }
    }


}

@Preview
@Composable
private fun HomeScreenPrev() {
    App {
        HomeScreen(rememberNavController())
    }
}