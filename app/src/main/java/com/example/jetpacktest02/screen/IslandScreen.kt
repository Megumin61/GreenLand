package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.ViewModel.FriendItem
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

//好友岛页面
//@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun IslandScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    controller:NavHostController
) {
    LaunchedEffect(key1 = userViewModel._uiState.value.msgItem.value) {
        if (userViewModel._uiState.value.showTextMsg.value == true) {
            delay(3000)
            userViewModel._uiState.value.showTextMsg.value = false
        }
    }

    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )


    Surface(modifier = Modifier.fillMaxSize()) {
        //顶部菜单栏
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "好友岛",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                    backgroundColor = Green1,
                    contentColor = Color.Black,
                    elevation = 0.dp, //设置阴影
                    //左侧按钮
                    navigationIcon = {

                        IconButton(onClick = nav01) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = "",
                            )
                        }
                    },
                    //右侧按钮
                    actions = {
                    }

                )
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Green1,
                                Green2
                            )
                        )
                    )
            ) {
                //好友列表按钮
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_ic_friendlist),
                        contentDescription = null,
                        modifier = Modifier
                            .size(55.dp)
                            .offset(-15.dp, 0.dp)
                            .clickable(
                                onClick = nav02,
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }
                //天气顶图
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -65.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_bg_weather),
                        contentDescription = null,
                        modifier = Modifier
                            .width(270.dp)
                    )
                }

                Box(modifier = Modifier.offset(y = -40.dp)) {
                    //弹出的消息小卡片
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = -70.dp)
                    ) {
                        AnimatedVisibility(
                            visible = userViewModel.uiState.value.showTextMsg.value,
                            enter = slideInVertically(initialOffsetY = { 100 }) + fadeIn(
                                initialAlpha = 0.3f
                            ),
                            exit = fadeOut(targetAlpha = 0f) + slideOutVertically()

                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .padding(horizontal = 60.dp) // 外边距
                                ,
                                backgroundColor = Color(0xfff5f5f7),
                                shape = MaterialTheme.shapes.medium,
                                elevation = 1.5.dp

                            ) {
                                //上方区域
                                Column(
                                    //设置内边距
                                    modifier = Modifier.padding(
                                        start = 0.dp,
                                        end = 0.dp,
                                        top = 10.dp,
                                        bottom = 30.dp
                                    )
                                ) {
                                    //文字输入区
                                    Row(modifier = Modifier) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color(0xfff5f5f7)),
                                            contentAlignment = Alignment.TopStart
                                        ) {
                                            BasicTextField(readOnly = true,
                                                value = userViewModel.uiState.value.msgItem.value.textMsg,
                                                onValueChange = {
                                                },
                                                textStyle = TextStyle(
                                                    color = textGray,
                                                    fontWeight = W800
                                                ),
                                                modifier = Modifier
                                                    .background(Color(0xfff5f5f7), CircleShape)
                                                    .fillMaxWidth(),
                                                decorationBox = { innerTextField ->
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier.padding(horizontal = 10.dp)
                                                    ) {
//                                            IconButton(
//                                                onClick = { }
//                                            ) {
//                                                Icon(painterResource(id = R.drawable.g4_2_ic_friendlist), null)
//                                            }
                                                        Box(
                                                            modifier = Modifier
                                                                .weight(1f)
                                                                .fillMaxSize(),
                                                            contentAlignment = Alignment.TopStart
                                                        ) {
                                                            innerTextField()
                                                        }
//                                            IconButton(
//                                                onClick = { },
//                                            ) {
//                                                Icon(Icons.Filled.Send, null)
//                                            }
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }

                                //下方区域
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 9.dp, horizontal = 16.dp),
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    //消息发送时间标注
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            buildAnnotatedString {
                                                append(userViewModel.uiState.value.msgItem.value.userName)
                                            },
                                            fontSize = 12.sp,
                                            color = Gray1
                                        )
                                        Text(
                                            buildAnnotatedString {
//                            withStyle(
//                                style = SpanStyle(
//                                    fontWeight = FontWeight.W600,
//                                    fontSize = 12.sp,
//                                    color = Color(0xFF4552B8)
//                                )
//                            ) {
//                                append("20")
//                            }
                                                append(userViewModel.uiState.value.msgItem.value.msgTime)
                                            },
                                            fontSize = 12.sp,
                                            color = Gray1
                                        )
                                    }
                                }


                            }
                        }


                    }

                    // 地图扫描动画背景
                    MapBgAnimation(nav03, nav04, userViewModel = userViewModel,controller)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 400.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_2_btn_publish),
                            contentDescription = null,
                            modifier = Modifier
                                .height(60.dp)
                                .clickable(
                                    onClick = nav03,
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                )
                        )
                    }

                }


            }

            //图片消息弹窗
            if (userViewModel.uiState.value.showImgMsgDialog.value) {
                Dialog(
                    onDismissRequest = {
                        userViewModel._uiState.value.showImgMsgDialog.value = false
                    },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //头像、用户名、好友标签、发布时间
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            //头像、用户名、好友标签
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = userViewModel.uiState.value.msgItem.value.userAvatar),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = userViewModel.uiState.value.msgItem.value.userName,
                                    style = TextStyle(
                                        fontWeight = FontWeight.W600, //设置字体粗细
                                        fontSize = 18.sp,
                                        color = Color.White
                                    ),
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.g4_2_ic_friendtag),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(40.dp)
                                        .clickable { }
                                )
                            }

                            //发布时间
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = userViewModel.uiState.value.msgItem.value.msgTime,
                                    style = TextStyle(
                                        fontWeight = FontWeight.W400, //设置字体粗细
                                        fontSize = 15.sp,
                                        color = Color.White
                                    ),
                                )
                            }
                        }
                        //图片消息
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = userViewModel.uiState.value.msgItem.value.imgMsg),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            )
                        }
                        //关闭按钮
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp), horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(20.dp)
                                    .clickable(
                                        onClick = {
                                            userViewModel.uiState.value.showImgMsgDialog.value =
                                                false
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

    }

}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun plantModelItem(
    name: String,
    plantType: Int,//plantType
    res:Int,//userAvatar
    offsetX: Float = 0f,
    offsetY: Float = 0f,
    textMsg: String,
    imgMsg: Int,
    nav2: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    item: FriendItem,
    controller:NavHostController
) {
    Column(
        modifier = Modifier.offset(offsetX * 100.dp, offsetY * 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier, contentAlignment = Alignment.TopEnd) {

            //右上角消息小红点
            if (textMsg != "" || imgMsg != 0) {
                Row(modifier = Modifier.padding(6.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_ic_msgdot),
                        contentDescription = null,
                        modifier = Modifier
                            .size(7.dp)
                            .clickable(
                                onClick = {
                                },
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }
            } else {
            }

            //植物图片
            if (textMsg != "" || imgMsg != 0) {
                Image(
                    painter = painterResource(id = plantType),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clickable(
                            onClick = {
                                if (textMsg != "") {
                                    userViewModel.uiState.value.msgItem.value = item
                                    userViewModel.uiState.value.showImgMsgDialog.value = false
                                    userViewModel.uiState.value.showTextMsg.value = true
                                    //清空好友的消息,用于消除红点
                                    userViewModel.uiState.value.friendListData[userViewModel.uiState.value.friendListData.indexOf(
                                        item
                                    )] =
                                        FriendItem(
                                            item.userName,
                                            item.userAvatar,
                                            item.userPlant,
                                            item.offsetX,
                                            item.offsetY,
                                            "",
                                            0,
                                            item.onlineTime,
                                            item.msgTime
                                        )
//                                    controller.navigate("4.5-island-visitOther/$res/$name")
                                } else if (imgMsg != 0) {
                                    userViewModel.uiState.value.msgItem.value = item
                                    userViewModel.uiState.value.showTextMsg.value = false
                                    userViewModel.uiState.value.showImgMsgDialog.value = true
                                    //清空好友的消息,用于消除红点
                                    userViewModel.uiState.value.friendListData[userViewModel.uiState.value.friendListData.indexOf(
                                        item
                                    )] =
                                        FriendItem(
                                            item.userName,
                                            item.userAvatar,
                                            item.userPlant,
                                            item.offsetX,
                                            item.offsetY,
                                            "",
                                            0,
                                            item.onlineTime,
                                            item.msgTime
                                        )
//                                    controller.navigate("4.5-island-visitOther/$res/$name")//这里将id拼接到参数后面
                                }
                            },
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        )
                )

            }
            //没有消息红点的植物
            else {
                Image(
                    painter = painterResource(id = plantType),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clickable(
                            onClick = {
                                controller.navigate("4.5-island-visitOther/$res/$name")
                                      } ,
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        )
                )
            }

        }
        //用户名
        Text(
            text = name,
            style = TextStyle(
                fontWeight = FontWeight.W500, //设置字体粗细
                fontSize = 12.sp,
            ),
            modifier = Modifier
        )
    }

}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MapBgAnimation(
    nav: () -> Unit = {},
    nav2: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    controller: NavHostController
) {
    //控制播放
    var isPlaying by remember {
        mutableStateOf(true)
    }
    //控制播放速度
    var speed by remember {
        mutableStateOf(1f)
    }
    LaunchedEffect(1) {
        delay(2000)
        isPlaying = false
    }
    //控制循环播放次数 累加该值可以增加播放次数
    var interations by remember {
        mutableStateOf(1)
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/map2.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = interations,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = true  // 暂停后重新播放是否从头开始
    )

    Box(modifier = Modifier.offset(y = -80.dp), contentAlignment = Alignment.Center) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(450.dp)
                .alpha(if (isPlaying == false) 0f else 1f)
        )
        //用户本人的植物模型
        plantModelItem(
            userViewModel.uiState.value.meItem.value.userName,
            userViewModel.uiState.value.meItem.value.userPlant,
            userViewModel.uiState.value.meItem.value.userAvatar,
            userViewModel.uiState.value.meItem.value.offsetX,
            userViewModel.uiState.value.meItem.value.offsetY,
            userViewModel.uiState.value.meItem.value.textMsg,
            userViewModel.uiState.value.meItem.value.imgMsg,
            nav2,
            userViewModel = userViewModel,
            userViewModel.uiState.value.meItem.value,
            controller
        )

        userViewModel.uiState.value.friendListData.forEachIndexed { index, item ->
            plantModelItem(
                item.userName,
                item.userPlant,
                item.userAvatar,
                item.offsetX,
                item.offsetY,
                item.textMsg,
                item.imgMsg,
                nav2,
                userViewModel = userViewModel,
                item,
                controller
            )
        }

    }
}
