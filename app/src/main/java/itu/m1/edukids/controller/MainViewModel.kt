package itu.m1.edukids.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject

open class MainViewModel: ViewModel() {
    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getErrorMessage(raw: String): String {
        val json = JSONObject(raw)
        return json.getString("message")
    }

    fun createError(message: String) {
        _error.value = message
    }
}