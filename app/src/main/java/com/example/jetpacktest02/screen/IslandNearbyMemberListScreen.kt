package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandNearbyMemberListScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {}
) {

    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    var requestFriendDialog by remember {
        mutableStateOf(false)
    }

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
                NearbyFriendList(nav02,{requestFriendDialog = true})
                Spacer(modifier = Modifier.height(20.dp))
                SwitchArea()

            }

            //添加好友弹窗
            if (requestFriendDialog) {
                Dialog(
                    onDismissRequest = { requestFriendDialog = false },
                ) {
                    Column() {
                        androidx.compose.material3.Card(
//                    onClick = { /* Do something */ },
                            modifier = Modifier.size(width = 380.dp, height = 350.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                Spacer(modifier = Modifier.height(15.dp))
                                Text(
                                    text = "好友申请",
                                    style = TextStyle(
                                        fontWeight = FontWeight.ExtraBold, //设置字体粗细
                                        fontSize = 22.sp,
                                    ),
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.g2_1_img_user04),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .scale(1.2f)
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "施&SHI", modifier = Modifier.align(
                                        Alignment.CenterHorizontally
                                    )
                                )
                                Spacer(modifier = Modifier.height(15.dp))
                                androidx.compose.material3.TextField(
                                    value = text,
                                    shape = RoundedCornerShape(10.dp),
                                    onValueChange = { text = it },
                                    singleLine = false,
                                    placeholder = {
                                        Text(
                                            "写两句话和好友打招呼吧",
                                            fontSize = 14.sp,
                                            color = Gray2
                                        )
                                    },
//                            label={ Text("写两句话和好友打招呼吧", fontSize = 14.sp, color = Gray2) },
                                    modifier = Modifier
                                        .size(250.dp, 60.dp)
                                        .align(Alignment.CenterHorizontally),
                                    colors = TextFieldDefaults.textFieldColors(
                                        textColor = Gray2,
                                        containerColor = Gray3,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    )
                                )
                                Spacer(modifier = Modifier.height(40.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.g4_4_btn_addfriend),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .clickable(onClick = { requestFriendDialog = false }),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g4_4_btn_close),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally).clickable(onClick = {requestFriendDialog=false})
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun NearbyFriendList(nav02: () -> Unit = {},showRequestFriendDialog: () -> Unit) {
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
                    NearbyFriendItem(nav02,showRequestFriendDialog)
                    Spacer(modifier = Modifier.height(20.dp))
                    NearbyFriendItem(nav02,showRequestFriendDialog)
                    Spacer(modifier = Modifier.height(20.dp))
                    NearbyFriendItem(nav02,showRequestFriendDialog)
                    Spacer(modifier = Modifier.height(20.dp))
                    NearbyFriendItem(nav02,showRequestFriendDialog)
                }
            }
        }

    }
}


@Composable
fun NearbyFriendItem(nav02: () -> Unit = {},showRequestFriendDialog:()->Unit={}) {
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
                        painter = painterResource(id = R.drawable.g2_2_btn_friend),
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp).clickable(onClick = showRequestFriendDialog),
                    )
                }
            }
            //私信按钮
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier.padding(end = 5.dp)
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