package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.model.Cart
import com.example.shoppingapp.data.model.CartItem
import com.example.shoppingapp.data.model.CartItem2
import com.example.shoppingapp.data.model.Category
import com.example.shoppingapp.data.model.Product
import com.example.shoppingapp.data.model.Shop
import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.data.model.User
import com.example.shoppingapp.domain.repository.datasource.localds.ShopLocalDataSource
import com.example.shoppingapp.domain.repository.datasource.remoteds.ShopRemoteDataSource
import com.example.shoppingapp.common.util.Resource
import com.example.shoppingapp.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

internal class ShopRepositoryImp @Inject constructor(
    private val remoteDataSource: ShopRemoteDataSource,
    private val localDataSource: ShopLocalDataSource
) : ShopRepository {


    override suspend fun getAllProducts(): Resource<Shop> {
        return responseToShopResult(remoteDataSource.getAllProducts())
    }

    override suspend fun getProduct(itemId: Int): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.getProduct(itemId))
    }

    override suspend fun getAllCategories(): Resource<Category> {
        return responseToCategoryResult(remoteDataSource.getAllCategories())
    }

    override suspend fun getCategoryProducts(category: String): Resource<Shop> {
        return responseToShopResult(remoteDataSource.getCategoryProducts(category))
    }

    override suspend fun uploadProduct(shopItem: ShopItem): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.uploadProduct(shopItem))
    }

    override suspend fun updateProduct(id: Int, shopItem: ShopItem): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.updateProduct(id,shopItem))
    }

    override suspend fun deleteProduct(id: Int): Resource<ShopItem> {
        return responseToShopItemResult(remoteDataSource.deleteProduct(id))
    }

    override suspend fun getCart(id: Int): Resource<Cart> {
        return responseToCartResult(remoteDataSource.getCart(id))
    }

    override suspend fun getCartProducts(id: Int): Resource<List<Product>> {
        return responseToCartProducts(remoteDataSource.getCartProducts(id))
    }

    override suspend fun addToCart(cartItem: CartItem): Resource<CartItem> {
        return responseToCartItemResult(remoteDataSource.addToCart(cartItem))
    }

    override suspend fun updateCart(id: Int, cartItem: CartItem): Resource<CartItem> {
        return responseToCartItemResult(remoteDataSource.updateCart(id,cartItem))
    }

    override suspend fun deleteCart(id: Int): Resource<CartItem> {
        return responseToCartItemResult(remoteDataSource.deleteCart(id))
    }

    override suspend fun getUser(id: Int): Resource<User> {
        return responseToUserResult(remoteDataSource.getUser(id))
    }

    override suspend fun updateUser(id: Int,user: User): Resource<User> {
        return responseToUserResult(remoteDataSource.updateUser(id,user))
    }

    override suspend fun addToCartItems(cartItem2: CartItem2) {
        return localDataSource.addToCart(cartItem2)
    }

    override fun getCartItems(): Flow<List<CartItem2>> {
        return localDataSource.getCartItems()
    }

    override suspend fun updateCartItems(cartItem2: CartItem2) {
        return localDataSource.updateCartItems(cartItem2)
    }

    override suspend fun deleteCartItems(cartItem2: CartItem2) {
        return localDataSource.deleteCartItems(cartItem2)
    }

    override suspend fun clearCart() {
        return localDataSource.clearCart()
    }

    override suspend fun addToWishlist(shopItem: ShopItem) {
        return localDataSource.addToWishlist(shopItem)
    }

    override fun getWishlistItems(): Flow<List<ShopItem>> {
        return localDataSource.getWishlistItems()
    }

    override suspend fun deleteWishlistItem(shopItem: ShopItem) {
        return localDataSource.deleteWishlistItem(shopItem)
    }

    override suspend fun clearWishlist() {
        return localDataSource.clearWishlist()
    }

    private fun responseToCartProducts(response : Response<CartItem>) : Resource<List<Product>> {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it.products)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToShopResult(response: Response<Shop>) : Resource<Shop> {
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToUserResult(response : Response<User>) : Resource<User> {
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToShopItemResult(response: Response<ShopItem>) : Resource<ShopItem> {
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToCartResult(response: Response<Cart>) : Resource<Cart> {
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToCartItemResult(response: Response<CartItem>) : Resource<CartItem> {
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }

    private fun responseToCategoryResult(response: Response<Category>) : Resource<Category> {
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }



}