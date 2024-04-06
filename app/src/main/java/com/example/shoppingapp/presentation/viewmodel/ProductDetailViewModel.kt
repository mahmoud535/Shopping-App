package com.example.shoppingapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.CartItem2
import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.domain.usecase.CartUseCase
import com.example.shoppingapp.domain.usecase.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel  @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val wishlistUseCase: WishlistUseCase
) : ViewModel() {

    fun saveToCart(cartItem2: CartItem2) = viewModelScope.launch(IO) {
        cartUseCase.addToCartItem(cartItem2)
    }

    fun addToWishlist(shopItem: ShopItem) = viewModelScope.launch(IO) {
        wishlistUseCase.addToWishlist(shopItem)
    }

    fun removeFromWishlist(shopItem: ShopItem) = viewModelScope.launch(IO) {
        wishlistUseCase.deleteWishlist(shopItem)
    }

}