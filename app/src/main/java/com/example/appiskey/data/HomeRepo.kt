package com.example.appiskey.data

import com.example.appiskey.model.PixabayApiResponse

interface HomeRepo {
    suspend fun hit(map: HashMap<String, Any>) : PixabayApiResponse
}