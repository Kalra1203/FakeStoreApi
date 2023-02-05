package com.example.fakestoreapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fakestoreapi.ui.theme.FakeStoreApiTheme
import com.example.fakestoreapi.ui.theme.model.ProductViewModel
import com.example.fakestoreapi.ui.theme.model.productsItem
import com.example.fakestoreapi.ui.theme.ui.SingleProduct
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity : ComponentActivity() {
    val model by viewModels<ProductViewModel>()


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreApiTheme {

                val isRefreshing by model._isRefreshing.collectAsState()
                val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = {
                            model.RefreshItems()

                        }
                    ) {
                        TopBar()
                        ShowProducts(product = model.response)
                        model.getItems()


                    }

//                    ShowProducts(product = model.response)
//                    model.getItems()


                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "FakeStoreAPI")
                }, actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Search, contentDescription = "Search"
                        )

                    }
                })


        })
    {


    }


}


@Composable
fun ShowProducts(product: List<productsItem>) {


    LazyColumn(Modifier.padding(top = 60.dp)) {
        items(product) { product ->
            SingleProduct(product = product)

        }
    }

}


@Composable
fun showProgressBar(isVisible: Boolean) {

    if (isVisible)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()

        }

}






