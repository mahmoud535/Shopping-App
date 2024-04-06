package com.example.shoppingapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.db.Converters
import com.example.shoppingapp.data.db.ShopDAO
import com.example.shoppingapp.data.model.CartItem2
import com.example.shoppingapp.data.model.ShopItem

@Database(entities = [CartItem2::class, ShopItem::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShopDatabase : RoomDatabase(){

    abstract fun shopDao() : ShopDAO

}