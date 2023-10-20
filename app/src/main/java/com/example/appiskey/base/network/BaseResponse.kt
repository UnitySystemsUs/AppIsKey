package com.example.appiskey.base.network

import com.google.gson.annotations.SerializedName

abstract class BaseResponse(
    @SerializedName("http_response") val httpResponse: Int? = 0,
    @SerializedName("cmd") val cmd: String? = "",
    @SerializedName("code") val code: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("success") val success: String? = "false"
)