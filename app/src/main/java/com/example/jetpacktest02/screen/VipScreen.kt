package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.animation.*
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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.TransformOrigin

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.*
import kotlinx.coroutines.delay


/**
 * vip??????
 * ????????????skc
 */


@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun VipScreen(userViewModel: UserViewModel) {

//    var ifVip by remember{
//        mutableStateOf(false)}
    val openDialog = remember {
        mutableStateOf(false)
    }
    var ifdialog by remember {
        mutableStateOf(false)
    }

    val ifVip = userViewModel.uiState.value.isVip.value

    var state by remember {
        mutableStateOf(false)
    }
    var state1 by remember {
        mutableStateOf(false)
    }
    var state2 by remember {
        mutableStateOf(false)
    }
    var state3 by remember {
        mutableStateOf(false)

    }

    LaunchedEffect(key1 = state) {
        state = true
    }
    LaunchedEffect(key1 = state1) {
        delay(150)
        state1 = true
    }
    LaunchedEffect(key1 = state2) {
        delay(250)
        state2 = true
    }
    LaunchedEffect(key1 = state3) {
        delay(350)
        state3 = true
    }

    //???????????????????????????
    rememberSystemUiController().setStatusBarColor(
        Color(0xfff8f8f8), darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )


    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_vip_bgimg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .offset(0.dp, 0.dp)
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )


    }

    Column(
        modifier = Modifier
            .padding(top = 30.dp, start = 0.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
//             verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = state1,
            enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
            exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
        ) {
            if (ifVip) {
                //?????????????????????
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_img_vipcard),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 323.dp, height = 165.dp)
                        .offset(0.dp, 0.dp)
                )
            }
            if (!ifVip) {
                //?????????????????????
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_0_vipinactived),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 323.dp, height = 155.dp)
                        .offset(0.dp, 0.dp)
                )
            }
        }
        AnimatedVisibility(
            visible = state2,
            enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
            exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
        )
        {
            LazyColumn(
                Modifier
                    .fillMaxWidth() // ?????????????????????
                    .height(350.dp)
                    .padding(top = 0.dp, start = 0.dp, end = 0.dp, bottom = 0.dp)
                    .offset(0.dp, 0.dp),


                verticalArrangement = Arrangement.spacedBy(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                content = {

                    item {
                        Image(
                            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v1),
                            contentDescription = null,
                            modifier = Modifier.size(width = 323.dp, height = 95.dp)
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v2),
                            contentDescription = null,
                            modifier = Modifier.size(width = 323.dp, height = 95.dp)
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v3),
                            contentDescription = null,
                            modifier = Modifier.size(width = 323.dp, height = 95.dp)
                        )
                    }
                    item {
                        Image(
                            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v4),
                            contentDescription = null,
                            modifier = Modifier.size(width = 323.dp, height = 95.dp)
                        )
                    }

                    item {
                        Image(
                            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_1_v5),
                            contentDescription = null,
                            modifier = Modifier.size(width = 323.dp, height = 95.dp)
                        )
                    }


                })
        }

        AnimatedVisibility(
            visible = state3,
            enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
            exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                    fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp, end = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = { ifdialog = true },
                    shape = RoundedCornerShape(27.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 204.dp, height = 50.dp),
                ) {
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = "?????????????????????", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }

    if (ifdialog) {
        Dialog(onDismissRequest = { ifdialog = false }) {
//            //????????????
//            Image(
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_backblur),
//                contentDescription = null,
//                alpha = 0.34f,
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .fillMaxSize(), contentScale = ContentScale.FillWidth
//            )
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_dialog),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 291.dp, height = 362.dp)
                        .offset(0.dp, 0.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 310.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "9.29 ~ 10.29", color = DateBlue, fontSize = 17.sp,
                        modifier = Modifier.offset(0.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            ifdialog = false;userViewModel.uiState.value.isVip.value = true
                        },
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenMain,
                            contentColor = GreenMain
                        ),
                        modifier = Modifier
                            .size(width = 201.dp, height = 42.dp)
                            .offset(0.dp, 0.dp)
                    ) {
                        Text(text = "??????", color = Color.White, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(33.dp))
                    Image(
                        painter = painterResource(id = R.drawable.g4_4_btn_close),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable(
                                onClick = {
                                    ifdialog = false
                                },
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }

            }
        }
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
                    Text(text = "??????", color = Color.White, fontSize = 16.sp)
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
//                    Text(text = "??????", color = Color.White)
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