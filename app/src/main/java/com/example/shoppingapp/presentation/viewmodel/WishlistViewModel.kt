package com.example.shoppingapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.domain.usecase.WishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val wishlistUseCase: WishlistUseCase
) : ViewModel() {

    fun getWishlist() : Flow<List<ShopItem>> = flow  {
        wishlistUseCase.getWishlist().collect{
            emit(it)
        }
    }

    fun deleteWishlist(shopItem: ShopItem) = viewModelScope.launch(IO) {
        wishlistUseCase.deleteWishlist(shopItem)
    }

    fun clearWishlist() = viewModelScope.launch(IO) {
        wishlistUseCase.clearWishlist()
    }


}