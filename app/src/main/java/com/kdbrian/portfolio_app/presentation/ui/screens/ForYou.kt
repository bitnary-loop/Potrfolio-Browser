package com.kdbrian.portfolio_app.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily
import com.kdbrian.portfolio_app.presentation.ui.composables.CategoryItem
import com.kdbrian.portfolio_app.presentation.ui.composables.HorizontallyStackedItemDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForYou(

) {

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = "For You",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontFamily = LocalFontFamily.current
                    ),
                    modifier = Modifier.padding(12.dp)
                )

                LazyRow(
                    modifier = Modifier.padding(12.dp)
                ){
                    items(10) {
                        CategoryItem()
                    }

                }

            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

            item {
            }



            items(12){
                HorizontallyStackedItemDetails(
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }

        }

    }

}

@Preview
@Composable
private fun ForYouPrev() {
    App {
        ForYou()
    }
}