package com.jaisonmacklin.test.marsroverphotogallery.data.repository

import android.util.Log
import com.jaisonmacklin.test.marsroverphotogallery.R
import com.jaisonmacklin.test.marsroverphotogallery.data.api.ApiService
import com.jaisonmacklin.test.marsroverphotogallery.data.model.Photo
import retrofit2.HttpException
import java.lang.Exception

class PhotosRepository(private val apiService: ApiService) : BaseRepository() {

    companion object {
        private val TAG = PhotosRepository::class.java.name
        const val GENERAL_ERROR_CODE = 499
    }

    var apiKey = R.string.api_key.toString()

    suspend fun getPhotosFromApi(sol: Int, page: Int)
            : com.jaisonmacklin.test.marsroverphotogallery.utils.Result<ArrayList<Photo>> {
        var result: com.jaisonmacklin.test.marsroverphotogallery.utils.Result<ArrayList<Photo>> =
            handleSuccess(arrayListOf())
        try {
            val response = apiService.getPhotos(sol, apiKey, page)
            response?.let {
                it.body()?.photos?.let { photosResponse ->
                    result = handleSuccess(photosResponse)
                }
                it.errorBody()?.let { responseErrorBody ->
                    if (responseErrorBody is HttpException) {
                        responseErrorBody.response()?.code()?.let { errorCode ->
                            result = handleException(errorCode)
                        }
                    } else result = handleException(GENERAL_ERROR_CODE)
                }
            }
        } catch (error: HttpException) {
            Log.e(TAG, "Error : ${error.message}")
            return handleException(error.code())
        }
        return result
    }
}