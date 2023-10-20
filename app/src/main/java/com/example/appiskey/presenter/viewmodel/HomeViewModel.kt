package com.example.appiskey.presenter.viewmodel

import android.content.Context
import androidx.annotation.Keep
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appiskey.model.PixabayApiResponse
import com.example.appiskey.network.ErrorResponse
import com.example.appiskey.network.NetworkException
import com.example.appiskey.network.NetworkExtensions.exceptionHandler
import com.example.appiskey.network.NetworkExtensions.launchApi
import com.example.appiskey.domain.HomeUseCase
import com.example.appiskey.uistate.HomeUiState
import com.example.appiskey.utils.OneShotEvent
import com.example.appiskey.utils.appendRequestParam
import com.example.appiskey.utils.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@Keep
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private var homeJob: Job? = null
    private var _homeUiState = MutableLiveData<OneShotEvent<HomeUiState>>()
    val homeUiState: MutableLiveData<OneShotEvent<HomeUiState>> get() = _homeUiState

    fun hitApi(context: Context, map: HashMap<String, Any>) {
        if (!context.isInternetAvailable()) {
            _homeUiState.value = OneShotEvent(HomeUiState.NoNetwork)
            return
        }
        if (homeJob?.isActive == true) homeJob?.cancel()
        _homeUiState.value = OneShotEvent(HomeUiState.ShowProgress)
        val exceptionHandler = viewModelScope.exceptionHandler {
            if (it is NetworkException)
                _homeUiState.value = OneShotEvent(HomeUiState.Error(it.errorResponse))
        }
        homeJob = viewModelScope.launchApi(exceptionHandler) {
            val response = homeUseCase.hitApi(map.appendRequestParam())
            handleHitResponse(response)
        }
    }

    private fun handleHitResponse(response: PixabayApiResponse?) {
        response?.let {
            _homeUiState.value = OneShotEvent(HomeUiState.HitResponse(it))
        } ?: kotlin.run {
            _homeUiState.value = OneShotEvent(HomeUiState.Error(ErrorResponse()))
        }
    }

}