package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily
import com.kdbrian.portfolio_app.presentation.ui.composables.CustomOutlinedTextField
import com.kdbrian.portfolio_app.util.DateUtils
import com.kdbrian.portfolio_app.util.DateUtils.toFormattedDate
import com.kdbrian.portfolio_app.util.toast
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewSolution(
    onClose: () -> Unit = {}
) {

    val context = LocalContext.current
    var currentTab by remember { mutableIntStateOf(0) }
    val clipboardManager = LocalClipboardManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = "Explore solution", style = MaterialTheme.typography.titleLarge)
                        Text(
                            text = UUID.randomUUID().toString().split("-").first(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(12.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(25))
            ) {
                AsyncImage(
                    model = "https://picsum.photos/200",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }


            Text(
                text = LoremIpsum(3).values.joinToString(),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontFamily = LocalFontFamily.current
                ),
            )

            TabRow(
                selectedTabIndex = currentTab,
                divider = { },
            ) {
                Tab(
                    selected = currentTab == 0,
                    onClick = {
                        currentTab = 0
                    },
                    text = {
                        Text(
                            text = "Details",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontFamily = LocalFontFamily.current
                            )
                        )
                    }
                )

                Tab(
                    selected = currentTab == 1,
                    onClick = {
                        currentTab = 1
                    },
                    text = {
                        Text(
                            text = "Reviews",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontFamily = LocalFontFamily.current
                            )
                        )
                    }
                )
            }

            AnimatedContent(
                targetState = currentTab,
                modifier = Modifier.weight(1f)
            ) {
                when (it) {
                    0 -> {

                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BasicText(
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(12.dp),
                                text = LoremIpsum(32).values.joinToString(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = LocalFontFamily.current,
                                    fontWeight = FontWeight.Light,
                                    color = Color.White
                                ),
                            )

                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(25),
                                shadowElevation = 2.dp
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp)
                                ) {

                                    Text(
                                        text = "Copyright \u00A9",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontFamily = LocalFontFamily.current
                                        )
                                    )

                                    Spacer(Modifier.padding(8.dp))

                                    Text(
                                        text = LoremIpsum(3).values.joinToString(),
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            fontFamily = LocalFontFamily.current
                                        )
                                    )

                                    Text(
                                        modifier = Modifier.padding(top = 4.dp),
                                        text = System.currentTimeMillis()
                                            .toFormattedDate(DateUtils.FORMAT_FULL),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontFamily = LocalFontFamily.current
                                        )
                                    )
                                }
                            }


                            CustomOutlinedTextField(
                                value = "https://picsum.photos/200",
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Link,
                                        contentDescription = null,
                                        modifier = Modifier.rotate(40.toFloat())
                                    )
                                },
                                enabled = false,
                                modifier = Modifier.clickable {
                                    clipboardManager.setText(
                                        androidx.compose.ui.text.AnnotatedString("https://picsum.photos/200")
                                    )
                                    context.toast("Copied to clipboard")
                                }
                            )

                            Surface(
                                shape = CircleShape,
                                shadowElevation = 3.dp,
                                modifier = Modifier.padding(12.dp)
                            ) {

                                Icon(
                                    imageVector = Icons.Rounded.Share,
                                    contentDescription = null,
                                    modifier = Modifier.rotate(-(40).toFloat())
                                )

                            }
                        }
                    }

                    1 -> {
                        Box(
                            modifier= Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = "Work in progress. Coming soon!!!",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontFamily = LocalFontFamily.current
                                )
                            )
                        }
                    }
                }
            }

        }
    }
}


@Preview
@Composable
private fun ViewSolutionPrev() {
    App {
        ViewSolution()
    }
}