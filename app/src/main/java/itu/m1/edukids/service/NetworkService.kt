package itu.m1.edukids.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

object NetworkService {
    var isOnline: Boolean = false
    var alert: AlertDialog? = null
    var currentActivity: AppCompatActivity? = null

    fun checkCurrentNetworkStatus() {
        if (!isOnline) {
            createAlertDialog()
        }
    }

    fun register(activity: AppCompatActivity) {
        currentActivity = activity
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isInternetAvailable(connectivityManager: ConnectivityManager): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun createAlertDialog() {
        alert = currentActivity?.let {
            AlertDialog.Builder(it)
                .setTitle("Vous êtes actuellement hors ligne.")
                .setMessage("Vérifiez votre connexion à internet puis réessayer")
                .setCancelable(false).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun checkInternetConnectionOnBackground() {
        val connectivityManager =
            currentActivity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        isOnline = isInternetAvailable(connectivityManager)

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isOnline = true
                if (alert?.isShowing == true) alert?.dismiss()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isOnline = false
                createAlertDialog()
            }
        })

        checkCurrentNetworkStatus()
    }
}