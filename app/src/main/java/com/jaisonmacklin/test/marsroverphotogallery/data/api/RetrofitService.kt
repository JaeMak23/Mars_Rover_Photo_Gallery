package com.jaisonmacklin.test.marsroverphotogallery.data.api

import com.jaisonmacklin.test.marsroverphotogallery.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    var client:OkHttpClient=OkHttpClient.Builder().build()
    //private const val BASE_URL="https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"

    private var retrofit=Retrofit.Builder()
        .baseUrl(R.string.base_url.toString())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> createService(serviceClass: Class<T>):T=
        retrofit.create(serviceClass)
}