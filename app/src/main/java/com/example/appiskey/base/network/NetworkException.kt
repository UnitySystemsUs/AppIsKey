package com.example.appiskey.base.network

class NetworkException(val errorResponse: ErrorResponse?=null)  : Exception()