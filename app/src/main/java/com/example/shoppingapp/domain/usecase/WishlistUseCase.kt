package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.data.model.ShopItem
import com.example.shoppingapp.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistUseCase (
    private val repository: ShopRepository
) {

    suspend fun addToWishlist(shopItem: ShopItem){
        repository.addToWishlist(shopItem)
    }

    suspend fun deleteWishlist(shopItem: ShopItem){
        return repository.deleteWishlistItem(shopItem)
    }

    fun getWishlist() : Flow<List<ShopItem>>{
        return repository.getWishlistItems()
    }

    suspend fun clearWishlist(){
        return repository.clearWishlist()
    }

}