package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.data.model.Category
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.common.util.Resource
import com.example.shoppingapp.domain.repository.ShopRepository

class ProductUseCase (
    private val repository: ShopRepository
) {

    suspend fun getAllProducts() : Resource<Shop> {
        return repository.getAllProducts()
    }

    suspend fun getAllCategories() : Resource<Category> {
        return repository.getAllCategories()
    }

    suspend fun getCategoryProducts(category : String) : Resource<Shop> {
        return repository.getCategoryProducts(category)
    }

}