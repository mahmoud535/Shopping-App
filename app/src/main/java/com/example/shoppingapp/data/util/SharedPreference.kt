package com.example.shoppingapp.data.util

import android.content.SharedPreferences
import com.example.shoppingapp.common.util.Constants
import javax.inject.Inject

class SharedPreference @Inject constructor(
    private val sharedPreferences : SharedPreferences
) {

    fun userIsLoggedIn() : Boolean {
        val token = sharedPreferences.getString(Constants.USER_IS_LOGGED_IN, null)
        return token != null
    }

    fun deleteAccessToken(): Boolean {
        sharedPreferences.edit().remove(Constants.USER_IS_LOGGED_IN).apply()
        return userIsLoggedIn()
    }
}