package com.example.shoppingapp.presentation.di.repo

import com.example.shoppingapp.data.repository.ShopRepositoryImp
import com.example.shoppingapp.domain.repository.datasource.localds.ShopLocalDataSource
import com.example.shoppingapp.domain.repository.datasource.remoteds.ShopRemoteDataSource
import com.example.shoppingapp.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object RepositoryModule {

    @Provides
    fun providesShopRepository(shopRemoteDataSource: ShopRemoteDataSource, localDataSource: ShopLocalDataSource) : ShopRepository {
        return ShopRepositoryImp(shopRemoteDataSource,localDataSource)
    }

}