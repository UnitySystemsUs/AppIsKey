package com.example.appiskey.photolisting.data

import android.util.Log
import com.example.appiskey.BuildConfig
import com.example.appiskey.base.network.ApiClient
import com.example.appiskey.photolisting.model.PixabayApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PixabayPhotoApiService @Inject constructor(private val apiClient: ApiClient) :
    PixabayPhotoApi {

    private val apiService: PixabayPhotoApiInterface by lazy {
        apiClient.retrofit.create(PixabayPhotoApiInterface::class.java)
    }

    override suspend fun getPixabayPhotos(map: HashMap<String, Any>): Response<PixabayApiResponse> {
        return withContext(Dispatchers.IO) {
            val q = map["q"].toString()
            val imageType = map["image_type"].toString()
            val pretty = map["pretty"].toString().toBoolean()
            map.clear()
            Log.d("", "hit: $map")
            apiService.getPixabayPhotos(BuildConfig.key, q, imageType, pretty)
        }
    }
}