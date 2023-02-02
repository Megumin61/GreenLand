package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.Flesh1
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IslandScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {}
) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight)


    Surface(modifier = Modifier.fillMaxSize()) {
        //绘制背景渐变色
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
                                .clickable(onClick = nav02)

                        )
                    }
                    Spacer(Modifier.height(40.dp))


                    Text(
                        text = "·关注朋友们的生活状态，增进互动",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xff9598ac)
                    )

                    Spacer(Modifier.height(25.dp))

                    Text(
                        text = "·探索附近的岛友，多样互动，让生活更有乐趣",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xff9598ac)
                    )
                }
            }

        }
    }
}

