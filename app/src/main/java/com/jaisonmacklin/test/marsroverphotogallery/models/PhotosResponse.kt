package com.jaisonmacklin.test.marsroverphotogallery.models

import com.google.gson.annotations.SerializedName

data class PhotosResponse(@SerializedName("photos")val  photos :ArrayList<Photo>)
