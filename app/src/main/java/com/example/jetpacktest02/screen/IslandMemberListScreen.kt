package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpacktest02.IslandDeliver
import com.example.jetpacktest02.IslandMemberList
import com.example.jetpacktest02.IslandNearbyMemberList
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.ViewModel.FriendItem
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FriendList(
    nav02: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),navController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
//            Box(modifier = Modifier.size(100.dp).background(Color.White))
        Column() {
            Row(modifier = Modifier.padding(horizontal = 26.dp)) {
                Text(text = "我的好友", fontSize = 14.sp, fontWeight = W900)
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp) // 外边距
                ,
                backgroundColor = Color.White,
                shape = MaterialTheme.shapes.large,
                elevation = 1.5.dp

            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp, 15.dp, 15.dp, 15.dp)
                ) {
                    userViewModel.uiState.value.friendListData.forEachIndexed { index, item ->
                        FriendItem(nav02, userViewModel, item,navController)
                        if (index == userViewModel.uiState.value.friendListData.size - 1) {
                        } else {
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                    }
                }
            }
        }

    }
}

@Composable
fun InviteCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
//            Box(modifier = Modifier.size(100.dp).background(Color.White))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(15.dp) // 外边距
            ,
            backgroundColor = Color.White,
            shape = MaterialTheme.shapes.large,
            elevation = 1.5.dp

        ) {
            Column(
                Modifier
                    .padding(15.dp)
            ) {

                Row() {
                    Text(text = "邀请好友来绿岛", fontSize = 14.sp, fontWeight = W900)
                }
                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_btn_wechat),
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_btn_pyq),
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_btn_qq),
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_btn_copycommand),
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_btn_more),
                            contentDescription = null,
                            modifier = Modifier
                                .size(105.dp)
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SwitchArea(
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
//            Box(modifier = Modifier.size(100.dp).background(Color.White))
        Column() {
            Row(modifier = Modifier.padding(horizontal = 25.dp)) {
                Text(text = "设置状态", fontSize = 14.sp, fontWeight = W900)
            }
            Row(
                modifier = Modifier.padding(40.dp, 10.dp, 20.dp, 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column() {
                    //按钮
                    if (userViewModel.uiState.value.meVisible.value) {
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_btn_becomeinvisible),
                            contentDescription = null,
                            modifier = Modifier
                                .size(55.dp)
                                .clickable(onClick = {
                                    userViewModel.uiState.value.meVisible.value = !userViewModel.uiState.value.meVisible.value
                                }),
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.g4_3_btn_becomevisible),
                            contentDescription = null,
                            modifier = Modifier
                                .size(55.dp)
                                .clickable(onClick = {
                                    userViewModel.uiState.value.meVisible.value = !userViewModel.uiState.value.meVisible.value
                                }),
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    when (userViewModel.uiState.value.meVisible.value) {
                        true -> {
                            Text(
                                text = "所有人可见",
                                fontSize = 12.sp,
                                fontWeight = W600,
                                textAlign = TextAlign.Center
                            )
                        }
                        false -> {
                            Text(
                                text = "仅自己可见",
                                fontSize = 12.sp,
                                fontWeight = W600,
                                textAlign = TextAlign.Center
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
fun FriendItem(
    nav02: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    item: FriendItem,navController: NavController
) {
    Row(modifier = Modifier.height(50.dp),verticalAlignment = Alignment.CenterVertically,) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            //用户头像
            Image(
                painter = painterResource(id = item.userAvatar),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp),
            )
            Column(
                modifier = Modifier
                    .width(150.dp)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center
            ) {
                //用户名字
                Text(
                    text = item.userName,
                    style = TextStyle(
                        fontWeight = FontWeight.W700, //设置字体粗细
                        fontSize = 14.sp,
                    ),
                )
                //上线记录
                Text(
                    text = item.onlineTime,
                    style = TextStyle(
                        fontWeight = FontWeight.W400, //设置字体粗细
                        fontSize = 14.sp,
                        color = Color(0xffB8C2C0)
                    ),
                    modifier = Modifier.offset(y = 3.dp)
                )
            }
        }
        //私信按钮
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.g4_3_ic_sendmessage),
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp)
                    .clickable(
                        onClick = {
                            userViewModel.uiState.value.visitItem.value.userName = item.userName
                            userViewModel.uiState.value.visitItem.value.userAvatar = item.userAvatar

                            navController.navigate(IslandDeliver.route) {
                                launchSingleTop = true; popUpTo(IslandMemberList.route) {}
                            }

                        },
                        indication = null,
                        interactionSource = MutableInteractionSource())
            )
        }
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun IslandMemberListScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),navController: NavController
) {
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
                            text = "好友列表",
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
            //绘制背景渐变色
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Green1,
                                Green2
                            )
                        )
                    )
            ) {

                //页面组件
                InviteCard()
                Spacer(modifier = Modifier.height(20.dp))
                FriendList(nav02, userViewModel = userViewModel,navController=navController)
                Spacer(modifier = Modifier.height(20.dp))
                SwitchArea(userViewModel)
                Spacer(modifier = Modifier.height(20.dp))

            }
        }

    }

}



