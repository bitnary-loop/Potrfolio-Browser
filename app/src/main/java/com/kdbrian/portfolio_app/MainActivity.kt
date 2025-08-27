package com.kdbrian.portfolio_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.kdbrian.portfolio_app.presentation.nav.MainApp
import com.kdbrian.portfolio_app.presentation.ui.theme.PortfolioAppTheme
import com.kdbrian.portfolio_app.presentation.ui.theme.appFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App {
                MainApp(

                )
            }
        }
    }
}


val LocalFontFamily = staticCompositionLocalOf {
    appFontFamily
}

@Composable
fun App(
    content: @Composable () -> Unit,
) {
    PortfolioAppTheme {
        content()
    }

}