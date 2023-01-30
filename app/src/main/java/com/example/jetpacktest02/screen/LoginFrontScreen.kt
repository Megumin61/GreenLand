package com.example.jetpacktest02.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * 登录页面_选择登录方式
 * 负责人：skc
 */
@Composable
fun LoginFrontScreen() {
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

        Column{

            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g0_loadingpage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight().fillMaxSize()
            )

        }
    }
}