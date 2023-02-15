package com.example.jetpacktest02.utils

import android.annotation.SuppressLint
import android.app.Notification.VISIBILITY_PRIVATE
import android.app.Notification.VISIBILITY_PUBLIC
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.app.job.JobInfo.PRIORITY_DEFAULT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.example.jetpacktest02.R

const val MAIN_CHANNEL_ID = "MainChannel ID"
const val MAIN_CHANNEL = "MainChannel"

fun Context.buildNotification(
    id: Int,
    title: String,
    message: String,
    action: String? = null,
    actionMessage: String? = null,
    visibility: Int = VISIBILITY_PUBLIC,
    activityIntent: Intent? = null,
    isDeepLink: Boolean = false
): Notification {
    val notification = Notification(id, title, message, action, actionMessage,
        visibility, activityIntent,isDeepLink)
    notification.builder = notification.builder(this)
    notification.manager = getNotificationManager()
    return notification
}

data class Notification(
    val id: Int,
    var title: String,
    var message: String,
    var action: String? = null,
    var actionMessage: String? = null,
    var visibility: Int = VISIBILITY_PUBLIC,
    var activityIntent: Intent? = null,
    val isDeepLink: Boolean = false,
    var builder: android.app.Notification.Builder? = null,
    var manager: NotificationManagerCompat? = null
)

@SuppressLint("MissingPermission")
fun Notification.show(): Notification {
    builder?.let { manager?.notify(id, it.build()) }
    return this
}

@SuppressLint("MissingPermission")
fun Notification.update(
    context: Context,
    titleNew: String? = null,
    messageNew: String? = null,
    action1: String? = null,
    visibleType: Int = VISIBILITY_PUBLIC
): Notification  {
    titleNew?.let { title = titleNew }
    messageNew?.let { message = messageNew}
    action1?.let { action = action1  }
    if (visibleType != visibility) visibility = visibleType
    manager?.notify(id, builder(context).build())
    return this
}

fun Notification.builder(context: Context): android.app.Notification.Builder {
    val builder = android.app.Notification.Builder(context, MAIN_CHANNEL_ID)
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setPriority(android.app.Notification.PRIORITY_DEFAULT)
        .setVisibility(visibility)
        .setAutoCancel(true)
    if (visibility == VISIBILITY_PRIVATE) {
        builder.setPublicVersion(
            android.app.Notification.Builder(context, MAIN_CHANNEL_ID)
                .setContentTitle("收到一条新的消息")
                .setContentText("请解锁屏幕后查看！")
                .build()
        )
    }
    val flg = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
    action?.let {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(KEY_MESSAGE, actionMessage)
            putExtra(KEY_NOTIFICATION_ID, id)
        }
        PendingIntent.getBroadcast(context, 0, intent, flg)
    }?.let { builder.addAction(0, action, it) }

    if (isDeepLink) {
        activityIntent?.let {
            TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(it)
                getPendingIntent(1, flg)
            }
        }?.let { builder.setContentIntent(it) }
    } else {
        activityIntent?.let { PendingIntent.getActivity(context, 1, it, flg) }
            ?.let { builder.setContentIntent(it) }
    }
    return builder
}

fun Context.getNotificationManager(): NotificationManagerCompat {
    val notificationManager = NotificationManagerCompat.from(applicationContext)
    // API 26 Android 8.0开始必须为每个通知指定一个channel才会显示
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(MAIN_CHANNEL_ID, MAIN_CHANNEL,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }
    return notificationManager
}

fun Context.cancelNotification(id: Int) = getNotificationManager().cancel(id)
