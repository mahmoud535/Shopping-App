package com.example.shoppingapp.domain.repository.datasource.remoteds

import com.example.shoppingapp.data.model.Cart
import com.example.shoppingapp.data.model.CartItem
import com.example.shoppingapp.data.model.Category
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.data.model.User
import retrofit2.Response

internal interface ShopRemoteDataSource {

    suspend fun getAllProducts() : Response<Shop>
    suspend fun getProduct(itemId : Int) : Response<ShopItem>
    suspend fun getAllCategories() : Response<Category>
    suspend fun getCategoryProducts(category : String) : Response<Shop>
    suspend fun uploadProduct(shopItem : ShopItem) : Response<ShopItem>
    suspend fun updateProduct(id : Int, shopItem : ShopItem) : Response<ShopItem>
    suspend fun deleteProduct(id : Int) : Response<ShopItem>
    suspend fun getCart(id : Int) : Response<Cart>
    suspend fun getCartProducts(id : Int) : Response<CartItem>
    suspend fun addToCart(cartItem : CartItem) : Response<CartItem>
    suspend fun updateCart(id : Int, cartItem: CartItem) : Response<CartItem>
    suspend fun deleteCart(id : Int) : Response<CartItem>
    suspend fun getUser(id : Int) : Response<User>
    suspend fun updateUser(id : Int,user: User) : Response<User>

}