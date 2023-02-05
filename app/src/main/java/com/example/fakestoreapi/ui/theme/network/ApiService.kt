package com.example.fakestoreapi.ui.theme.network

import com.example.fakestoreapi.ui.theme.model.productsItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL= "https://fakestoreapi.com/"

interface ApiService {

    @GET("products")
    suspend fun getProducts() : List<productsItem>

    companion object {
        var apiService: ApiService?= null
        fun getInstance():ApiService?{
            if(apiService== null){
                apiService= Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return apiService!!
        }

    }


}