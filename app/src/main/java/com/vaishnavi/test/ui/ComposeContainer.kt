package com.vaishnavi.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vaishnavi.model.Response

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComposeContainer(response: Response) {
    response.rows.let {
        LazyColumn(
            Modifier
                .background(Color.DarkGray.copy(alpha = 0.4f))
                .padding(dimensionResource(id = R.dimen.padding_0_5x))
                .fillMaxSize()
        ) {
            items(it) { item ->
                if(!item.title.isNullOrEmpty()) {
                    Card(Modifier.padding(dimensionResource(id = R.dimen.padding_0_5x))) {
                        Row(
                            Modifier
                                .background(Color.Black.copy(0.9f))
                                .padding(dimensionResource(id = R.dimen.padding_1x))
                        ) {
                            val painter = rememberImagePainter(item.imageHref)
                            Image(
                                painter = painter ?: painterResource(id = R.drawable.ic_placeholder),
                                stringResource(id = R.string.content_description_image),
                                modifier = Modifier
                                    .height(dimensionResource(id = R.dimen.image_height))
                                    .width(dimensionResource(id = R.dimen.image_width))
                                    .background(Color.DarkGray.copy(0.2f))
                            )

                            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_1x)))

                            Column(
                                Modifier
                                    .fillMaxWidth()

                            ) {
                                item.title?.let { title ->
                                    Text(
                                        text = title,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                                Spacer(modifier = Modifier.padding(4.dp))
                                item.description?.let { desc ->
                                    Text(
                                        text = desc,
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        maxLines = 6,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}