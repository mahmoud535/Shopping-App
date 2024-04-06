package com.example.shoppingapp.data.repository.datasourceImpl.remoteds

import com.example.shoppingapp.data.api.ShopApiService
import com.example.shoppingapp.data.model.Cart
import com.example.shoppingapp.data.model.CartItem
import com.example.shoppingapp.data.model.Category
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.data.model.User
import com.example.shoppingapp.domain.repository.datasource.remoteds.ShopRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

internal class ShopRemoteDataSourceImp (
    private val apiService: ShopApiService
) : ShopRemoteDataSource {
    override suspend fun getAllProducts(): Response<Shop> {
        return apiService.getAllProducts()
    }

    override suspend fun getProduct(itemId: Int): Response<ShopItem> {
        return apiService.getProduct(itemId)
    }

    override suspend fun getAllCategories(): Response<Category> {
        return apiService.getAllCategories()
    }

    override suspend fun getCategoryProducts(category: String): Response<Shop> {
        return apiService.getCategoryProducts(category)
    }

    override suspend fun uploadProduct(shopItem: ShopItem): Response<ShopItem> {
        return apiService.uploadProduct(shopItem)
    }

    override suspend fun updateProduct(id: Int, shopItem: ShopItem): Response<ShopItem> {
        return apiService.updateProduct(id,shopItem)
    }

    override suspend fun deleteProduct(id: Int): Response<ShopItem> {
        return apiService.deleteProduct(id)
    }

    override suspend fun getCart(id: Int): Response<Cart> {
        return apiService.getCart(id)
    }

    override suspend fun getCartProducts(id: Int): Response<CartItem> {
        return apiService.getCartProducts(id)
    }

    override suspend fun addToCart(cartItem: CartItem): Response<CartItem> {
        return apiService.addToCart(cartItem)
    }

    override suspend fun updateCart(id: Int, cartItem: CartItem): Response<CartItem> {
        return apiService.updateCart(id,cartItem)
    }

    override suspend fun deleteCart(id: Int): Response<CartItem> {
        return apiService.deleteCart(id)
    }

    override suspend fun getUser(id: Int): Response<User> {
        return apiService.getUser(id)
    }

    override suspend fun updateUser(id: Int,user: User): Response<User> {
        return apiService.updateUser(id,user)
    }

}