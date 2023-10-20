package com.example.appiskey.network

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.ContinuationInterceptor

object NetworkExtensions {

    inline fun CoroutineScope.exceptionHandler(crossinline block: suspend (Throwable) -> Unit) =
        CoroutineExceptionHandler { _, exception ->
            val mainDispatcher =
                (coroutineContext[ContinuationInterceptor] as? MainCoroutineDispatcher
                    ?: Dispatchers.Main)
            exception.printStackTrace()
            launch(mainDispatcher.immediate) {
                val errorResponse = ErrorResponse()
                errorResponse.message = "Something went wrong"
                if (exception is UnknownHostException) {
                } else if (exception is SocketTimeoutException) {
                    errorResponse.message =
                        "Server is taking too long to respond. Please try again."
                } else
                    errorResponse.message =
                        (exception as? NetworkException)?.errorResponse?.message
                            ?: "Something went wrong"
                block(NetworkException(errorResponse))
            }
        }


    fun CoroutineScope.launchApi(
        exceptionHandler: CoroutineExceptionHandler?,
        block: suspend (CoroutineScope) -> Unit,
    ) =
        exceptionHandler?.let {
            this.launch(it) {
                block.invoke(this)
            }
        }

}