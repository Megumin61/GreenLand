package com.example.jetpacktest02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpacktest02.ViewModel.UserViewModel

/**
 * 登录页面_手机验证码登录
 * 负责人：skc
 */


@Composable
fun PhoneLoginScreen() {



    Column {
        Text("这里是加载页面")
        Button(
            onClick = {},
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }
    }
}
@Preview (name = "Light Mode")
@Composable
fun DefaultPreview(){
    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_frontpage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
        )
        Column(
            modifier=Modifier
                .padding(start=300.dp, end = 19.dp,top=20.dp)
            ,horizontalAlignment= Alignment.CenterHorizontally

        ){

            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_button_skip),
                contentDescription = null,
                modifier = Modifier.width(200.dp).height(20.dp)
            )

        }

        Column(
            modifier=Modifier
                .padding(top = 450.dp,start=16.dp, end = 16.dp)
            ,horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
        ){

            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0),
                contentDescription = null,
                modifier = Modifier.width(340.dp).height(75.dp)
            )
            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_button_wechatlogin),
                contentDescription = null,
                modifier = Modifier.width(340.dp).height(75.dp)
            )
            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_button_otherwaylogin),
                contentDescription = null,
                modifier = Modifier.width(100.dp).height(75.dp)

            )
        }
    }
}