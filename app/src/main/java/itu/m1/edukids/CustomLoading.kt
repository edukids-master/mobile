package itu.m1.edukids

import android.app.Activity
import android.app.AlertDialog

class CustomLoading(var activity : Activity) {
    lateinit var alertDialog: AlertDialog

    fun startLoading(){
        var builder = AlertDialog.Builder(activity)
        var inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_loading,null))
        builder.setCancelable(false)
        alertDialog = builder.create()
        alertDialog.show()
    }

    fun hideLoading(){
        alertDialog.dismiss()
    }
}