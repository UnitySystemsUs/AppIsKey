package com.example.appiskey.domain

import com.example.appiskey.model.PixabayApiResponse
import com.example.appiskey.data.HomeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepo: HomeRepo) {

    suspend fun hitApi(map: HashMap<String, Any>): PixabayApiResponse {
        return withContext(Dispatchers.IO) {
            homeRepo.hit(map)
        }
    }

}