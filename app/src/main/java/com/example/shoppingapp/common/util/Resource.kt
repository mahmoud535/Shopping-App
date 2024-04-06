package com.example.shoppingapp.common.util


sealed class Resource<Model> {
    data class Success<Model>(val data: Model) : Resource<Model>()
    data class Loading<Model>(val loading: Boolean = true) : Resource<Model>()
    data class Error<Model>(val message: String) : Resource<Model>()
}
