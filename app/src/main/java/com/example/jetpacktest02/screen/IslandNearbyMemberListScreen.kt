package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.widget.MultiAutoCompleteTextView
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
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
import androidx.navigation.NavController
import com.example.jetpacktest02.IslandDeliver
import com.example.jetpacktest02.IslandNearbyMemberList
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.ExploreMemberItem
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun IslandNearbyMemberListScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {

    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    var requestFriendDialog by remember {
        mutableStateOf(false)
    }

    //???????????????????????????
    rememberSystemUiController().setStatusBarColor(
        Flesh2, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    //SnackBar????????????
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        //???????????????
        Scaffold(
            snackbarHost = {
                androidx.compose.material3.SnackbarHost(snackbarHostState) { data ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        androidx.compose.material3.Snackbar(
                            modifier = Modifier.width(200.dp),
                            snackbarData = data,
                            containerColor = Green5,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(30.dp),
                        )
                    }

                }
            },
            topBar = {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "????????????",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //??????????????????
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-35.dp, 0.dp)//????????????????????????
                        )
                    }
                },
                    backgroundColor = Flesh2,
                    contentColor = Color.Black,
                    elevation = 0.dp, //????????????
                    //????????????
                    navigationIcon = {
                        IconButton(onClick = nav01) {
                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = "",
                            )
                        }
                    },
                    //????????????
                    actions = {

                    }

                )
            }
        ) {
            //?????????????????????
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Flesh2,
                                Flesh1
                            )
                        )
                    )
            ) {

                //????????????
                Spacer(modifier = Modifier.height(20.dp))
                NearbyFriendList(
                    nav02,
                    { requestFriendDialog = true },
                    userViewModel = userViewModel, navController = navController
                )
                Spacer(modifier = Modifier.height(20.dp))
                SwitchArea(userViewModel = userViewModel)

            }

            //??????????????????
            if (userViewModel.uiState.value.showRequestFriendDialog.value) {
                Dialog(
                    onDismissRequest = {
                        userViewModel.uiState.value.showRequestFriendDialog.value = false
                    },
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
                                    text = "????????????",
                                    style = TextStyle(
                                        fontWeight = FontWeight.ExtraBold, //??????????????????
                                        fontSize = 22.sp,
                                    ),
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                //????????????
                                Image(
                                    painter = painterResource(id = userViewModel.uiState.value.visitItem.value.userAvatar),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .scale(1.2f)
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = userViewModel.uiState.value.visitItem.value.userName,
                                    modifier = Modifier.align(
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
                                            "?????????????????????????????????",
                                            fontSize = 14.sp,
                                            color = Gray2
                                        )
                                    },
//                            label={ Text("?????????????????????????????????", fontSize = 14.sp, color = Gray2) },
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
                                        .clickable(
                                            onClick = {
                                                userViewModel.uiState.value.showRequestFriendDialog.value =
                                                    false
                                                scope.launch {
                                                    snackbarHostState.showSnackbar(
                                                        "????????????????????????~"
                                                    )
                                                }
//                                                text = ""

                                            },
                                            indication = null,
                                            interactionSource = MutableInteractionSource()
                                        ),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g4_4_btn_close),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .clickable(
                                    onClick = {
                                        userViewModel.uiState.value.showRequestFriendDialog.value =
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


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NearbyFriendList(
    nav02: () -> Unit = {},
    showRequestFriendDialog: () -> Unit,
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
//            Box(modifier = Modifier.size(100.dp).background(Color.White))
        Column() {
            Row(modifier = Modifier.padding(horizontal = 26.dp)) {
                Text(text = "????????????", fontSize = 14.sp, fontWeight = FontWeight.W900)
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                backgroundColor = Color.White,
                shape = MaterialTheme.shapes.large,
                elevation = 1.5.dp

            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(15.dp, 15.dp, 15.dp, 15.dp)
                ) {
                    userViewModel.uiState.value.exploreMemberListData.forEachIndexed { index, item ->
                        NearbyFriendItem(
                            nav02 = nav02,
                            userViewModel = userViewModel,
                            item = item,
                            navController = navController
                        )
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
fun NearbyFriendItem(
    nav02: () -> Unit = {},
    showRequestFriendDialog: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    item: ExploreMemberItem,
    navController: NavController,
) {
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
                //????????????
                Image(
                    painter = painterResource(id = item.userAvatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp),
                )
                //???????????????????????????
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 5.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    //????????????
                    Text(
                        text = item.userName,
                        style = TextStyle(
                            fontWeight = FontWeight.W700, //??????????????????
                            fontSize = 14.sp,
                        ),
                    )

                    //????????????
                    Text(
                        text = "??????${item.distance.toInt()}???",
                        style = TextStyle(
                            fontWeight = FontWeight.W400, //??????????????????
                            fontSize = 14.sp,
                            color = Color(0xffB8C2C0)
                        ),
                        modifier = Modifier.offset(y = 3.dp)
                    )
                }
                //????????????
                Column(
                    modifier = Modifier.width(55.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //????????????
                    if (!item.isFriend) {
                        //?????????????????????????????????????????????item
                        Image(
                            painter = painterResource(id = R.drawable.g2_2_btn_friend),
                            contentDescription = null,
                            modifier = Modifier
                                .width(40.dp)
                                .clickable(
                                    onClick = {
                                        userViewModel.uiState.value.showRequestFriendDialog.value =
                                            true
                                        userViewModel.uiState.value.visitItem.value = item
                                    },
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                ),
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.g4_2_ic_friendtag),
                            contentDescription = null,
                            modifier = Modifier
                                .width(40.dp)
                        )
                    }

                }
            }
            //????????????
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier.padding(end = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g4_3_ic_sendmessage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(65.dp)
                        .clickable(
                            onClick = {
                                userViewModel.uiState.value.visitItem.value = item

                                navController.navigate(IslandDeliver.route) {
                                    launchSingleTop = true; popUpTo(IslandNearbyMemberList.route) {}
                                }

                            },
                            indication = null,
                            interactionSource = MutableInteractionSource()
                        )
                )
            }
        }

    }

}