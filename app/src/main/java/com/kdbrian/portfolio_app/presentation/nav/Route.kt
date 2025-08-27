package com.kdbrian.portfolio_app.presentation.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class Route

@Serializable
data object HomeScreen : Route()

@Serializable
data object GetStarted : Route()

@Serializable
data object NumberLogin : Route()

@Serializable
data object ForYou : Route()

@Serializable
data object Explore : Route()

@Serializable
data object Search : Route()

@Serializable
data object Profile : Route()

@Serializable
data object Create : Route()



