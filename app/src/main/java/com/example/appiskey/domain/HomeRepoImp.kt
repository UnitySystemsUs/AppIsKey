package com.example.appiskey.domain

import android.util.Log
import com.example.appiskey.model.PixabayApiResponse
import com.example.appiskey.data.HitApi
import com.example.appiskey.data.HomeRepo
import javax.inject.Inject

class HomeRepoImp @Inject constructor(private val api: HitApi) : HomeRepo {
    override suspend fun hit(map: HashMap<String, Any>): PixabayApiResponse {
        val response = api.hit(map)
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