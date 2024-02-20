package com.example.client.common.exception

data class ErrorResponse(
        val errorCode: String,
        val errorMessage: String?
)