package com.example.appiskey.base.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @field:SerializedName("http_response")
    var httpResponse : Int = 0,

    @field:SerializedName("code")
    var code: String = "",

    @field:SerializedName("success")
    var success: Boolean = false,

    @field:SerializedName("cmd")
    var cmd: String = "",

    @field:SerializedName("message")
    var message: String = ""

)