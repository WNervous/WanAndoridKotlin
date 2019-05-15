package com.wys.wankotlinpractice.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.wys.wankotlinpractice.MainActivity
import com.wys.wankotlinpractice.NotificationActivity
import com.wys.wankotlinpractice.R

object NotificationUtils {

    fun showNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "test")
            .setSmallIcon(R.drawable.ic_about_us)
            .setContentTitle("title Test")
            .setContentText("this is test content")
            .build()
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        notification.contentIntent = pendingIntent
        manager.notify(1, notification)
    }

    fun showNotificationParent(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "test")
            .setSmallIcon(R.drawable.ic_about_us)
            .setContentTitle("title Test")
            .setContentText("this is test content")
            .build()
        val intent = Intent(context, NotificationActivity::class.java)
        val taskStack= TaskStackBuilder.create(context)
        taskStack.addParentStack(NotificationActivity::class.java)
        taskStack.addNextIntent(intent)
        val pendingIntent = taskStack.getPendingIntent(1,PendingIntent.FLAG_CANCEL_CURRENT)
        notification.contentIntent = pendingIntent

        manager.notify(1, notification)
    }
}