package itu.m1.edukids.controller

import androidx.lifecycle.ViewModel
import org.json.JSONObject

open class MainViewModel: ViewModel() {
    fun getErrorMessage(raw: String): String {
        val json = JSONObject(raw)
        return json.getString("message")
    }
}