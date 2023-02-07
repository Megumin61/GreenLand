package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresPermission
import androidx.compose.animation.*
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
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.ui.main.DialogCard
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.permissions.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalPermissionsApi::class)
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
        DialogCard(userViewModel)
        val state = remember {
            mutableStateOf(true)
        }
        Column(Modifier.padding(5.dp)) {
            FriendTabRow(userViewModel, controller)
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                AnimatedVisibility(
//                    visible = state.value,
//                    enter = slideInVertically(initialOffsetY = { -40 }) + expandVertically(
//                        expandFrom = Alignment.Top
//                    ) + fadeIn(initialAlpha = 0.3f), exit = shrinkHorizontally() + fadeOut()
//                ) {
//                    Text(
//                        text = "ssss",
//                        fontWeight = FontWeight.W900,
//                        style = MaterialTheme.typography.headlineMedium
//                    )
//                    Image(
//                        painter = painterResource(id = R.drawable.g2_5_btn_friend),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .width(100.dp)
//                            .height(100.dp)
//                            .offset(-10.dp, 0.dp)
//                            .clickable(onClick = {
//                                state.value = !state.value
//                            })
//                    )
//                }
//                Button(onClick = { state.value = !state.value }) {
//                    Text(if (state.value) "隐藏" else "显示")
//                }
//            }
            Text(text = userViewModel.uiState.value.currentRoot)
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalPagerApi::class, DelicateCoroutinesApi::class,
    ExperimentalPermissionsApi::class
)
@Composable
fun FriendTabRow(
    userViewModel: UserViewModel,
    controller: NavHostController
) {
    //申请查看联系人权限
//    val permissionState =
//        rememberPermissionState(permission = android.Manifest.permission.READ_CONTACTS)


    val titles = listOf("好友", "附近", "可能认识")
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    userViewModel.uiState.value.searchText = text.text
    val coroutineScope = rememberCoroutineScope()

    //认识的好友
    val friends by remember {
        mutableStateOf(
            listOf(
                //如果需要改变下面对象里面的属性，需要单独复制一份生成一个新的对象才可以
                FriendTest(
                    1,
                    "ajunGrit",
                    "1天前在线",
                    res = R.drawable.g2_5_img_user05,
                    count = "有 3 个共同朋友"
                ),
                FriendTest(
                    2,
                    "kevin",
                    "4天前在线",
                    res = R.drawable.g2_5_img_user03,
                    count = "有 1 个共同朋友"
                ),
                FriendTest(
                    3,
                    "aJuan",
                    "1天前在线",
                    res = R.drawable.g2_5_img_user04,
                    count = "有 0 个共同朋友"
                ),
                FriendTest(
                    4,
                    "sandr",
                    "在线",
                    res = R.drawable.g2_5_img_user02,
                    count = "有 1 个共同朋友"
                ),
                FriendTest(
                    5,
                    "liu猪侨",
                    "在线",
                    res = R.drawable.g2_5_img_user01,
                    count = "有 1 个共同朋友"
                ),
                FriendTest(
                    6,
                    "joyce",
                    "1天前在线",
                    res = R.drawable.g2_1_img_user01,
                    count = "有 2 个共同朋友"
                ),
                FriendTest(
                    7,
                    "foxbread",
                    "在线",
                    res = R.drawable.g2_1_img_user05,
                    count = "有 4 个共同朋友"
                ),
                FriendTest(
                    8,
                    "kcChang",
                    "1天前在线",
                    res = R.drawable.g2_1_img_user03,
                    count = "有 6 个共同朋友"
                ),

                )
        )
    }
    val grouped: Map<Char, List<FriendTest>>
    val result = mutableListOf<FriendTest>()
    if (userViewModel.uiState.value.searchText != "") {
        for (i: FriendTest in friends) {
            if (i.name.contains(userViewModel.uiState.value.searchText)) {
                result.add(i)
            }
        }
        grouped = result.groupBy { it.name[0] }
    } else {
        grouped = friends.groupBy { it.name[0] }
    }


    //state为顶部的tab导航栏绑定参数
    val state = userViewModel.uiState.value.pageState
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

        HorizontalPager(count = 4, state = pagerState) { page ->
//                Text(text = "Page: $page")
            //下面为要滑动切换的界面，可以通过判断page调用不同页面
//                Text(page.toString())
            if (page == 0) {
                FriendList(
                    grouped = grouped,
                    controller = controller,
                    isFriend = false,
                    isSearch = false,
                    userViewModel
                )
            }
            if (page == 1 || page == 2) {
                FriendList(
                    grouped = grouped,
                    controller = controller,
                    isFriend = true,
                    isSearch = false,
                    userViewModel
                )
            }
            if (page == 3) {
                IconButtonFriendList(
                    grouped = grouped,
                    controller = controller,
                    userViewModel = userViewModel
                )
            }
        }
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
fun FriendList(
    grouped: Map<Char, List<FriendTest>>,
    controller: NavHostController,
    isFriend: Boolean,
    isSearch: Boolean,
    userViewModel: UserViewModel
) {
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
                    if (isSearch == true) {
                        FriendMessageItem(
                            contact.name,
                            contact.count,
                            contact.res,
                            controller,
                            isFriend,
                            userViewModel = userViewModel
                        )
                    } else {
                        FriendMessageItem(
                            contact.name,
                            contact.msg,
                            contact.res,
                            controller,
                            isFriend,
                            userViewModel = userViewModel
                        )
                    }
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
    controller: NavHostController,
    isFriend: Boolean,
    userViewModel: UserViewModel
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
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.Text(
                    text = name,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                )
                if (isFriend == true) {
                    Spacer(Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.g2_1_btn_friend),
                        contentDescription = null,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                            .clickable(onClick = {
                                userViewModel._uiState.value.openDialog.value = true
                            })
                    )
                }
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun IconButtonFriendList(
    grouped: Map<Char, List<FriendTest>>,
    controller: NavHostController,
    userViewModel: UserViewModel
//    permissionState: PermissionState
//    nav01: () -> Unit = {},//通讯录
//    nav02: () -> Unit = {},//二维码和添加好友
) {
    val isContact = remember {
        mutableStateOf(false)
    }
    //获取读取通讯录的权限
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.READ_CONTACTS
        )
    )
    Column() {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,//子元素的水平方向排列效果

        ) {
            Spacer(Modifier.height(30.dp))
            //通讯录
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_directory),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable(onClick = {
//                    permissionState.launchPermissionRequest()
                        isContact.value = !isContact.value;
                        locationPermissionsState.launchMultiplePermissionRequest()
                    })

            )
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_code),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
//                .clickable(onClick = nav02)

            )
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_scan),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_scan1),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
//                .clickable(onClick = nav02)
            )

        }
        if (isContact.value == false) {
            FriendList(
                grouped = grouped,
                controller = controller,
                isFriend = true,
                isSearch = true,
                userViewModel
            )
        } else {
            Sample(locationPermissionsState, userViewModel,controller)
        }
    }
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

