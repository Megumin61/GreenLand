package com.example.jetpacktest02.utils

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.jetpacktest02.config.UsersApplication

class ApkUtils {
    companion object {
        //通过包名启动第三方应用
        fun StartLaunchAPK(context: Context, packageName: String, activityName: String) {
            var mainAct: String? = null
            val pkgMag = context.packageManager
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.flags =
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK
            //如果已经启动apk，则直接将apk从后台调到前台运行（类似home键之后再点击apk图标启动），如果未启动apk，则重新启动
            @SuppressLint("WrongConstant")
            val list = pkgMag.queryIntentActivities(
                intent,
                PackageManager.GET_ACTIVITIES
            )
            for (i in list.indices) {
                val info = list[i]
                if (info.activityInfo.packageName == packageName) {
                    mainAct = info.activityInfo.name
                    break
                }
            }
            if (TextUtils.isEmpty(mainAct)) {
                return
            }
            // 启动指定的activity页面
            intent.component = ComponentName(packageName,activityName)
            //启动到app的主页或启动到原来留下的位置
            intent.component = ComponentName(packageName, mainAct!!)
            //启动app
            context.startActivity(intent)

        }

        //根据包名 判断某APP是否安装
        public fun CheckApkExist(context: Context, packageName: String): Boolean {
            //  检查app是否有安装
            var intent = Intent()
            if (TextUtils.isEmpty(packageName))
                return true
            try {
                val info = context.packageManager
                    .getApplicationInfo(
                        packageName,
                        PackageManager.GET_UNINSTALLED_PACKAGES
                    )
                intent = context.packageManager.getLaunchIntentForPackage(packageName)!!;
//                Toast.makeText(UsersApplication.context,info.toString(), Toast.LENGTH_SHORT).show()
                return true
            } catch (e: PackageManager.NameNotFoundException) {
                // Timber.d(e.toString()) // Timber 是我打印 log 用的工具，这里只是打印一下 log
                Toast.makeText(UsersApplication.context,e.message.toString(), Toast.LENGTH_SHORT).show()
                return false
            }

        }

    }
}