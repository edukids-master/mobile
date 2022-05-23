package itu.m1.edukids.model

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import itu.m1.edukids.R

const val notificationID = 1
const val channelID = "channel1"
const val titleExtra = "TitleExtra"
const val messageExtra = "MessageExtra"


class NotificationBroadcaster : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
//        val notification = NotificationCompat.Builder(context, channelID)
//            .setSmallIcon(R.mipmap.edukid)
//            .setContentTitle(intent.getStringExtra(titleExtra))
//            .setContentText(intent.getStringExtra(messageExtra))
//            .build()
//
//        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        manager.notify(notificationID, notification)
    }


}