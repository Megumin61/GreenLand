package com.example.jetpacktest02.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.jetpacktest02.config.UsersApplication

const val KEY_MESSAGE = "Notification_Message"
const val KEY_NOTIFICATION_ID = "Notification_Id"

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.run {
            val msg = getStringExtra(KEY_MESSAGE)
            msg?.let { Toast.makeText(UsersApplication.context,msg, Toast.LENGTH_SHORT).show() }
            val id = getIntExtra(KEY_NOTIFICATION_ID, 0)
            context?.cancelNotification(id) // 根据需求决定要不要取消
        }
    }
}
