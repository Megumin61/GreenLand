@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.jetpacktest02.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.*
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.example.jetpacktest02.AppIntroduction
import com.example.jetpacktest02.MainActivity
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.GreenMain
import com.example.scaffolddemo.ui.theme.Text3Gray
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mob.MobSDK
import kotlinx.coroutines.launch
import java.util.*


/**
 * 登录页面_手机验证码登录
 * 负责人：skc
 */

@SuppressLint("PermissionLaunchedDuringComposition", "CoroutineCreationDuringComposition")
@Composable
//定义判断权限申请的函数，在onCreat中调用就行
fun applypermission() {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var needapply = true
    val appPermissionsState = rememberMultiplePermissionsState(
        listOf(
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
    )

    if (Build.VERSION.SDK_INT >= 23) {
        if (appPermissionsState.allPermissionsGranted) {
            needapply = false
        } else {

        }
        for (i in appPermissionsState.permissions) {
            val chechpermission = ContextCompat.checkSelfPermission(
                context,
                i.permission
            )
            if (chechpermission != PackageManager.PERMISSION_GRANTED) {
                needapply = true
            }
        }
        if (needapply) {
            Button(onClick = { appPermissionsState.launchMultiplePermissionRequest() }) {
                Text("获取所需权限")
            }
        }
    }
}

@ExperimentalPermissionsApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneLoginScreen(
    navController: NavController
) {

    applypermission()

    MobSDK.submitPolicyGrantResult(true, null)

    var phonenumber by remember { mutableStateOf("") }
    var verifyCode by remember { mutableStateOf("") }
    var timer: Timer? = null
    var count by remember { mutableStateOf(60) }
    var getCodeBtnEabled by remember {
        mutableStateOf(true)
    }
    var getCodeBtnText by remember {
        mutableStateOf("获取验证码")
    }
    var hasLogin by remember {
        mutableStateOf(false)
    }
    var loginBtnText by remember {
        mutableStateOf("")
    }

    var eventHandler: EventHandler? = null
    var editTextPhone: EditText? = null
    var editTextNumber: EditText? = null
    var get_code_id: Button? = null
    var login_id: Button? = null
//    var timer: Timer? = null
//    var count = 60
    LaunchedEffect(key1 = hasLogin) {
        if(hasLogin){
            navController.navigate(AppIntroduction.route) {
                launchSingleTop = true;
            }
        }
    }

    @SuppressLint("HandlerLeak")
    var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: android.os.Message) {
            val tag = msg.what
            when (tag) {
                1 -> {
                    val arg = msg.arg1
                    if (arg == 1) {
                        getCodeBtnText = "重新获取" //计时结束停止计时把值恢复
                        count = 60
                        timer!!.cancel()
                        getCodeBtnEabled = true
//                        get_code_id!!.isEnabled = true
                    } else {
                        getCodeBtnText = count.toString() + ""
                    }
                }
                2 -> {}
                3 -> {
                    Log.i("code", "获取短信验证码失败")
                }
                4 -> {}
                else -> {}
            }
        }
    }


    fun appInit(
        navController: NavController
    ) {
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
                        Log.i("code", "登录成功")
                        hasLogin = true

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

    appInit(navController = navController)

    fun CountdownStart() {
//        get_code_id!!.isEnabled = false
        getCodeBtnEabled = false
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

    fun handleGetCodeClick() {
        var phone = ""
        var code = ""
        phone = phonenumber.trim { it <= ' ' }
        if (!TextUtils.isEmpty(phone)) {
            CountdownStart()
            SMSSDK.getVerificationCode("86", phone)
        } else {
            Log.i("code", "手机号不能为空！")
        }
    }

    fun handleLoginClick() {
        appInit(navController = navController)
        var phone = ""
        var code = ""
        phone = phonenumber.trim { it <= ' ' }
        code = verifyCode.trim { it <= ' ' }
        if (TextUtils.isEmpty(phone)) {
            Log.i("code", "手机号不能为空！")
        } else if (TextUtils.isEmpty(code)) {
            Log.i("code", "验证码不能为空！")
        } else {
            SMSSDK.submitVerificationCode("86", phone, code)
        }
    }


//    fun onClick() {
//        var phone = ""
//        var code = ""
////        val id = view.id
//        when (id) {
//            com.example.jetpacktest02.R.id.get_code_id -> {
//                phone = editTextPhone!!.text.toString().trim { it <= ' ' }
//                if (!TextUtils.isEmpty(phone)) { //倒计时
//                    CountdownStart()
//                    MainActivity.getVerificationCode("86", phone)
//                } else {
////                    Toast.makeText(, "请输入手机号码", Toast.LENGTH_LONG).show()
//                }
//            }
//            com.example.jetpacktest02.R.id.login_id -> {
//                phone = editTextPhone!!.text.toString().trim { it <= ' ' }
//                code = editTextNumber!!.text.toString().trim { it <= ' ' }
//                if (TextUtils.isEmpty(phone)) {
////                    Toast.makeText(this, "请输入手机号码", Toast.LENGTH_LONG).show()
//                } else if (TextUtils.isEmpty(code)) {
////                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG).show()
//                } else { //登录逻辑
//                    /**
//                     * cn.smssdk.SMSSDK.class
//                     * 提交验证码
//                     * @param country   国家区号
//                     * @param phone     手机号
//                     * @param code      验证码
//                     */
//                    SMSSDK.submitVerificationCode("86", phone, code) //提交验证码
//                }
//            }
//        }
//    }

    object {
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


    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )


    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_frontpage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .padding(start = 0.dp, end = 19.dp, top = 40.dp),
            horizontalAlignment = Alignment.End

        )
        {
            Box {
                //立即体验跳过按钮
                androidx.compose.material.TextButton(onClick = {
                    /*TODO*/
                }) {
                    Text(text = "立即体验", color = Text3Gray, fontSize = 14.sp)
                }

            }


        }

        Column(
            modifier = Modifier
                .padding(top = 380.dp, start = 0.dp, end = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
        ) {
//请输入手机号
            Box {
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_1_textbox_phonenumber),
                    contentDescription = null,
                    modifier = Modifier
                        .width(340.dp)
                        .height(75.dp)
                        .offset(10.dp, 0.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //输入手机号文本框
                    TextField(
                        value = phonenumber,
                        onValueChange = {
                            phonenumber = it

                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "请输入手机号", color = Color.White)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Send
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color.Unspecified,
                            unfocusedLabelColor = Color.Unspecified,
                            focusedBorderColor = Color.Unspecified,
                            unfocusedBorderColor = Color.Unspecified

                        ),
                        modifier = Modifier
                            .offset(20.dp, -3.dp)
                            .width(250.dp)
                            .height(75.dp)


                    )
                }
            }


            Box {
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_1_textbox_password),
                    contentDescription = null,
                    modifier = Modifier
                        .width(340.dp)
                        .height(75.dp)
                        .offset(10.dp, 0.dp)
                )
                //获取验证码文字按钮
                androidx.compose.material.TextButton(
                    onClick = { handleGetCodeClick() },
                    modifier = Modifier.offset(230.dp, 12.dp),
                    enabled = getCodeBtnEabled
                ) {
                    Text(text = getCodeBtnText, color = GreenMain, fontSize = 15.sp)


                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //验证码输入栏文本框
                    TextField(
                        value = verifyCode,
                        onValueChange = {
                            verifyCode = it

                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "", color = GreenMain)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Send
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = Color.Unspecified,
                            unfocusedLabelColor = Color.Unspecified,
                            focusedBorderColor = Color.Unspecified,
                            unfocusedBorderColor = Color.Unspecified

                        ),
                        modifier = Modifier
                            .offset(-31.dp, -2.dp)
                            .width(150.dp)
                            .height(75.dp)

                    )
                }

            }

            Row(
                modifier = Modifier
                    .width(375.dp)
                    .height(40.dp)
            ) {
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_3_tips),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 200.dp)
                        .size(width = 130.dp, height = 20.dp)
                )
            }


//登录按钮
            Button(
                onClick = {
//                    navController.navigate(AppIntroduction.route) {
//                        launchSingleTop = true;
//                    }
                    handleLoginClick()
                },
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier
                    .size(width = 137.dp, height = 50.dp)
                    .offset(0.dp, 10.dp)
            ) {
                Text(text = "登录", color = Color.White, fontSize = 20.sp)
            }
            Box {
//其他登录方式文本按钮
                androidx.compose.material.TextButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.offset(0.dp, 30.dp)

                ) {
                    Text(text = "其他登录方式 ＞", color = Text3Gray, fontSize = 14.sp)


                }
            }
//            Image(
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_button_otherwaylogin),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(100.dp)
//                    .height(75.dp)
//
//            )
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview (name = "Light Mode")
//@Composable
//fun DefaultPreview(){
//    PhoneLoginScreen()
//}