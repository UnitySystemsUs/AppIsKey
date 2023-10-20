package com.example.appiskey.data

import com.example.appiskey.constants.HitApiEndPoint.PHOTO
import com.example.appiskey.model.PixabayApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HitApiInterface {

    @GET(PHOTO)
    suspend fun hit(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String,
        @Query("pretty") pretty: Boolean,
    ): Response<PixabayApiResponse>

}