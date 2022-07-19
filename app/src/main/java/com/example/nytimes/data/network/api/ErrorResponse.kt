package com.example.nytimes.data.network.api

data class ErrorResponse(
    val error_description: String,
    val causes: Map<String, String> = emptyMap()
)