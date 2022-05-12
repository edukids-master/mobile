package itu.m1.edukids.controller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itu.m1.edukids.model.Activities
import itu.m1.edukids.service.ApiService
import kotlinx.coroutines.launch
import org.json.JSONObject

class ActiviteController : ViewModel() {
    private val _activities = MutableLiveData<List<Activities>>()
    val activities: LiveData<List<Activities>> = _activities

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getErrorMessage(raw: String): String {
        val json = JSONObject(raw)
        return json.getString("message")
    }

    init {
        getActivities()
    }

    private fun getActivities(){
        viewModelScope.launch {
            val response = ApiService.activitiesService.getActivities()

            if(response.isSuccessful) {
                _activities.value = response.body()
            } else {
                val errorMessage = getErrorMessage(response.errorBody()!!.string())
                _error.value = errorMessage
            }
        }
    }
}