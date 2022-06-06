package com.jaisonmacklin.test.marsroverphotogallery.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("camara") val camera: Camera,
    @SerializedName("earth_date") val earthDate: String,
    @SerializedName("id") val id: String,
    @SerializedName("img_src") val imgSrc: String,
    @SerializedName("rover") val rover: Rover,
    @SerializedName("sol") val sol: String
) : Parcelable
