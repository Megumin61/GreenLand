package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.config.UsersApplication
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition",
    "Range"
)
@Composable
fun MessageFriendScreen(
    nav01: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    controller: NavHostController
) {
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
                            modifier = Modifier.offset(-25.dp, 0.dp)//向左偏移一段距离
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
//                    Image(
//                        painter = painterResource(id = R.drawable.g2_5_btn_friend),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .width(100.dp)
//                            .height(100.dp)
//                            .offset(-10.dp, 0.dp)
////                            .clickable(onClick = {userViewModel.uiState.value.pageState.value=3})
//                    )
                },

                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ) {
        val friends by remember {
            mutableStateOf(
                listOf(
                    //如果需要改变下面对象里面的属性，需要单独复制一份生成一个新的对象才可以
                    FriendTest(
                        1,
                        "ajunGrit",
                        "1天前在线",
                        res = R.drawable.g2_5_img_user05
                    ),
                    FriendTest(
                        2,
                        "kevin",
                        "4天前在线",
                        res = R.drawable.g2_5_img_user03
                    ),
                    FriendTest(
                        3,
                        "aJuan",
                        "1天前在线",
                        res = R.drawable.g2_5_img_user04
                    ),
                    FriendTest(
                        4,
                        "sandr",
                        "在线",
                        res = R.drawable.g2_5_img_user02
                    ),
                    FriendTest(
                        5,
                        "liu猪侨",
                        "在线",
                        res = R.drawable.g2_5_img_user01
                    ),
                    FriendTest(
                        6,
                        "joyce",
                        "1天前在线",
                        res = R.drawable.g2_1_img_user01
                    ),
                    FriendTest(
                        7,
                        "foxbread",
                        "在线",
                        res = R.drawable.g2_1_img_user05
                    ),
                    FriendTest(
                        8,
                        "kcChang",
                        "1天前在线",
                        res = R.drawable.g2_1_img_user03
                    ),

                    )
            )
        }
        val grouped = friends.groupBy { it.name[0] }

        //state为顶部的tab导航栏绑定参数
        var state = userViewModel.uiState.value.pageState
        //pagerState为底部viewpager参数
        val pagerState: PagerState = remember { PagerState() }

        //将底部pager的参数和顶部导航栏的参数state绑定，让pager响应顶部导航栏参数变化
        LaunchedEffect(pagerState) {
            snapshotFlow { state.value }.collect { page ->
                pagerState.animateScrollToPage(page)
            }
        }
        //将底部pager的参数和顶部导航栏的参数state绑定
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                if (page != 3) {
                    state.value = page
                }
            }
        }

        Column(Modifier.padding(5.dp)) {
            FriendTabRow(userViewModel, pagerState)
            Text(text = userViewModel.uiState.value.currentRoot)
            //https://blog.csdn.net/haojiagou/article/details/123040803?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522167548132816800180688371%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=167548132816800180688371&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~baidu_landing_v2~default-4-123040803-null-null.142^v73^pc_new_rank,201^v4^add_ask,239^v1^control&utm_term=compose%20viewpager&spm=1018.2226.3001.4187
            //Pager核心代码,count为页面总数
            HorizontalPager(count = 4, state = pagerState) { page ->
//                Text(text = "Page: $page")
                //下面为要滑动切换的界面，可以通过判断page调用不同页面
//                Text(page.toString())
                FriendList(grouped, controller = controller)
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class, DelicateCoroutinesApi::class)
@Composable
fun FriendTabRow(userViewModel: UserViewModel, pagerState: PagerState) {
    val titles = listOf("好友", "附近", "可能认识")
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TabRow(
                modifier = Modifier.width(270.dp),
                backgroundColor = Color.White,
                selectedTabIndex = userViewModel.uiState.value.pageState.value,
                indicator = @Composable { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.customTabIndicatorOffset(tabPositions[userViewModel.uiState.value.pageState.value]),
                        color = LightGreen
                    )
                },

                ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(0.dp),
                        selected = userViewModel.uiState.value.pageState.value == index,
                        onClick = {
                            userViewModel.uiState.value.pageState.value = index;
                        },
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.W800,
                            )
                        },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Gray1
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.g2_5_btn_friend),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .offset(-10.dp, 0.dp)
                    .clickable(onClick = {
                        coroutineScope.launch {
                            // Animate scroll to the first item
                            pagerState.animateScrollToPage(3)
                        }

                    })
            )
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
fun FriendList(grouped: Map<Char, List<FriendTest>>, controller: NavHostController) {
    val state = rememberLazyListState()
    val coroutinueScope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,//子元素的水平方向排列效果
    ) {
        LazyColumn(
            state = state, modifier = Modifier
                .padding(20.dp)
                .width(300.dp),
            horizontalAlignment = Alignment.Start
        ) {
            grouped.forEach { (initial, contactsForInitial) ->
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .height(20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = initial.toString().uppercase(), color = Gray1)
                    }
                }

                items(contactsForInitial) { contact ->
                    FriendMessageItem(contact.name, contact.msg, contact.res, controller)
                    Divider(thickness = 1.dp, color = BlueGray1)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
        TextButton(state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendMessageItem(
    name: String,
    msg: String,
    res: Int,
    controller: NavHostController
) {
    ListItem(
        modifier = Modifier
//            .clickable(onClick = nav01)
            .background(
                color = Color.White
            )
            .clickable(onClick = {
                controller.navigate("4.5-island-visitOther/$res/$name")//这里将参数拼接到参数后面
            }),
        colors = ListItemDefaults.colors(containerColor = Color.White),
        headlineText = {
            Column() {
                androidx.compose.material3.Text(
                    text = name,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                )
            }

        },
        supportingText = {
            Spacer(modifier = Modifier.height(4.dp))
            androidx.compose.material3.Text(
                msg,
                fontSize = 13.sp,
                style = MaterialTheme.typography.bodySmall,
                color = Gray1,
                textAlign = TextAlign.Left,
            )
        },
        leadingContent = {
            Image(
                painter = painterResource(id = res),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
//                    .clickable(onClick = nav02)
            )
        }
    )

}

@Composable
fun TextButton(state: LazyListState) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.offset(-5.dp, 20.dp),
        horizontalAlignment = Alignment.End
    ) {
        // Add 5 items
        // 添加多个
        val firstWord: String = "ABCDEFGHIJKLMNOPQISVUVWXYZ0000"
        items(26) { index ->
            val letter: Char = firstWord[index]
            androidx.compose.material3.TextButton(onClick = {
                coroutineScope.launch {
                    // Animate scroll to the first item
                    state.animateScrollToItem(index = 10)
                }
            }, modifier = Modifier.size(36.dp)) {
                androidx.compose.material3.Text(
                    text = "$letter",
                    color = Gray4,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                )
            }
        }

    }
}

data class FriendTest(
    val id: Int,
    val name: String,
    val msg: String,//给你留言了
    val res: Int
)