package com.example.shoppingapp.data.util


object Utils {

    fun formatPrice(price : String): String {
        return String.format("%.2f", price.toDouble())
    }

}