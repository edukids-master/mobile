package itu.m1.edukids.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.model.User
import itu.m1.edukids.service.ApiService
import kotlinx.coroutines.launch

class UserController : ViewModel() {
    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _user = MutableLiveData<User>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val user: LiveData<User> = _user

    init {
        login()
    }

    private fun login() {
        viewModelScope.launch {
            try {
                _user.value = ApiService.retrofitService.connexion(User("mario.safidy", "testing123+"))
                user.value?.let { Log.d("LOGIN", it.login) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}