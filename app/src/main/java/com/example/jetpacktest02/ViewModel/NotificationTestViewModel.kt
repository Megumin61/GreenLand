package com.example.jetpacktest02.ViewModel

import android.app.Notification.VISIBILITY_PUBLIC
import android.content.Context
import android.content.Intent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import com.example.jetpacktest02.LockScreenActivity
import com.example.jetpacktest02.RallyActivity
import com.example.jetpacktest02.utils.Notification
import com.example.jetpacktest02.utils.buildNotification
import com.example.jetpacktest02.utils.show
import com.example.jetpacktest02.utils.update
import com.google.accompanist.permissions.ExperimentalPermissionsApi


class NotificationTestViewModel: ViewModel() {
    var notification: Notification? = null

    @OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
    private fun buildNotification(context: Context, title: String, message: String) {
        val clickIntent = Intent(context, LockScreenActivity::class.java)
        notification = context.buildNotification(
            id = 1,
            title = title,
            message = message,
            action = "快去喝水吧！",
            actionMessage = "点击了按钮",
            visibility = VISIBILITY_PUBLIC,
            activityIntent = clickIntent,
        )
    }

    fun showNotification(context: Context, title: String, message: String) {
        buildNotification(context, title, message)
        notification?.show()
    }

    fun updateNotification(context: Context, titleNew: String, messageNew: String) {
        notification?.update(context, titleNew, messageNew)
    }
}
