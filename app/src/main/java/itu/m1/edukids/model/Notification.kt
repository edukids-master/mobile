package itu.m1.edukids.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.Activity
import android.app.Notification
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import itu.m1.edukids.R

object Notification {
    const val name = "Edukids"

    fun createNotificationChannel(activity: AppCompatActivity) {
        val notificationManager: NotificationManager =
            activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = name
            val descriptionText = "Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("channelID", name, importance).apply {
                description = descriptionText
                enableVibration(true)
                enableLights(true)
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notify(ctx: Context, lambda: (builder: NotificationCompat.Builder) -> Unit) {
        val intent = Intent(ctx, NotificationBroadcaster::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(ctx, "channelID")
            .setSmallIcon(R.mipmap.edukid)
            .setContentTitle(name)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        lambda(builder)

        with(NotificationManagerCompat.from(ctx)) {
            notify(1, builder.build())
        }
    }
}