package com.example.jetpacktest02.screen

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

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.draw.scale

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacktest02.R


/**
 * vip界面
 * 负责人：skc
 */


@Composable
fun ChooseSeed() {

    var ifVip by remember{
        mutableStateOf(false)}
    val openDialog=remember{
        mutableStateOf(false)
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

    Column(){

        

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