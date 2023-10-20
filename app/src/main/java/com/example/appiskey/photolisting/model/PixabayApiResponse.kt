package com.example.appiskey.photolisting.model


import com.example.appiskey.base.network.BaseResponse
import com.google.gson.annotations.SerializedName

data class PixabayApiResponse(
    @SerializedName("hits")
    var pixabayPhotoList: ArrayList<PixabayPhotoModel>?,
    @SerializedName("total")
    var total: Int?,
    @SerializedName("totalHits")
    var totalHits: Int?
) : BaseResponse()