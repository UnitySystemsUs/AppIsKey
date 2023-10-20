package com.example.appiskey.base.network

import android.util.Log
import com.example.appiskey.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiClient @Inject constructor() {

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request()

        Log.d("ApiFactory", "Base Url : ${request.url}")

        val response = chain.proceed(request)
        val rawJson = response.body?.string() ?: ""
        val isDecrypted = rawJson.trim().startsWith("{") && rawJson.trim().endsWith("}")
        val responseBuilder = response.newBuilder()
        if (isDecrypted) {
            responseBuilder.body(rawJson.toResponseBody(response.body?.contentType()))
        }
        responseBuilder.build()
    }

    val retrofit: Retrofit by lazy {
        Log.d("AppIsKey", "BaseUrl: ${BuildConfig.BASE_URL}")
        Retrofit.Builder()
            .client(apiClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val apiClient = getOkHttpBuilder().build()

    private fun getOkHttpBuilder(): OkHttpClient.Builder {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        builder.addInterceptor(authInterceptor)
        builder.addInterceptor(HttpLoggingInterceptor())
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        builder.connectTimeout(1, TimeUnit.MINUTES)
        builder.writeTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(1, TimeUnit.MINUTES)
        return builder
    }

    var gson = GsonBuilder()
        .setLenient()
        .serializeNulls()
        .serializeSpecialFloatingPointValues()
        .create()

}