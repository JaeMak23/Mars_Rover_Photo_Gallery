package com.jaisonmacklin.test.marsroverphotogallery.data.repository

import android.util.Log
import com.jaisonmacklin.test.marsroverphotogallery.R
import com.jaisonmacklin.test.marsroverphotogallery.data.api.ApiService
import com.jaisonmacklin.test.marsroverphotogallery.data.model.PhotosResponse
import retrofit2.Response
import java.lang.Exception

class PhotosRepository(val apiService: ApiService) {
    private val TAG=PhotosRepository::class.java.name

    var apiKey= R.string.api_key.toString()

    suspend fun getPhotosFromApi(sol:Int,page:Int):Response<PhotosResponse>?{
        try{
            val response=apiService.getPhotos(sol, apiKey, page)
            response?.let{
                return it
            }

        }catch (error :Exception){
            Log.e(TAG,"Error : ${error.message}")
        }
        return null
    }
}