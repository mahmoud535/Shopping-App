package com.example.shoppingapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.Category
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.data.util.Network.isNetworkAvailable
import com.example.shoppingapp.common.util.Resource
import com.example.shoppingapp.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val productUseCase: ProductUseCase
) : AndroidViewModel(app) {

    private val _products = MutableStateFlow<Resource<Shop>>(Resource.Loading())
    val products: StateFlow<Resource<Shop>> = _products

    private val _categories = MutableStateFlow<Resource<Category>>(Resource.Loading())
    val categories: StateFlow<Resource<Category>> = _categories


     fun getAllCategories() = viewModelScope.launch(IO) {
         _categories.value = Resource.Loading()
         try {
             if (isNetworkAvailable(app)) {
                 val apiResult = productUseCase.getAllCategories()
                 _categories.value = apiResult
             } else {
                 _categories.value = Resource.Error(message = "Internet not available")
             }
         } catch (e: Exception) {
             _categories.value =
                 Resource.Error(message = "${e.localizedMessage} ?: Unknown Error")
         }
    }

     fun getAllProducts() = viewModelScope.launch(IO) {
         _products.value = Resource.Loading()
         try {
             if (isNetworkAvailable(app)) {
                 val apiResult = productUseCase.getAllProducts()
                 _products.value = apiResult
             } else {
                 _products.value = Resource.Error(message = "Internet not available")
             }
         } catch (e: Exception) {
             _products.value = Resource.Error(message = e.localizedMessage ?: "Unknown Error")
         }
    }

    fun getCategoryProducts(category: String) = viewModelScope.launch(IO) {
        if (category != "All") {
            _products.value = Resource.Loading()
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = productUseCase.getCategoryProducts(category)
                    _products.value = apiResult
                } else {
                    _products.value = Resource.Error(message = "Internet not available")
                }
            } catch (e: Exception) {
                _products.value = Resource.Error(message = e.localizedMessage ?: "Unknown Error")
            }
        } else {
            getAllProducts()
        }
    }

}