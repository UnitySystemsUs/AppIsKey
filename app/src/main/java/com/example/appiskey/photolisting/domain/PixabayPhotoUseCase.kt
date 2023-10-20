package com.example.appiskey.photolisting.domain

import com.example.appiskey.photolisting.model.PixabayApiResponse
import com.example.appiskey.photolisting.data.PixabayPhotoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PixabayPhotoUseCase @Inject constructor(private val pixabayPhotoRepo: PixabayPhotoRepo) {

    suspend fun getPixabayPhotos(map: HashMap<String, Any>): PixabayApiResponse {
        return withContext(Dispatchers.IO) {
            pixabayPhotoRepo.getPixabayPhotos(map)
        }
    }

}