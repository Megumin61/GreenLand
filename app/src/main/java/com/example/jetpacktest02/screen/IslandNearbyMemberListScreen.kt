package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.Flesh1
import com.example.scaffolddemo.ui.theme.Flesh2
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandNearbyMemberListScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {}
) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Flesh2, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
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
                            text = "成员列表",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                    backgroundColor = Flesh2,
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
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Flesh2,
                                Flesh1
                            )
                        )
                    )
            ) {

                //页面组件
                Spacer(modifier = Modifier.height(20.dp))
                NearbyFriendList(nav02)
                Spacer(modifier = Modifier.height(20.dp))
                SwitchArea()

            }
        }
    }
}


@Composable
fun NearbyFriendList(nav02: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
//            Box(modifier = Modifier.size(100.dp).background(Color.White))
        Column() {
            Row(modifier = Modifier.padding(horizontal = 26.dp)) {
                Text(text = "我的岛友", fontSize = 14.sp, fontWeight = FontWeight.W900)
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(325.dp)
                    .padding(15.dp),
                backgroundColor = Color.White,
                shape = MaterialTheme.shapes.large,
                elevation = 1.5.dp

            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp, 15.dp, 15.dp, 15.dp)
                ) {
                    NearbyFriendItem(nav02)
                    Spacer(modifier = Modifier.height(20.dp))
                    NearbyFriendItem(nav02)
                    Spacer(modifier = Modifier.height(20.dp))
                    NearbyFriendItem(nav02)
                    Spacer(modifier = Modifier.height(20.dp))
                    NearbyFriendItem(nav02)
                }
            }
        }

    }
}


@Composable
fun NearbyFriendItem(nav02: () -> Unit = {}) {
    Row(modifier = Modifier.height(50.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //用户头像
                Image(
                    painter = painterResource(id = R.drawable.g4_3_avatar1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp),
                )
                //用户名字、定位距离
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    //用户名字
                    Text(
                        text = "ajunGrit",
                        style = TextStyle(
                            fontWeight = FontWeight.W700, //设置字体粗细
                            fontSize = 14.sp,
                        ),
                    )

                    //定位距离
                    Text(
                        text = "距你10m",
                        style = TextStyle(
                            fontWeight = FontWeight.W400, //设置字体粗细
                            fontSize = 14.sp,
                            color = Color(0xffB8C2C0)
                        ),
                        modifier = Modifier.offset(y = 3.dp)
                    )
                }
                //好友标签
                Column(
                    modifier = Modifier.width(55.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //用户头像
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_ic_friendtag),
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp),
                    )
                }
            }
            //私信按钮
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g4_3_ic_sendmessage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(65.dp)
                        .clickable(onClick = nav02)
                )
            }
        }

    }

}