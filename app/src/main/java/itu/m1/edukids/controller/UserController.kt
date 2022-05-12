package itu.m1.edukids.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.model.User
import itu.m1.edukids.service.ApiService
import itu.m1.edukids.view.GameListActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject

class UserController : MainViewModel() {
    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _user = MutableLiveData<User>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val user: LiveData<User> = _user

    fun connexion(ctx: Context,  user: User): Boolean {
        var res = false
        try{
            viewModelScope.launch {
                val response = ApiService.userService.connexion(user)

                if(response.isSuccessful) {
                    _user.value = response.body()
                    val intent = Intent(ctx, GameListActivity::class.java)
                    ctx.startActivity(intent)
                } else {
                    val errorMessage = getErrorMessage(response.errorBody()!!.string())
                    Log.e("ERROR", errorMessage)
                    createError(errorMessage)
                }
            }
        }catch(error: Error) {
            createError("Veuillez nous excusez, une erreur inattendue est survenue")
        }
        return res
    }
}