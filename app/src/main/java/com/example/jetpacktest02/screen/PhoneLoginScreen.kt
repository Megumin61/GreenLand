package com.example.jetpacktest02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions

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
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpacktest02.AppIntroduction
import com.example.jetpacktest02.LoginFront
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green700
import com.example.scaffolddemo.ui.theme.GreenMain
import com.example.scaffolddemo.ui.theme.Text3Gray
import com.google.accompanist.systemuicontroller.rememberSystemUiController


/**
 * 登录页面_手机验证码登录
 * 负责人：skc
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneLoginScreen(
    navController: NavController
) {
    var phonenumber by remember{ mutableStateOf("")}
    var captcha by remember{ mutableStateOf("")}

    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1,darkIcons = androidx.compose.material.MaterialTheme.colors.isLight)


    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_0_frontpage),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )
        Column(
            modifier=Modifier
                .padding(start=0.dp, end = 19.dp,top=40.dp)
            ,horizontalAlignment= Alignment.End

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
                .padding(top = 380.dp,start=0.dp, end = 0.dp)
            ,horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
        ){
//请输入手机号
            Box{
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
                        placeholder={
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


            Box{
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_1_textbox_password),
                    contentDescription = null,
                    modifier = Modifier
                        .width(340.dp)
                        .height(75.dp)
                        .offset(10.dp, 0.dp)
                )
                //获取验证码文字按钮
                androidx.compose.material.TextButton(onClick = { /*TODO*/ },
                    modifier = Modifier.offset(230.dp,12.dp)

                ) {
                    Text(text = "获取验证码", color = GreenMain , fontSize = 15.sp)


                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //验证码输入栏文本框
                    TextField(
                        value = captcha,
                        onValueChange = {
                            captcha = it

                        },
                        singleLine = true,
                        placeholder={
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
            ){
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_3_tips),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 200.dp)
                        .size(width = 130.dp, height = 20.dp)
                )
            }


//登录按钮
            Button(onClick = { navController.navigate(AppIntroduction.route) { launchSingleTop = true; }},
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier.size(width =137.dp, height = 50.dp ).offset(0.dp,10.dp)
            ) {
                Text(text = "登录", color = Color.White , fontSize = 20.sp)
            }
            Box{
//其他登录方式文本按钮
                androidx.compose.material.TextButton(onClick = { /*TODO*/ },
                    modifier = Modifier.offset(0.dp,30.dp)

                ) {
                    Text(text = "其他登录方式 ＞", color = Text3Gray , fontSize = 14.sp)


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