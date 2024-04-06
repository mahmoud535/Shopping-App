package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.data.model.User
import com.example.shoppingapp.common.util.Resource
import com.example.shoppingapp.domain.repository.ShopRepository

class ProfileUseCase (private val repository: ShopRepository) {

    suspend fun getUser(id : Int) : Resource<User> {
        return repository.getUser(id)
    }

    suspend fun updateUser(id : Int,user: User): Resource<User> {
        return repository.updateUser(id,user)
    }

}