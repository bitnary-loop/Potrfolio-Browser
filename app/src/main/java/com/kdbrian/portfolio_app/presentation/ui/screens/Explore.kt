package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily
import com.kdbrian.portfolio_app.presentation.ui.composables.HorizontallyStackedItemDetails
import com.kdbrian.portfolio_app.presentation.ui.composables.VerticalStackedItemDetails


@Composable
fun Explore(
    onExpand: (String) -> Unit = {},
    onSearch: () -> Unit = {},
    openForYou: () -> Unit = {}
) {


    val categoryOption = (0..10).map { "Cat $it" }.toList()
    var isCategoryOptionsVisible by remember { mutableStateOf(false) }



    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = "Explore", style = MaterialTheme.typography.displayLarge.copy(
                            fontFamily = LocalFontFamily.current
                        )
                    )

                    Text(
                        text = "Discover what others are building",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = LocalFontFamily.current
                        )
                    )
                }

                IconButton(onClick = onSearch) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }

            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            item {

                Text(
                    text = "Popular", style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = LocalFontFamily.current
                    ),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )

                LazyRow(
                    modifier = Modifier.padding(horizontal = 12.dp),
                ) {
                    items(12) {
                        VerticalStackedItemDetails(onExpand = onExpand)
                    }
                }

            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 12.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Category",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = LocalFontFamily.current
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )

                    Box {
                        Surface(
                            onClick = { isCategoryOptionsVisible = !isCategoryOptionsVisible },
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shadowElevation = 3.dp
                        ) {

                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "All",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontFamily = LocalFontFamily.current
                                    )
                                )

                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )

                            }

                        }

                        DropdownMenu(
                            expanded = isCategoryOptionsVisible,
                            onDismissRequest = { isCategoryOptionsVisible = false }
                        ) {
                            categoryOption.forEach {
                                DropdownMenuItem(
                                    modifier = Modifier.clip(RoundedCornerShape(50)),
                                    text = { Text(text = it, modifier = Modifier.padding(
                                        horizontal = 12.dp,
                                        vertical = 4.dp
                                    ), style = MaterialTheme.typography.labelLarge.copy(
                                        fontFamily = LocalFontFamily.current
                                    )) },
                                    onClick = { isCategoryOptionsVisible = false }
                                )
                            }
                        }

                    }
                }

            }

            items(12) {
                HorizontallyStackedItemDetails(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    onExpand = onExpand
                )
            }

        }


    }


}

@Preview
@Composable
private fun ExplorePrev() {
    App {
        Explore()
    }
}