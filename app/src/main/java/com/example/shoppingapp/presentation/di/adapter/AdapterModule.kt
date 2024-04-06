package com.example.shoppingapp.presentation.di.adapter

import com.example.shoppingapp.presentation.adapter.CartAdapter
import com.example.shoppingapp.presentation.adapter.HomeAdapter
import com.example.shoppingapp.presentation.adapter.SearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Singleton
    @Provides
    fun providesHomeAdapter() : HomeAdapter {
        return HomeAdapter()
    }

    @Singleton
    @Provides
    fun providesCartAdapter() : CartAdapter {
        return CartAdapter()
    }

    @Singleton
    @Provides
    fun providesSearchAdapter() : SearchAdapter {
        return SearchAdapter()
    }



}