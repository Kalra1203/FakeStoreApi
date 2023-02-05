package com.example.fakestoreapi.ui.theme.model

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapi.showProgressBar
import com.example.fakestoreapi.ui.theme.network.ApiService
import com.google.accompanist.swiperefresh.SwipeRefresh
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductViewModel : ViewModel() {
    var response: List<productsItem> by mutableStateOf(listOf())
    val _isRefreshing = MutableStateFlow(false)


    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun getItems() {

        var isVisible by remember {
            mutableStateOf(true)

        }
        showProgressBar(isVisible = isVisible)
        viewModelScope.launch {
            val apiService = ApiService.getInstance()

            try {

                val listResponse = apiService?.getProducts()
                if (listResponse != null) {
                    response = listResponse
                    isVisible = false
                }

            } catch (e: Exception) {
                e.localizedMessage

            }

        }
        showProgressBar(isVisible = isVisible)

    }


    fun RefreshItems() {


        viewModelScope.launch {
            _isRefreshing.value = true
            val apiService = ApiService.getInstance()

            try {

                val listResponse = apiService?.getProducts()
                if (listResponse != null) {
                    response = listResponse
                }

            } catch (e: Exception) {
                e.localizedMessage

            }
            _isRefreshing.value= false

        }


    }
}