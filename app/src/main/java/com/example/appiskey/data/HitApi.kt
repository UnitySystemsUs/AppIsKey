package com.example.appiskey.data

import com.example.appiskey.model.PixabayApiResponse
import retrofit2.Response

interface HitApi {
    suspend fun hit(map: HashMap<String, Any>) : Response<PixabayApiResponse>
}