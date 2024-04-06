package com.example.shoppingapp.common.di.db

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.example.shoppingapp.data.db.Converters
import com.example.shoppingapp.data.db.ShopDAO
import com.example.shoppingapp.data.db.ShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesShopDatabase(app : Application,gson : Gson) : ShopDatabase {
        return Room.databaseBuilder(app, ShopDatabase::class.java,"shop_database")
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converters(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesShopDao(database: ShopDatabase) : ShopDAO {
        return database.shopDao()
    }

}