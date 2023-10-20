package com.example.appiskey.photolisting.presenter.viewmodel

import android.content.Context
import androidx.annotation.Keep
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appiskey.base.network.ErrorResponse
import com.example.appiskey.base.network.NetworkException
import com.example.appiskey.base.network.NetworkExtensions.exceptionHandler
import com.example.appiskey.base.network.NetworkExtensions.launchApi
import com.example.appiskey.photolisting.domain.PixabayPhotoUseCase
import com.example.appiskey.photolisting.model.PixabayApiResponse
import com.example.appiskey.photolisting.uistate.PixabayPhotoUiState
import com.example.appiskey.utils.OneShotEvent
import com.example.appiskey.utils.appendRequestParam
import com.example.appiskey.utils.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@Keep
@HiltViewModel
class PixabayPhotoViewModel @Inject constructor(private val pixabayPhotoUseCase: PixabayPhotoUseCase) :
    ViewModel() {

    private var pixabayPhotoJob: Job? = null
    private var _pixabayPhotoUiState = MutableLiveData<OneShotEvent<PixabayPhotoUiState>>()
    val pixabayPhotoUiState: MutableLiveData<OneShotEvent<PixabayPhotoUiState>> get() = _pixabayPhotoUiState

    fun hitApi(context: Context, map: HashMap<String, Any>) {
        if (!context.isInternetAvailable()) {
            _pixabayPhotoUiState.value = OneShotEvent(PixabayPhotoUiState.NoNetwork)
            return
        }
        if (pixabayPhotoJob?.isActive == true) pixabayPhotoJob?.cancel()
        _pixabayPhotoUiState.value = OneShotEvent(PixabayPhotoUiState.ShowProgress)
        val exceptionHandler = viewModelScope.exceptionHandler {
            if (it is NetworkException)
                _pixabayPhotoUiState.value = OneShotEvent(PixabayPhotoUiState.Error(it.errorResponse))
        }
        pixabayPhotoJob = viewModelScope.launchApi(exceptionHandler) {
            val response = pixabayPhotoUseCase.getPixabayPhotos(map.appendRequestParam())
            handleHitResponse(response)
        }
    }

    private fun handleHitResponse(response: PixabayApiResponse?) {
        response?.let {
            _pixabayPhotoUiState.value = OneShotEvent(PixabayPhotoUiState.PixabayPhotoResponse(it))
        } ?: kotlin.run {
            _pixabayPhotoUiState.value = OneShotEvent(PixabayPhotoUiState.Error(ErrorResponse()))
        }
    }

}