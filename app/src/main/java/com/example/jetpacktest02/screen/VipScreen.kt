package com.example.jetpacktest02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.GreenMain
import com.example.scaffolddemo.ui.theme.Text3Gray
import com.example.scaffolddemo.ui.theme.WechatGreen

/**
 * vip界面
 * 负责人：skc
 */


@Composable
fun VipScreen() {



    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_vip_page),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
        )
        Column(
            modifier=Modifier
                .padding(start=300.dp, end = 19.dp,top=40.dp)
            ,horizontalAlignment= Alignment.CenterHorizontally

        )
        {
            Box{
                //立即体验跳过按钮
                androidx.compose.material.TextButton(onClick = {
                    /*TODO*/
                }) {
                    Text(text = "立即体验", color = Text3Gray , fontSize = 14.sp)
                }

            }


        }

        Column(
            modifier=Modifier
                .padding(top = 450.dp,start=16.dp, end = 16.dp)
            ,horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
        ){

//            Image(
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_button_phonelogin),
//                contentDescription = null,
//                modifier = Modifier.width(340.dp).height(75.dp)
//            )


            //手机号登录按钮
            Button(onClick = { /*TODO*/ },
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier
                    .size(width = 311.dp, height = 50.dp)
                    .offset(0.dp, 10.dp)

            ) {


                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_1_ic_phone),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp,24.dp)

                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "手机号登录", color = Color.White , fontSize = 15.sp)
            }
            //微信一键登录按钮
            Button(onClick = { /*TODO*/ },
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WechatGreen,
                    contentColor = WechatGreen
                ),
                modifier = Modifier
                    .size(width = 311.dp, height = 50.dp)
                    .offset(0.dp, 25.dp)

            ) {


                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_ic_wechat),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp,24.dp)

                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "微信用户一键登录", color = Color.White , fontSize = 15.sp)
            }

//            Image(
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_button_wechatlogin),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(340.dp)
//                    .height(75.dp)
//            )
            Box{
//其他登录方式文本按钮
                androidx.compose.material.TextButton(onClick = { /*TODO*/ },
                    modifier = Modifier.offset(0.dp,30.dp)

                ) {
                    Text(text = "其他登录方式 ＞", color = Text3Gray , fontSize = 14.sp)


                }
            }
        }
    }
}
@Preview (name = "Light Mode")
@Composable
fun DefaultPreview(){
    VipScreen()
}