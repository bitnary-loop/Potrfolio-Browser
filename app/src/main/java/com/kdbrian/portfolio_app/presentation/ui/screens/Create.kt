package com.kdbrian.portfolio_app.presentation.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily
import com.kdbrian.portfolio_app.R
import com.kdbrian.portfolio_app.presentation.ui.composables.CustomOutlinedTextField
import com.kdbrian.portfolio_app.util.getFileInfo
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Create() {

    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(Uri.EMPTY) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        imageUri = it
    }
//    val fileInfo = derivedStateOf {
//        if (imageUri != null && imageUri != Uri.EMPTY)
//            context.getFileInfo(imageUri!!)
//        else
//            null
//    }

    LaunchedEffect(imageUri) {
        if (imageUri != null && imageUri != Uri.EMPTY) {
            val fileInfo = context.getFileInfo(imageUri!!)
            Timber.d("File Info: $fileInfo")
        }
    }

    val permissionState = rememberPermissionState(
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU)
            android.Manifest.permission.READ_MEDIA_IMAGES
        else
            android.Manifest.permission.READ_EXTERNAL_STORAGE

    )


    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Create",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(12.dp),
                style = MaterialTheme.typography.displayLarge.copy(
                    fontFamily = LocalFontFamily.current
                )
            )


            Text(
                text = "You already brought that idea to life. Now bring more lives to it",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = LocalFontFamily.current
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {

                CustomOutlinedTextField(
                    value = name,
                    leadingIcon = {},
                    placeholder = { Text(text = "Name", fontFamily = LocalFontFamily.current) },
                    onValueChange = { name = it },
                    modifier = Modifier.weight(1f),
                )


                Column(
                    modifier = Modifier.padding(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(12)),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = "https://picsum.photos/200",
                            modifier = Modifier.fillMaxSize(),
                            error = painterResource(R.drawable.___fiery_chicken_ramen_with_creamy_garlic_sauce),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(0.75f),
                                            Color.Black.copy(0.75f),
                                            Color.Black.copy(0.65f),
                                            Color.Transparent,
                                        )
                                    )
                                )
                        )

                        IconButton(onClick = {
                            permissionState.launchPermissionRequest()
                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = null,
                            )
                        }
                    }

                }

            }

            Spacer(Modifier.padding(12.dp))


            CustomOutlinedTextField(
                value = name,
                leadingIcon = {},
                singleLine = false,
                placeholder = { Text(text = "Description", fontFamily = LocalFontFamily.current) },
                onValueChange = { name = it },
                modifier = Modifier,
            )

            CustomOutlinedTextField(
                value = name,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Link,
                        contentDescription = null,
                        modifier = Modifier.rotate(60.toFloat())
                    )
                },
                placeholder = { Text(text = "Link", fontFamily = LocalFontFamily.current) },
                onValueChange = { name = it },
                modifier = Modifier,
            )



            Text(
                fontFamily = LocalFontFamily.current,
                text = buildAnnotatedString {

                    withStyle(
                        SpanStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Info graphic\n")
                    }

                    withStyle(
                        SpanStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                    ) {
                        append("See more about requirements")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )



            CustomOutlinedTextField(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Upload,
                        contentDescription = null
                    )
                },
                enabled = false,
                placeholder = {
                    Text(
                        text = "Tap to select",
                        fontFamily = LocalFontFamily.current
                    )
                },
                onValueChange = { name = it },
                modifier = Modifier.clickable {
                    permissionState.launchPermissionRequest()
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
            )


            Button(onClick = {}, modifier = Modifier.padding(top = 24.dp)) {
                Text(text = "Create")
            }

        }

    }

}

@Preview
@Composable
private fun CreatePrev() {
    App {
        Create()
    }
}