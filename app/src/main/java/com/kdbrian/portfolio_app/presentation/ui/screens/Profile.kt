package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.BuildConfig
import com.kdbrian.portfolio_app.R
import com.kdbrian.portfolio_app.presentation.ui.composables.SurfacedButton
import com.kdbrian.portfolio_app.util.DateUtils
import com.kdbrian.portfolio_app.util.DateUtils.toFormattedDate


@Composable
fun Profile(
    onLogout: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(12.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            ) {
                AsyncImage(
                    model = "https://picsum.photos/200",
                    contentDescription = null,
                    error = painterResource(id = R.drawable.___fiery_chicken_ramen_with_creamy_garlic_sauce),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Text(
                text = "KDBrian",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = com.kdbrian.portfolio_app.LocalFontFamily.current,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = "kdbrian@dev.com",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = com.kdbrian.portfolio_app.LocalFontFamily.current,
                    fontWeight = FontWeight.Light
                )
            )

            Spacer(Modifier.padding(12.dp))

            SurfacedButton(
                leadingIcon = {
                    Text(text = "Verify email")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Send,
                        contentDescription = null,
                        modifier = Modifier.rotate((-40).toFloat())
                    )
                }
            )

            SurfacedButton(
                leadingIcon = {
                    Text(text = "Deactivate account")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = null,
                        modifier = Modifier
                    )
                }
            )


            TextButton(onClick = onLogout) {
                Text(text = "Logout")
            }

            Spacer(Modifier.padding(16.dp))

            Text(
                text = buildAnnotatedString {
                    append("App ${BuildConfig.VERSION_NAME}\n")
                    append(System.currentTimeMillis().toFormattedDate(DateUtils.FORMAT_FULL))
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = com.kdbrian.portfolio_app.LocalFontFamily.current
                )
            )
        }
    }
}

@Preview
@Composable
private fun ProfilePrev() {
    App {
        Profile()
    }
}