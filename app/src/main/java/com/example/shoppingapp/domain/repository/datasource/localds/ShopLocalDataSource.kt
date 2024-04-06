package com.example.shoppingapp.domain.repository.datasource.localds

import com.example.shoppingapp.data.model.CartItem2
import com.example.shoppingapp.data.model.ShopItem
import kotlinx.coroutines.flow.Flow

internal interface ShopLocalDataSource {

    suspend fun addToCart(cartItem2: CartItem2)
    fun getCartItems() : Flow<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart()
    suspend fun addToWishlist(shopItem: ShopItem)
    fun getWishlistItems() : Flow<List<ShopItem>>
    suspend fun deleteWishlistItem(shopItem: ShopItem)
    suspend fun clearWishlist()

}