@SuppressLint("Range", "Recycle", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun Sample(
    locationPermissionsState: MultiplePermissionsState,
    userViewModel: UserViewModel,
    controller: NavHostController
) {
    val contactsList = ArrayList<String>()
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver

//    val locationPermissionsState = rememberMultiplePermissionsState(
//        listOf(
//            android.Manifest.permission.READ_CONTACTS
//        )
//    )
    if (locationPermissionsState.allPermissionsGranted) {
//        Text("Thanks! I can access your exact location :D")
//        Text(userViewModel.uiState.value.searchText)
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null
        )?.apply {
            while (moveToNext()) {
                // 获取联系人姓名
                val displayName = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
                )
                // 获取联系人手机号
                val number = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )
//                contactsList.add("$displayName\n$number")
                contactsList.add(displayName)
            }
        }
        //通讯录用户，初始化，填充获取通讯录名称至对象
        val friendlist = mutableListOf<FriendTest>()
        for(i:String in contactsList)
        {
            friendlist.add(FriendTest(0,i,"有 1 个共同朋友","有 1 个共同朋友",R.drawable.g2_5_img_user01))
        }

        val grouped: Map<Char, List<FriendTest>>
        val result = mutableListOf<FriendTest>()
        if (userViewModel.uiState.value.searchText != "") {
            for (i: FriendTest in friendlist) {
                if (i.name.contains(userViewModel.uiState.value.searchText)) {
                    result.add(i)
                }
            }
            grouped = result.groupBy { it.name[0] }
        } else {
            grouped = friendlist.groupBy { it.name[0] }
        }

        Text(text = contactsList[0])
        FriendList(
            grouped = grouped,
            controller = controller,
            isFriend = true,
            isSearch = true,
            userViewModel
        )

    }

//    else {
//        Column {
//            val allPermissionsRevoked =
//                locationPermissionsState.permissions.size ==
//                        locationPermissionsState.revokedPermissions.size
//
//            val textToShow = if (!allPermissionsRevoked) {
//                // If not all the permissions are revoked, it's because the user accepted the COARSE
//                // location permission, but not the FINE one.
//                "Yay! Thanks for letting me access your approximate location. " +
//                        "But you know what would be great? If you allow me to know where you " +
//                        "exactly are. Thank you!"
//            } else if (locationPermissionsState.shouldShowRationale) {
//                // Both location permissions have been denied
//                "Getting your exact location is important for this app. " +
//                        "Please grant us fine location. Thank you :D"
//            } else {
//                // First time the user sees this feature or the user doesn't want to be asked again
//                "This feature requires location permission"
//            }
//
//            val buttonText = if (!allPermissionsRevoked) {
//                "Allow precise location"
//            } else {
//                "Request permissions"
//            }
//            Text(text = textToShow)
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = { locationPermissionsState.launchMultiplePermissionRequest() }) {
//                Text(buttonText)
//            }
}


data class FriendTest(
    val id: Int,
    val name: String,
    val msg: String,//给你留言了
    val count: String,//共同好友数量
    val res: Int
)
