package itu.m1.edukids.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.model.LocalAccess
import itu.m1.edukids.model.User
import itu.m1.edukids.service.ApiService
import itu.m1.edukids.view.GameListActivity
import kotlinx.coroutines.launch

class UserController : MainViewModel() {
    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _user = MutableLiveData<User>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val user: LiveData<User> = _user
    lateinit var userSelect : User


    fun connexion(ctx: Context,  user: User, fonction : () -> Unit): Boolean {
        var res = false
        try{
            localAccess = LocalAccess(ctx)
            viewModelScope.launch {
                val response = ApiService.userService.connexion(user)
                fonction()
                if(response.isSuccessful) {
                    _user.value = response.body()
                    _user.value?.let {
                        localAccess.ajout(it)
                    }
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

    companion object{
        lateinit var localAccess: LocalAccess
        private var instance : UserController? = null
        fun getInstance(contexte: Context) : UserController? {
            if(instance == null){
                instance = UserController()
                localAccess = LocalAccess(contexte)
            }
            return instance
        }


    }

}