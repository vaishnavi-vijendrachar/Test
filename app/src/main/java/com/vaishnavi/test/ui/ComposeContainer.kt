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
import androidx.compose.ui.res.painterResource
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
                .padding(4.dp)
                .fillMaxSize()
        ) {
            items(it) { item ->
                if(!item.title.isNullOrEmpty()) {
                    Card(Modifier.padding(4.dp)) {
                        Row(
                            Modifier
                                .background(Color.Black.copy(0.9f))
                                .padding(8.dp)
                        ) {
                            val painter = rememberImagePainter(item.imageHref)
                            Image(

                                painter = painter ?: painterResource(id = R.drawable.ic_placeholder),
                                "Image",
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(100.dp)
                                    .background(Color.DarkGray.copy(0.2f))
                            )

                            Spacer(modifier = Modifier.padding(8.dp))

                            Column(
                                Modifier
                                    .fillMaxWidth()

                            ) {
                                item.title?.let { it1 ->
                                    Text(
                                        text = it1,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                                Spacer(modifier = Modifier.padding(4.dp))
                                item.description?.let { it1 ->
                                    Text(
                                        text = it1,
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