package com.example.shoppingapp.presentation.di.datasource

import com.example.shoppingapp.data.api.ShopApiService
import com.example.shoppingapp.data.db.ShopDAO
import com.example.shoppingapp.domain.repository.datasource.localds.ShopLocalDataSource
import com.example.shoppingapp.domain.repository.datasource.remoteds.ShopRemoteDataSource
import com.example.shoppingapp.data.repository.datasourceImpl.localds.ShopLocalDataSourceImp
import com.example.shoppingapp.data.repository.datasourceImpl.remoteds.ShopRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal object DataSourceModule {

    @Provides
    fun providesLocalDataSource(shopDAO: ShopDAO) : ShopLocalDataSource {
        return ShopLocalDataSourceImp(shopDAO)
    }

    @Provides
    fun provideShopRemoteDataSource(shopApiService: ShopApiService) : ShopRemoteDataSource {
        return ShopRemoteDataSourceImp(shopApiService)
    }

}