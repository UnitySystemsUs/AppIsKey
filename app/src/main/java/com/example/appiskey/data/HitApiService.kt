package com.example.appiskey.data

import android.util.Log
import com.example.appiskey.BuildConfig
import com.example.appiskey.model.PixabayApiResponse
import com.example.appiskey.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class HitApiService @Inject constructor(private val apiClient: ApiClient) : HitApi {

    private val apiService: HitApiInterface by lazy {
        apiClient.retrofit.create(HitApiInterface::class.java)
    }

    override suspend fun hit(map: HashMap<String, Any>): Response<PixabayApiResponse> {
        return withContext(Dispatchers.IO) {
            val q = map["q"].toString()
            val imageType = map["image_type"].toString()
            val pretty = map["pretty"].toString().toBoolean()
            map.clear()
            Log.d("", "hit: $map")
            apiService.hit(BuildConfig.key, q, imageType, pretty)
        }
    }
}