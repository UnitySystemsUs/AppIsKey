package com.example.appiskey.photolisting.data

import com.example.appiskey.photolisting.model.PixabayApiResponse
import retrofit2.Response

interface PixabayPhotoApi {
    suspend fun getPixabayPhotos(map: HashMap<String, Any>) : Response<PixabayApiResponse>
}