package com.kdbrian.portfolio_app.presentation.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kdbrian.portfolio_app.App
import com.kdbrian.portfolio_app.LocalFontFamily
import com.kdbrian.portfolio_app.R

@Composable
fun HorizontallyStackedItemDetails(
    modifier: Modifier = Modifier,
    image: String = "https://picsum.photos/200",
    title: String = LoremIpsum(3).values.joinToString(),
    description: String = LoremIpsum(12).values.joinToString()
) {

    Box(modifier = modifier.fillMaxWidth().heightIn(max = 180.dp)) {

        Row (
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(25))
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.___fiery_chicken_ramen_with_creamy_garlic_sauce),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = LocalFontFamily.current,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                BasicText(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = LocalFontFamily.current,
                        fontWeight = FontWeight.Light
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }


    }

}

@Preview
@Composable
private fun HorizontallyStackedItemDetailsPrev() {
    App {
        val scrollState = rememberScrollState()
        Column (
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            repeat(13) {
                HorizontallyStackedItemDetails()
            }
        }
    }
}