package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MessageFriendScreen(nav01: () -> Unit = {}) {
    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = "好友列表",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(15.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                //左侧按钮
                navigationIcon = {
                    IconButton(onClick = nav01) {
                        Icon(
                            painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = ""
                        )

                    }
                },
                //右侧按钮
                actions = {
//                    IconButton(onClick = nav01) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.g2_5_1_btn_friend),
//                            contentDescription = ""
//                        )
//                    }
                    Image(
                        painter = painterResource(id = R.drawable.g2_5_btn_friend),
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp).offset(-10.dp,0.dp)
                    )

                },

                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ) {
        Column(Modifier.padding(10.dp)) {
            FriendTabRow()
        }
        val messages by remember {
            mutableStateOf(
                listOf(
                    //如果需要改变下面对象里面的属性，需要单独复制一份生成一个新的对象才可以
                    MsgTest(
                        1,
                        "aaaa"
                    ),
                    MsgTest(
                        2,
                        "bbbbb"
                    ),
                    MsgTest(
                        3,
                        "bbbbb"
                    ),
                    MsgTest(
                        4,
                        "ccccccccc"
                    ),
                    MsgTest(
                        4,
                        "dcccccccc"
                    ),
                    MsgTest(
                        4,
                        "ecccccccc"
                    ),
                    MsgTest(
                        4,
                        "fcccccccc"
                    ),
                    MsgTest(
                        4,
                        "gcccccccc"
                    ),
                    MsgTest(
                        4,
                        "hcccccccc"
                    ),
                    MsgTest(
                        4,
                        "icccccccc"
                    ),
                    MsgTest(
                        4,
                        "jcccccccc"
                    ),
                    MsgTest(
                        4,
                        "kcccccccc"
                    ),
                    MsgTest(
                        4,
                        "lcccccccc"
                    ),
                    MsgTest(
                        4,
                        "hcccccccc"
                    ),

                    )
            )
        }
        val grouped = messages.groupBy { it.msg[0] }
//        MessageList(grouped)
    }
}

@Composable
fun FriendTabRow() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("好友", "附近", "可能认识")

//    val indicator = @Composable { tabPositions: List<TabPosition> ->
//        TabIndicator(
//        )
//    }

    Column {
        TabRow(
            selectedTabIndex = state,
            indicator = @Composable { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.customTabIndicatorOffset(tabPositions[state]),
                    color = LightGreen
                )
            }
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier
                        .background(Color.White)
                        .width(10.dp),
                    selected = state == index,
                    onClick = { state = index },
                    text = {
                        Text(
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
//            Text(
//                modifier = Modifier.align(Alignment.CenterHorizontally),
//                text = "Fancy tab ${state + 1} selected",
//                style = MaterialTheme.typography.body2
//            )
    }
}


fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset + currentTabWidth / 4)  // 偏移量加上宽度的1/4
        .width(currentTabWidth / 2)  // 宽度Tab宽度的1/2
        .height(4.dp)

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageList(grouped: Map<Char, List<MsgTest>>) {
    val state = rememberLazyListState()
    val coroutinueScope = rememberCoroutineScope()
    Row() {
        Button(onClick = {
            coroutinueScope.launch {
                state.animateScrollToItem(
                    index = 10
                )
            }
        }) {

        }

        LazyColumn(state = state) {
            grouped.forEach { (initial, contactsForInitial) ->
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .background(Gray2)
                            .height(80.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = initial.toString())
                    }
                }

                items(contactsForInitial) { contact ->
                    Text(text = contact.msg)
                }
            }
        }
    }
}


data class MsgTest(
    val id: Int,
    val msg: String,//给你留言了
)