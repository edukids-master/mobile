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

    private var _error = MutableLiveData<String>()

    val error: LiveData<String> = _error

    fun connexion(user: User) {
        viewModelScope.launch {
            try {
                _user.value = ApiService.userService.connexion(user)
                _user.value?.let { Log.d("LOGIN", it.login) }
            } catch (e: Exception) {
                _error.value = e.message
                throw e
            }
        }
    }
}