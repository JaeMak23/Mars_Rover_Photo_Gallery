package com.jaisonmacklin.test.marsroverphotogallery.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Camara(
    @SerializedName("full_name") val fullName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rover_id") val roverId: Int
) : Parcelable {
    fun getNameFullName() = "$name - $fullName"
}
