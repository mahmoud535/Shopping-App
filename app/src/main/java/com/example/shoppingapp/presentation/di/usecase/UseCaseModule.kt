package com.example.shoppingapp.presentation.di.usecase

import com.example.shoppingapp.domain.repository.ShopRepository
import com.example.shoppingapp.domain.usecase.CartUseCase
import com.example.shoppingapp.domain.usecase.ProductUseCase
import com.example.shoppingapp.domain.usecase.ProfileUseCase
import com.example.shoppingapp.domain.usecase.WishlistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {


    @Provides
    fun providesCartUseCase(repository: ShopRepository) : CartUseCase {
        return CartUseCase(repository)
    }

    @Provides
    fun providesProductUseCase(repository: ShopRepository) : ProductUseCase {
        return ProductUseCase(repository)
    }

    @Provides
    fun providesProfileUseCase(repository: ShopRepository) : ProfileUseCase {
        return ProfileUseCase(repository)
    }

    @Provides
    fun providesWishListUseCase(repository: ShopRepository) : WishlistUseCase {
        return WishlistUseCase(repository)
    }


}