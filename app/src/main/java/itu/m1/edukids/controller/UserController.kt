package itu.m1.edukids.controller

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
    private val _users = MutableLiveData<User>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val users: LiveData<User> = _users

    init {
        login()
    }

    private fun login(){
        viewModelScope.launch {
            try {
                _users.value = ApiService.retrofitService.connexion()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}