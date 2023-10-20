package com.example.appiskey.photolisting.domain

import android.util.Log
import com.example.appiskey.photolisting.data.PixabayPhotoApi
import com.example.appiskey.photolisting.data.PixabayPhotoRepo
import com.example.appiskey.photolisting.model.PixabayApiResponse
import javax.inject.Inject

class PixabayPhotoRepoImp @Inject constructor(private val api: PixabayPhotoApi) : PixabayPhotoRepo {
    override suspend fun getPixabayPhotos(map: HashMap<String, Any>): PixabayApiResponse {
        val response = api.getPixabayPhotos(map)
        Log.d("HitRepoImpl", "Hit: $response")
        return if (response.isSuccessful) {
            if (response.body()?.success?.toBoolean() == false) {
                throw Exception(response.body()?.message ?: "")
            }
            requireNotNull(response.body())
        } else
            error(response)

    }
}