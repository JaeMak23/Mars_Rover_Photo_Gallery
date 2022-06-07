package com.jaisonmacklin.test.marsroverphotogallery.data.api

import com.jaisonmacklin.test.marsroverphotogallery.data.model.PhotosResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("sol") sol :Int,
        @Query("api_key")apiKey:String,
        @Query("page")page:Int
    ): Response<PhotosResponse>?
}
