package com.example.shoppingapp.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)
