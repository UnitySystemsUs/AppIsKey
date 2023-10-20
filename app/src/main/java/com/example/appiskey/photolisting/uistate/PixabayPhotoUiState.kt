package com.example.appiskey.photolisting.uistate

import com.example.appiskey.photolisting.model.PixabayApiResponse
import com.example.appiskey.base.network.ErrorResponse

sealed interface PixabayPhotoUiState {
    data object NoNetwork : PixabayPhotoUiState
    data object ShowProgress : PixabayPhotoUiState
    data class PixabayPhotoResponse(val pixabayApiResponse: PixabayApiResponse) : PixabayPhotoUiState
    data class Error(val errorResponse: ErrorResponse?) : PixabayPhotoUiState
}