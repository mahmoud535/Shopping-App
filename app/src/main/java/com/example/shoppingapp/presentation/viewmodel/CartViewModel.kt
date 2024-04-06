package com.example.shoppingapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.shoppingapp.data.model.CartItem2
import com.example.shoppingapp.data.util.Utils
import com.example.shoppingapp.domain.usecase.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    fun getCartItems() :  StateFlow<List<CartItem2>> {
        return cartUseCase.getCartItems().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun deleteCart(cartItem2: CartItem2) = viewModelScope.launch(IO) {
        cartUseCase.deleteCartItem(cartItem2)
    }

    fun clearCart() = viewModelScope.launch(IO) {
        cartUseCase.clearCart()
    }

    fun increment(cartItem: CartItem2) {
        updateProductInCart(quantity = cartItem.quantity + 1, price = cartItem.price.toDouble() + cartItem.pricePerItem, cartItem)
    }

    fun decrement(cartItem: CartItem2) {
        if (cartItem.quantity > 1) {
            updateProductInCart(quantity = cartItem.quantity - 1, price = cartItem.price.toDouble() - cartItem.pricePerItem, cartItem)
        }
    }

    private fun updateProductInCart(quantity: Int, price: Double, cartItem: CartItem2) = viewModelScope.launch(IO) {
        val copy = cartItem.copy(price = Utils.formatPrice(price.toString()), quantity = quantity)
        cartUseCase.updateCartItem(copy)
    }
}