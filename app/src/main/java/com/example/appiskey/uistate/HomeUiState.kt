package com.example.appiskey.uistate

import com.example.appiskey.model.PixabayApiResponse
import com.example.appiskey.network.ErrorResponse

sealed interface HomeUiState {
    data object NoNetwork : HomeUiState
    data object ShowProgress : HomeUiState
    data class HitResponse(val pixabayApiResponse: PixabayApiResponse) : HomeUiState
    data class Error(val errorResponse: ErrorResponse?) : HomeUiState
}