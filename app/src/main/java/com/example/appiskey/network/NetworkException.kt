package com.example.appiskey.network

class NetworkException(val errorResponse: ErrorResponse?=null)  : Exception()