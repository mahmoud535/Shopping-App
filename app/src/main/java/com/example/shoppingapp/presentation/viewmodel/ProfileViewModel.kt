package com.example.shoppingapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.model.User
import com.example.shoppingapp.common.util.Resource
import com.example.shoppingapp.data.util.SharedPreference
import com.example.shoppingapp.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val sharedPreference: SharedPreference
) : ViewModel() {

    private val _user = MutableStateFlow<Resource<User>>(Resource.Loading())
    val user:StateFlow<Resource<User>> = _user

    fun getUser(id : Int) = viewModelScope.launch(IO) {
        _user.emit(Resource.Loading())
        try {
            val apiResult = profileUseCase.getUser(id)
            _user.emit(apiResult)
        }catch (e : Exception){
            _user.emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }

    fun logoutUser() = viewModelScope.launch {
        sharedPreference.deleteAccessToken()
    }


}