package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.data.model.Cart
import com.example.shoppingapp.data.model.CartItem
import com.example.shoppingapp.data.model.CartItem2
import com.example.shoppingapp.data.model.Category
import com.example.shoppingapp.data.model.Product
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.data.model.User
import com.example.shoppingapp.common.util.Resource
import kotlinx.coroutines.flow.Flow


 interface ShopRepository {

    suspend fun getAllProducts() : Resource<Shop>
    suspend fun getProduct(itemId : Int) : Resource<ShopItem>
    suspend fun getAllCategories() : Resource<Category>
    suspend fun getCategoryProducts(category : String) : Resource<Shop>
    suspend fun uploadProduct(shopItem : ShopItem) : Resource<ShopItem>
    suspend fun updateProduct(id : Int, shopItem : ShopItem) : Resource<ShopItem>
    suspend fun deleteProduct(id : Int) : Resource<ShopItem>
    suspend fun getCart(id : Int) : Resource<Cart>
    suspend fun getCartProducts(id : Int) : Resource<List<Product>>
    suspend fun addToCart(cartItem : CartItem) : Resource<CartItem>
    suspend fun updateCart(id : Int, cartItem: CartItem) : Resource<CartItem>
    suspend fun deleteCart(id : Int) : Resource<CartItem>
    suspend fun getUser(id : Int) : Resource<User>
    suspend fun updateUser(id : Int,user: User) : Resource<User>

    suspend fun addToCartItems(cartItem2: CartItem2)
    fun getCartItems() : Flow<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart()

    suspend fun addToWishlist(shopItem: ShopItem)
    fun getWishlistItems() : Flow<List<ShopItem>>
    suspend fun deleteWishlistItem(shopItem: ShopItem)
    suspend fun clearWishlist()

}