package itu.m1.edukids.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.model.User
import itu.m1.edukids.service.ApiService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject

class UserController : ViewModel() {
    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _user = MutableLiveData<User>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val user: LiveData<User> = _user

    private var _error = MutableLiveData<String>()

    val error: LiveData<String> = _error

    fun getErrorMessage(raw: String): String {
        val json = JSONObject(raw)
        return json.getString("message")
    }

    fun connexion(user: User) {
        viewModelScope.launch {
            val response = ApiService.userService.connexion(user)

            if(response.isSuccessful) {
                _user.value = response.body()
            } else {
                val errorMessage = getErrorMessage(response.errorBody()!!.string())
                Log.e("ERROR", errorMessage)
                _error.value = errorMessage
            }
        }
    }
}