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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    val ifVip=remember{
        mutableStateOf(false)}
    val shouldShowDialog=remember{
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_vip_page),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
        )



        }

        Column(
            modifier=Modifier
                .padding(top = 48.dp,start=35.dp)
            ,horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
        ){

            //会员未激活卡片
            Image(
                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_vipinactived),
                contentDescription = null,
                modifier = Modifier.size(width = 323.dp, height = 155.dp)
            )
//会员已激活卡片
//            Image(
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_img_vipcard),
//                contentDescription = null,
//                modifier = Modifier.size(width = 323.dp, height = 155.dp)
//            )

            Button(onClick = { shouldShowDialog.value=true },
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier
                    .size(width = 204.dp, height = 50.dp)
                    .offset(70.dp, 510.dp)

            ) {

                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "确认协议并开通", color = Color.White , fontSize = 18.sp)
            }

    }
    //DialogUI()
}


@Composable
fun DialogUI(){
    val shouldShowDialog=remember{
        mutableStateOf(true)
    }
    if(shouldShowDialog.value){
        AlertDialog(onDismissRequest = { shouldShowDialog.value=false },

//            title = { Text(text = "Dialog")},
//            Image(painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_vipinactived)
//                , contentDescription =null ),

            text = { Text(text = "This is jetpack compose")},
            confirmButton = {
                Button(onClick = { shouldShowDialog.value=false},
                    colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain)
                

                ) {
                    Text(text = "确认", color = Color.White)
                }
            }


        )
        
    }

}



//@Preview (name = "Light Mode")
//@Composable
//fun DefaultPreview(){
//    VipScreen()
//}