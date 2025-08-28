package com.kdbrian.portfolio_app.presentation.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.auth.FirebaseAuth
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily
import com.kdbrian.portfolio_app.R
import com.kdbrian.portfolio_app.presentation.ui.composables.CategoryItem
import com.kdbrian.portfolio_app.presentation.ui.composables.CustomOutlinedTextField
import com.kdbrian.portfolio_app.presentation.ui.state.CreateScreenUiState
import com.kdbrian.portfolio_app.util.FileInfo
import com.kdbrian.portfolio_app.util.Resource
import com.kdbrian.portfolio_app.util.getFileInfo
import com.kdbrian.portfolio_app.util.toast
import org.koin.compose.koinInject

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Create(
    addSolutionState: Resource<Boolean> = Resource.Idle(),
    createScreenUiState: CreateScreenUiState = CreateScreenUiState(),
    onNameChange: (String) -> Unit = {},
    onDescriptionChange: (String) -> Unit = {},
    onLinkChange: (String) -> Unit = {},
    onImageUriChange: (Uri) -> Unit = {},
    onInfoGraphicUriChange: (Uri) -> Unit = {},
    onFileInfoChange: (FileInfo) -> Unit = {},
    onSave: () -> Unit = {}
) {

    val firebaseAuth = koinInject<FirebaseAuth>()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        it?.let {
            onImageUriChange(it)
        }
    }
    val infoImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            it?.let {
                onInfoGraphicUriChange(it)
            }
        }

    LaunchedEffect(createScreenUiState.infoGraphic) {
        createScreenUiState.infoGraphic?.let {
            onFileInfoChange(context.getFileInfo(it))
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    val permissionState = rememberPermissionState(
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU)
            android.Manifest.permission.READ_MEDIA_IMAGES
        else
            android.Manifest.permission.READ_EXTERNAL_STORAGE

    )

    LaunchedEffect(addSolutionState) {
        if (addSolutionState.message?.isNotEmpty() == true)
            snackbarHostState.showSnackbar(addSolutionState.message.toString())
        if (addSolutionState is Resource.Success) {
            snackbarHostState.showSnackbar("Solution added")
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        AnimatedContent(
            targetState = firebaseAuth.currentUser != null && firebaseAuth.currentUser!!.isEmailVerified,
            label = "permission",
            modifier = Modifier
                .padding(padding)

        ) {
            if (it) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    item {
                        Text(
                            text = "Create",
                            modifier = Modifier
//                        .align(Alignment.Start)
                                .padding(12.dp),
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontFamily = LocalFontFamily.current
                            )
                        )
                    }


                    item {

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
                                keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Next),
                                keyboardActions = KeyboardActions(onNext = {
                                    focusManager.moveFocus(
                                        FocusDirection.Down
                                    )
                                }),
                                enabled = addSolutionState !is Resource.Loading,
                                value = createScreenUiState.name,
                                leadingIcon = {},
                                placeholder = {
                                    Text(
                                        text = "Name",
                                        fontFamily = LocalFontFamily.current
                                    )
                                },
                                onValueChange = onNameChange,
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
                                        model = createScreenUiState.imageUri,
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
                                        launcher.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }) {
                                        Icon(
                                            imageVector = Icons.Rounded.Edit,
                                            contentDescription = null,
                                        )
                                    }
                                }

                            }

                        }
                    }


                    item {
                        CustomOutlinedTextField(
                            enabled = addSolutionState !is Resource.Loading,
                            value = createScreenUiState.description,
                            leadingIcon = {},
                            singleLine = false,
                            placeholder = {
                                Text(
                                    text = "Description",
                                    fontFamily = LocalFontFamily.current
                                )
                            },
                            onValueChange = onDescriptionChange,
                            modifier = Modifier,
                            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                        )

                        CustomOutlinedTextField(
                            enabled = addSolutionState !is Resource.Loading,
                            value = createScreenUiState.link,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Link,
                                    contentDescription = null,
                                    modifier = Modifier.rotate(60.toFloat())
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Link",
                                    fontFamily = LocalFontFamily.current
                                )
                            },
                            onValueChange = onLinkChange,
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
//                    enabled = addSolutionState !is Resource.Loading,
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
                            value = createScreenUiState.fileInfo?.fileName ?: "",
                            modifier = Modifier.clickable {
                                if (addSolutionState !is Resource.Loading) {
                                    permissionState.launchPermissionRequest()
                                    infoImageLauncher.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                }
                            },
                        )

                    }


                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (addSolutionState is Resource.Loading)
                                CircularProgressIndicator()
                            else
                                Button(onClick = onSave, modifier = Modifier) {
                                    Text(text = "Create")
                                }
                        }
                    }

                }
            } else {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CategoryItem(
                        text = "Verify email to create"
                    ) {
                        firebaseAuth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                            context.toast("Verification email sent")
                        }

                    }


                }
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