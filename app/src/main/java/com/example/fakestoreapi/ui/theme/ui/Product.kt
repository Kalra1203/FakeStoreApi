package com.example.fakestoreapi.ui.theme.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.fakestoreapi.R
import com.example.fakestoreapi.ui.theme.download.AndroidDownloader
import com.example.fakestoreapi.ui.theme.model.productsItem

@Composable
fun SingleProduct(product: productsItem) {
    val context= LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxHeight(.3f)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = "product image",
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .fillMaxHeight(.3f)


        )
        Column() {
            Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.fillMaxSize()) {


                Text(
                    text = product.title,
                    color = Color.Blue,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(7.dp)
                )
            }

            Row {
                Box(contentAlignment = Alignment.BottomStart) {
                    Text(
                        text = "$" + product.price.toString(),
                        color = Color.Red,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 7.dp)
                    )
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {

                    IconButton(onClick = {
                        Toast.makeText(context, "Download Started...", Toast.LENGTH_LONG).show()
                        val downloader= AndroidDownloader(context)
                        downloader.downloadFile(product.image)
                    }) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.download
                            ),
                            modifier = Modifier.size(35.dp),
                            contentDescription = "download",
                        )
                    }
                }
            }
        }
        Divider(thickness = 2.dp, color = Color.Black)
    }
}


