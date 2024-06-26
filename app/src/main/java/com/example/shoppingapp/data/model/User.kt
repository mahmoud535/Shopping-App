package com.example.shoppingapp.data.model


import com.example.shoppingapp.data.model.Address
import com.example.shoppingapp.data.model.Name
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User(
    @SerializedName("address")
    val address: Address,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: Name,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("__v")
    val v: Int
) : Serializable