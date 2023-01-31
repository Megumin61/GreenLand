package com.example.jetpacktest02.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scaffolddemo.ui.theme.Green1

@Composable

/*
* 顶部菜单栏 公共组件案例
* 复制修改即可使用
* (注意：TopAppBar必须放在Scaffold的topBar属性下，详细可参考IslandChooseIslandScreen.kt)
* */

fun MyTopAppBar() {
    TopAppBar(title = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.Text(
                text = "岛屿选择",
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

//                            IconButton(onClick = {}) {
//                                Icon(
//                                    Icons.Default.Menu,
//                                    contentDescription = "",
//                                )
//                            }
        },
        //右侧按钮
        actions = {
//                            IconButton(onClick = {}) {
//                                Icon(
//                                    Icons.Default.Settings,
//                                    contentDescription = "",
//                                )
//                            }
        }
    )


    //    val showMenu = remember {
//        mutableStateOf(false)
//    }

//    TopAppBar(
//        title = {
//            Text(text =, color = Color.White)
//        },
//        backgroundColor = MaterialTheme.colors.primary,
//        navigationIcon = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Menu, contentDescription = "", tint = Color.White)
//            }
//        },
//        actions = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Settings, contentDescription = "", tint = Color.White)
//            }
//            IconButton(onClick = { showMenu.value = !showMenu.value }) {
//                Icon(Icons.Default.MoreVert, contentDescription = "", tint = Color.White)
//            }
//
//            DropdownMenu(expanded = showMenu.value, onDismissRequest = { showMenu.value = false }) {
//                DropdownMenuItem(onClick = {}) {
//                    TextButton(onClick = { showMenu.value = false }) {
//                        Text(text = "Settings", color = MaterialTheme.colors.primary)
//                    }
//                }
//                DropdownMenuItem(onClick = {}) {
//                    TextButton(onClick = { showMenu.value = false }) {
//                        Text(text = "Search", color = MaterialTheme.colors.primary)
//                    }
//                }
//            }
//        }
//    )
}