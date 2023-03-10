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
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.example.jetpacktest02.Message
import com.example.jetpacktest02.MessageID
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
    //???????????????????????????
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
                            text = "????????????",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //??????????????????
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-25.dp, 0.dp)//????????????????????????
                        )
                    }
                },
                //????????????
                navigationIcon = {
                    IconButton(onClick = nav01) {
                        Icon(
                            painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = ""
                        )

                    }
                },
                //????????????
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
                elevation = 0.dp, //????????????
            )
        }
    ) {
        DialogCard(userViewModel)
        val state = remember {
            mutableStateOf(true)
        }
        Column(Modifier.padding(5.dp)) {
            FriendTabRow(userViewModel, controller)
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
    //???????????????????????????
//    val permissionState =
//        rememberPermissionState(permission = android.Manifest.permission.READ_CONTACTS)


    val titles = listOf("??????", "??????", "????????????")
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    userViewModel.uiState.value.searchText = text.text
    val coroutineScope = rememberCoroutineScope()

    //???????????????
    val friends by remember {
        mutableStateOf(
            listOf(
                //?????????????????????????????????????????????????????????????????????????????????????????????????????????
                FriendTest(
                    1,
                    "ajunGrit",
                    "1????????????",
                    res = R.drawable.userprofile_15,
                    count = "??? 3 ???????????????"
                ),
                FriendTest(
                    2,
                    "kevin",
                    "4????????????",
                    res = R.drawable.userprofile_16,
                    count = "??? 1 ???????????????"
                ),
                FriendTest(
                    3,
                    "aJuan",
                    "1????????????",
                    res = R.drawable.userprofile_17,
                    count = "??? 0 ???????????????"
                ),
                FriendTest(
                    4,
                    "sandr",
                    "??????",
                    res = R.drawable.userprofile_18,
                    count = "??? 1 ???????????????"
                ),
                FriendTest(
                    5,
                    "liu??????",
                    "??????",
                    res = R.drawable.userprofile_19,
                    count = "??? 1 ???????????????"
                ),
                FriendTest(
                    6,
                    "joyce",
                    "1????????????",
                    res = R.drawable.userprofile_20,
                    count = "??? 2 ???????????????"
                ),
                FriendTest(
                    7,
                    "foxbread",
                    "??????",
                    res = R.drawable.userprofile_21,
                    count = "??? 4 ???????????????"
                ),
                FriendTest(
                    8,
                    "kcChang",
                    "1????????????",
                    res = R.drawable.userprofile_22,
                    count = "??? 6 ???????????????"
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


    //state????????????tab?????????????????????
    val state = userViewModel.uiState.value.pageState
    //pagerState?????????viewpager??????
    val pagerState: PagerState = remember { PagerState() }

    //?????????pager????????????????????????????????????state????????????pager?????????????????????????????????
    LaunchedEffect(pagerState) {
        snapshotFlow { state.value }.collect { page ->
            pagerState.animateScrollToPage(page)
        }
    }
    //?????????pager????????????????????????????????????state??????
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
                    .clickable(
                        onClick = {
                            coroutineScope.launch {
                                // Animate scroll to the first item
                                pagerState.animateScrollToPage(3)
                            }

                        }, indication = null,
                        interactionSource = MutableInteractionSource()
                    )
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
                    "???????????????id",
                    fontSize = 14.sp,
                    color = Gray2
                )
            },
//                            label={ Text("?????????????????????????????????", fontSize = 14.sp, color = Gray2) },
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
            //??????????????????????????????????????????????????????page??????????????????
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
        .offset(x = indicatorOffset + currentTabWidth / 4)  // ????????????????????????1/4
        .width(currentTabWidth / 2)  // ??????Tab?????????1/2
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
        horizontalArrangement = Arrangement.SpaceBetween,//????????????????????????????????????
    ) {
        LazyColumn(
            state = state, modifier = Modifier
                .padding(20.dp, 20.dp, 0.dp, 20.dp)
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
            .clickable(
                onClick = {
                    controller.navigate("4.5-island-visitOther/$res/$name")//????????????????????????????????????
                }, indication = null,
                interactionSource = MutableInteractionSource()
            ),
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
                            .clickable(
                                onClick = {
                                    userViewModel._uiState.value.openDialog.value = true
                                }, indication = null,
                                interactionSource = MutableInteractionSource()
                            )
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
//    nav01: () -> Unit = {},//?????????
//    nav02: () -> Unit = {},//????????????????????????
) {
    val isContact = remember {
        mutableStateOf(false)
    }
    //??????????????????????????????
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.READ_CONTACTS
        )
    )
    Column() {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,//????????????????????????????????????

        ) {
            Spacer(Modifier.height(20.dp))
            //?????????
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_directory),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clickable(
                        onClick = {
//                    permissionState.launchPermissionRequest()
                            isContact.value = !isContact.value;
                            locationPermissionsState.launchMultiplePermissionRequest()
                        }, indication = null,
                        interactionSource = MutableInteractionSource()
                    )

            )
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_code),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clickable(
                        onClick = {
                            controller.navigate(MessageID.route) {
                                launchSingleTop = true;popUpTo(Message.route)
                            }
                        }, indication = null,
                        interactionSource = MutableInteractionSource()
                    )

            )
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_scan),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.g2_5_1_ic_scan1),
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clickable(
                        onClick = {
                            controller.navigate(MessageID.route) {
                                launchSingleTop = true;popUpTo(Message.route)
                            }
                        }, indication = null,
                        interactionSource = MutableInteractionSource()
                    )
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
            Sample(locationPermissionsState, userViewModel, controller)
        }
    }
}

@Composable
fun TextButton(state: LazyListState) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.offset(-10.dp, 20.dp),
        horizontalAlignment = Alignment.End
    ) {
        // Add 5 items
        // ????????????
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

    if (locationPermissionsState.allPermissionsGranted) {
//        Text("Thanks! I can access your exact location :D")
//        Text(userViewModel.uiState.value.searchText)
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null
        )?.apply {
            while (moveToNext()) {
                // ?????????????????????
                val displayName = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
                )
                // ????????????????????????
                val number = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )
//                contactsList.add("$displayName\n$number")
                contactsList.add(displayName)
            }
        }
        //??????????????????????????????????????????????????????????????????
        val friendlist = mutableListOf<FriendTest>()
        for (i: String in contactsList) {
            friendlist.add(FriendTest(0, i, "??? 1 ???????????????", "??? 1 ???????????????", R.drawable.g2_5_img_user01))
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
    val msg: String,//???????????????
    val count: String,//??????????????????
    val res: Int
)
