package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily
import com.kdbrian.portfolio_app.presentation.ui.composables.CustomOutlinedTextField
import com.kdbrian.portfolio_app.presentation.ui.composables.VerticalStackedItemDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(

) {

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(12.dp),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    CustomOutlinedTextField()
                }
            )
        }
    ){ padding ->

        LazyColumn(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){

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
                        VerticalStackedItemDetails()
                    }
                }

            }


            item {

                Text(
                    text = "Recents", style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = LocalFontFamily.current
                    ),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )

            }


            items(8){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = LoremIpsum(4).values.joinToString(), style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = LocalFontFamily.current
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            }


        }

    }

}

@Preview
@Composable
private fun SearchPrev() {
    App {
        Search()
    }
}