package com.example.jetpacktest02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * vip界面
 * 负责人：skc
 */


@Composable
fun VipScreen() {

    var ifVip by remember{
        mutableStateOf(false)}
    val shouldShowDialog=remember{
        mutableStateOf(true)
    }
    var ifdialog by remember{
        mutableStateOf(false)}


    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1,darkIcons = androidx.compose.material.MaterialTheme.colors.isLight)


    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_vip_page),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )



        }

        Column(
            modifier=Modifier
                .padding(top = 48.dp,start=35.dp)
            ,horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
        ){
            if (ifVip) {
                //会员已激活卡片
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_img_vipcard),
                    contentDescription = null,
                    modifier = Modifier.size(width = 333.dp, height = 160.dp).offset(-5.dp,-10.dp)
                )
            }
            if(!ifVip) {
                //会员未激活卡片
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_vipinactived),
                    contentDescription = null,
                    modifier = Modifier.size(width = 323.dp, height = 155.dp).offset(0.dp,-10.dp)
                )
            }




            Button(onClick = {ifdialog=true },
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier
                    .size(width = 204.dp, height = 50.dp)
                    .offset(70.dp, 510.dp),


            ) {

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "确认协议并开通", color = Color.White , fontSize = 18.sp)
            }


    }
    if (ifdialog) {
        //背景颜色
        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_backblur),
            contentDescription = null,
            alpha = 0.34f,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )
        Box{


            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_dialog),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 291.dp, height = 362.dp)
                    .offset(50.dp, 200.dp)
            )


            Text(text = "9.29 ~ 10.29", color = DateBlue , fontSize = 20.sp,

                modifier = Modifier.offset(150.dp,466.dp)
            )



            Button(onClick = { ifdialog=false;ifVip=true },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier
                    .size(width = 201.dp, height = 42.dp)
                    .offset(96.dp, 506.dp)

            ) {
                Text(text = "确认", color = Color.White , fontSize = 16.sp)
            }

            
        }
//        Image(
//            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_dialog),
//            contentDescription = null,
//            alignment = Alignment.Center,
//            modifier = Modifier
//                .size(width = 291.dp, height = 362.dp)
//                .offset(50.dp, 200.dp)
//        )
    }
}


//@Composable
//fun DialogUI(){
//    val shouldShowDialog=remember{
//        mutableStateOf(true)
//    }
//    if(shouldShowDialog.value){
//        AlertDialog(onDismissRequest = { shouldShowDialog.value=false },
//
////            title = { Text(text = "Dialog")},
////            Image(painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_vipinactived)
////                , contentDescription =null ),
//
//            text = { Text(text = "This is jetpack compose")},
//            confirmButton = {
//                Button(onClick = { shouldShowDialog.value=false},
//                    colors = ButtonDefaults.buttonColors(
//                    containerColor = GreenMain,
//                    contentColor = GreenMain)
//
//
//                ) {
//                    Text(text = "确认", color = Color.White)
//                }
//            }
//
//
//        )
//
//    }

//}



//@Preview (name = "Light Mode")
//@Composable
//fun DefaultPreview(){
//    VipScreen()
//}