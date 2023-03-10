package com.example.todolist.view.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.todolist.R

class NotificationHelper(base: Context?): ContextWrapper(base){
    private val channelID: String = "channelID"
    private val channelName: String = "channelName"

    init {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel: NotificationChannel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)

        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getManager().createNotificationChannel(channel)
    }

    fun getManager(): NotificationManager {
        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    fun getChannelNotification(title: String, message: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_background)
    }
}