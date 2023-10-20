package com.example.appiskey.photolisting.data

import com.example.appiskey.photolisting.constants.PixabayPhotoApiEndPoint.PHOTO
import com.example.appiskey.photolisting.model.PixabayApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayPhotoApiInterface {

    @GET(PHOTO)
    suspend fun getPixabayPhotos(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String,
        @Query("pretty") pretty: Boolean,
    ): Response<PixabayApiResponse>

}