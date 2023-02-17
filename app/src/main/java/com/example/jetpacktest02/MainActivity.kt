package com.example.jetpacktest02

import android.Manifest

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.*
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK

import com.mob.MobSDK
import java.util.*

class MainActivity : AppCompatActivity() {
    var eventHandler: EventHandler? = null
    var editTextPhone: EditText? = null
    var editTextNumber: EditText? = null
    var get_code_id: Button? = null
    var login_id: Button? = null
    var timer: Timer? = null
    var count = 60

    @SuppressLint("HandlerLeak")
    var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: android.os.Message) {
            val tag = msg.what
            when (tag) {
                1 -> {
                    val arg = msg.arg1
                    if (arg == 1) {
                        get_code_id!!.text = "重新获取" //计时结束停止计时把值恢复
                        count = 60
                        timer!!.cancel()
                        get_code_id!!.isEnabled = true
                    } else {
                        get_code_id!!.text = count.toString() + ""
                    }
                }
                2 -> Toast.makeText(this@MainActivity, "获取短信验证码成功", Toast.LENGTH_LONG).show()
                3 -> {
                    Log.i("code", "获取短信验证码失败")
                    Toast.makeText(this@MainActivity, msg.data.getString("code"), Toast.LENGTH_LONG)
                        .show()
                }
                4 -> Toast.makeText(
                    this@MainActivity,
                    msg.data.getString("code"),
                    Toast.LENGTH_LONG
                ).show()
                else -> {}
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applypermission()
        MobSDK.submitPolicyGrantResult(true, null)
        init()
    }

    //定义判断权限申请的函数，在onCreat中调用就行
    fun applypermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            var needapply = false
            for (i in PERMISSIONS_STORAGE.indices) {
                val chechpermission = ContextCompat.checkSelfPermission(
                    applicationContext,
                    PERMISSIONS_STORAGE[i]
                )
                if (chechpermission != PackageManager.PERMISSION_GRANTED) {
                    needapply = true
                }
            }
            if (needapply) {
                ActivityCompat.requestPermissions(this@MainActivity, PERMISSIONS_STORAGE, 1)
            }
        }
    }

    //重载用户是否同意权限的回调函数，进行相应处理
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (i in grantResults.indices) {      //检查权限是否获取
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                //同意后的操作
                //Toast.makeText(MainActivity.this, permissions[i]+"已授权",Toast.LENGTH_SHORT).show();//提示
            } else {
                //不同意后的操作
                //Toast.makeText(MainActivity.this,permissions[i]+"拒绝授权",Toast.LENGTH_SHORT).show();//提示
            }
        }
    }

    private fun init() {
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextNumber = findViewById(R.id.editTextNumber)
        get_code_id = findViewById(R.id.get_code_id)
        get_code_id?.setOnClickListener(View.OnClickListener { view: View -> onClick(view) })
        login_id = findViewById(R.id.login_id)
        login_id?.setOnClickListener(View.OnClickListener { view: View -> onClick(view) })
        eventHandler = object : EventHandler() {
            override fun afterEvent(
                event: Int,
                result: Int,
                data: Any
            ) { // TODO 此处为子线程！不可直接处理UI线程！处理后续操作需传到主线程中操作！
                Log.i("返回:", "$event | $result | $data")
                if (result == SMSSDK.RESULT_COMPLETE) { //成功回调
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交短信、语音验证码成功
                        val bundle = Bundle()
                        bundle.putString("code", "登录成功！")
                        val message = Message()
                        message.what = 4
                        message.data = bundle
                        handler.sendMessage(message)
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        val message = Message()
                        message.what = 2
                        handler.sendMessage(message)
                    } else if (event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE) { //获取语音验证码成功
                        val message = Message()
                        message.what = 2
                        handler.sendMessage(message)
                    }
                } else if (result == SMSSDK.RESULT_ERROR) { //失败回调
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交返回
                        val bundle = Bundle()
                        bundle.putString("code", "输入的验证码不正确！")
                        val message = Message()
                        message.what = 4
                        message.data = bundle
                        handler.sendMessage(message)
                    } else {
                        val bundle = Bundle()
                        bundle.putString("code", data.toString())
                        val message = Message()
                        message.what = 3
                        message.data = bundle
                        handler.sendMessage(message)
                    }
                } else { //其他失败回调
                    (data as Throwable).printStackTrace()
                }
            }
        }
        SMSSDK.registerEventHandler(eventHandler) //注册短信回调
    }

    private fun onClick(view: View) {
        var phone = ""
        var code = ""
        val id = view.id
        when (id) {
            R.id.get_code_id -> {
                phone = editTextPhone!!.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(phone)) { //倒计时
                    CountdownStart()
                    getVerificationCode("86", phone)
                } else {
                    Toast.makeText(this, "请输入手机号码", Toast.LENGTH_LONG).show()
                }
            }
            R.id.login_id -> {
                phone = editTextPhone!!.text.toString().trim { it <= ' ' }
                code = editTextNumber!!.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "请输入手机号码", Toast.LENGTH_LONG).show()
                } else if (TextUtils.isEmpty(code)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show()
                } else { //登录逻辑
                    /**
                     * cn.smssdk.SMSSDK.class
                     * 提交验证码
                     * @param country   国家区号
                     * @param phone     手机号
                     * @param code      验证码
                     */
                    SMSSDK.submitVerificationCode("86", phone, code) //提交验证码
                }
            }
        }
    }

    //倒计时函数
    private fun CountdownStart() {
        get_code_id!!.isEnabled = false
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                val message = Message()
                message.what = 1
                message.arg1 = count
                if (count != 0) {
                    count--
                } else {
                    return
                }
                handler.sendMessage(message)
            }
        }, 1000, 1000)
    }

    override fun onDestroy() {
        super.onDestroy() // 使用完EventHandler需注销，否则可能出现内存泄漏
        SMSSDK.unregisterEventHandler(eventHandler)
    }

    companion object {
        //所需申请的权限
        private val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
        )

        /**
         * cn.smssdk.SMSSDK.class
         * 请求文本验证码
         * @param country   国家区号
         * @param phone     手机号
         */
        fun getVerificationCode(country: String?, phone: String?) {
            //获取短信验证码
            SMSSDK.getVerificationCode(country, phone)
        }

        /**cn.smssdk.SMSSDK.class
         * 请求文本验证码(带模板编号)
         * @param tempCode  模板编号
         * @param country   国家区号
         * @param phone     手机号
         */
        fun getVerificationCode(tempCode: String?, country: String?, phone: String?) {
            //获取短信验证码
            SMSSDK.getVerificationCode(tempCode, country, phone)
        }
    }
}