package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.draw.scale

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.*


/**
 * vip界面
 * 负责人：skc
 */


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun VipScreen(userViewModel:UserViewModel) {

//    var ifVip by remember{
//        mutableStateOf(false)}
    val openDialog=remember{
        mutableStateOf(false)
    }
    var ifdialog by remember{
        mutableStateOf(false)}

    val ifVip = userViewModel.uiState.value.isVip.value


    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Color(0xfff8f8f8),darkIcons = androidx.compose.material.MaterialTheme.colors.isLight)


    Surface(modifier = Modifier.fillMaxSize(),) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_vip_bgimg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight().offset(0.dp,0.dp)
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )



    }

    Column(
        modifier= Modifier
            .padding(top = 30.dp, start = 0.dp)
            .fillMaxSize()
        ,horizontalAlignment= Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.SpaceBetween
//             verticalArrangement = Arrangement.Center
    ){
        if (ifVip) {
            //会员已激活卡片
            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_img_vipcard),
                contentDescription = null,
                modifier = Modifier.size(width = 323.dp, height = 165.dp).offset(0.dp,0.dp)
            )
        }
        if(!ifVip) {
            //会员未激活卡片
            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_vipinactived),
                contentDescription = null,
                modifier = Modifier.size(width = 323.dp, height = 155.dp).offset(0.dp,0.dp)
            )
        }
        LazyColumn(
            Modifier
                .fillMaxWidth() // 宽度填满父空间
                .height(350.dp)
                .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
                .offset(0.dp,0.dp),


            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            content = {

                item{
                    Image(
                        painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v1),
                        contentDescription = null,
                        modifier = Modifier.size(width = 323.dp, height = 95.dp)
                    )
                }
                item{
                    Image(
                        painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v2),
                        contentDescription = null,
                        modifier = Modifier.size(width = 323.dp, height = 95.dp)
                    )
                }
                item{
                    Image(
                        painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v3),
                        contentDescription = null,
                        modifier = Modifier.size(width = 323.dp, height = 95.dp)
                    )
                }
                item{
                    Image(
                        painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v4),
                        contentDescription = null,
                        modifier = Modifier.size(width = 323.dp, height = 95.dp)
                    )
                }

                item{
                    Image(
                        painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v5),
                        contentDescription = null,
                        modifier = Modifier.size(width = 323.dp, height = 95.dp)
                    )
                }



            })

        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp, end = 20.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
            Button(onClick = {ifdialog=true },
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier
                    .size(width = 204.dp, height = 50.dp)
                ,


                ) {

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "确认协议并开通", color = Color.White , fontSize = 18.sp)
            }
        }


    }
//    Button(onClick = {ifdialog=true },
//        shape = RoundedCornerShape(27.dp),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = GreenMain,
//            contentColor = GreenMain
//        ),
//        modifier = Modifier
//            .size(width = 204.dp, height = 50.dp)
//            .offset(160.dp, 670.dp)
//
//        ,
//
//
//        ) {
//
////        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
//        Text(text = "确认协议并开通", color = Color.White , fontSize = 18.sp)
//    }
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



            Button(onClick = { ifdialog=false;userViewModel.uiState.value.isVip.value=true },
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
        if (!ifdialog){}
    }

}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DialogCard(userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {


    if (userViewModel.uiState.value.openDialog.value) {
        Dialog(
            onDismissRequest = { userViewModel.uiState.value.openDialog.value = false },
        ) {
            Box {


                Image(


                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_dialog),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 291.dp, height = 362.dp)
                        .offset(50.dp, 200.dp)
                )


                Text(
                    text = "9.29 ~ 10.29", color = DateBlue, fontSize = 20.sp,

                    modifier = Modifier.offset(150.dp, 466.dp)
                )



                Button(
                    onClick = { userViewModel.uiState.value.openDialog.value = false },
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 201.dp, height = 42.dp)
                        .offset(96.dp, 506.dp)

                ) {
                    Text(text = "确认", color = Color.White, fontSize = 16.sp)
                }


            }

        }
    }
}
//@Composable
//fun DialogUI(){
//
//
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





//@Preview (name = "Light Mode")
//@Composable
//fun DefaultPreview(){
//    VipScreen()
//}