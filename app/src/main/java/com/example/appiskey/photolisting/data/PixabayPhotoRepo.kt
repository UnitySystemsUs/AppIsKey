package com.example.appiskey.photolisting.data

import com.example.appiskey.photolisting.model.PixabayApiResponse

interface PixabayPhotoRepo {
    suspend fun getPixabayPhotos(map: HashMap<String, Any>) : PixabayApiResponse
}