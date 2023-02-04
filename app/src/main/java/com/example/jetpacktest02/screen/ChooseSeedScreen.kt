package com.example.jetpacktest02.screen

import androidx.compose.foundation.*
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
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.draw.scale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow

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
        SeedTabRow()

//        Image(
//            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_vip_page),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxHeight()
//                .fillMaxSize(), contentScale = ContentScale.FillWidth
//        )



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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeedTabRow() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("自定义", "随机")
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

//    val indicator = @Composable { tabPositions: List<TabPosition> ->
//        TabIndicator(
//        )
//    }

    Column {
        androidx.compose.material.TabRow(
            selectedTabIndex = state,
            indicator = @Composable { tabPositions ->
                androidx.compose.material.TabRowDefaults.Indicator(
                    Modifier.customTabIndicatorOffset(tabPositions[state]),
                    color = LightGreen
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                androidx.compose.material.Tab(
                    modifier = Modifier
                        .background(Color.White)
                        .width(10.dp),
                    selected = state == index,
                    onClick = { state = index },
                    text = {
                        androidx.compose.material.Text(
                            text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W800,
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Gray1
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        androidx.compose.material3.TextField(
            value = text,
            shape = RoundedCornerShape(25f.dp),
            onValueChange = { text = it },
            singleLine = false,
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.g2_5_icon_search),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp, 20.dp)
                )
            },
            placeholder = {
                androidx.compose.material3.Text(
                    "搜索昵称或id",
                    fontSize = 14.sp,
                    color = Gray2
                )
            },
//                            label={ Text("写两句话和好友打招呼吧", fontSize = 14.sp, color = Gray2) },
            modifier = Modifier
                .height(50.dp)
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
                .border(BorderStroke(1.dp, BlueGray2), RoundedCornerShape(25f.dp)),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Gray2,
                containerColor = Gray3,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}
//@Preview (name = "Light Mode")
//@Composable
//fun DefaultPreview(){
//    VipScreen()
//}