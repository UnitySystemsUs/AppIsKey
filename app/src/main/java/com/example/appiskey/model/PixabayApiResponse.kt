package com.example.appiskey.model


import com.example.appiskey.network.BaseResponse
import com.google.gson.annotations.SerializedName

data class PixabayApiResponse(
    @SerializedName("hits")
    var pixabayPhotoList: ArrayList<PixabayPhotoModel>?,
    @SerializedName("total")
    var total: Int?,
    @SerializedName("totalHits")
    var totalHits: Int?
) : BaseResponse()