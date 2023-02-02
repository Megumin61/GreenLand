package com.example.jetpacktest02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.scaffolddemo.ui.theme.Green1
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//
/**
 * 加载页面页面
 * 负责人：skc
 */
@Composable
fun LoginLoadingScreen(){
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1,darkIcons = androidx.compose.material.MaterialTheme.colors.isLight)


    Column{
        Column{

            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_loadingpage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize()
            , contentScale = ContentScale.FillWidth
            )

        }
    }

}

//@Preview (name = "Light Mode")
//@Composable
//fun DefaultPreview(){
//    LoginLoadingScreen()
//}