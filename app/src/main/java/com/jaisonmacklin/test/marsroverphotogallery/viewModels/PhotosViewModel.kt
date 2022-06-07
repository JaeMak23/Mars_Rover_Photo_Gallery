package com.jaisonmacklin.test.marsroverphotogallery.viewModels

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaisonmacklin.test.marsroverphotogallery.data.model.Photo
import com.jaisonmacklin.test.marsroverphotogallery.utils.Result
import com.jaisonmacklin.test.marsroverphotogallery.data.repository.PhotosRepository
import kotlinx.coroutines.launch

class PhotosViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private val photos = MutableLiveData<Result<ArrayList<Photo>>>()
    private var currentPage = 1

    // to save and restore rv's adapter
    var listState: Parcelable? = null

    fun getPhotos() = photos

    fun getCurrentPage() = currentPage

    fun loadData() {
        if (currentPage == 1) {
            photos.postValue(Result.InProgress)
        }

        viewModelScope.launch {
            val response = photosRepository.getPhotosFromApi(1000, currentPage)

            response.let {
                when (it) {
                    is Result.Success -> {
                        val photosList = it.extractData

                        photosList?.let { list ->
                            //  set photos for first page
                            if (currentPage == 1) {
                                photos.postValue(Result.Success(list))
                            } else {
                                //  add photos to current list
                                val currentPhotos: ArrayList<Photo>? = photos.value?.extractData

                                if (currentPhotos == null || currentPhotos.isEmpty()) {
                                    photos.postValue(Result.Success(list))
                                } else {
                                    currentPhotos.addAll(list)
                                    photos.postValue(Result.Success(currentPhotos))
                                }

                            }
                        }
                    }
                    else -> photos.postValue(it)
                }
            }
        }
    }

    fun loadDataNextPage(){
        currentPage++
        loadData()
    }
}