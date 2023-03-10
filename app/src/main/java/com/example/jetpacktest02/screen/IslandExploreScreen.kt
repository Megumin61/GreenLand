package com.example.jetpacktest02.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetpacktest02.IslandExplore
import com.example.jetpacktest02.IslandVisitMe
import com.example.jetpacktest02.IslandVisitOther
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.ExploreMemberItem
import com.example.jetpacktest02.ViewModel.FriendItem
import com.example.jetpacktest02.ViewModel.MarsViewModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.GIFimage
import com.example.jetpacktest02.utils.GPSUtils
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition",
    "CoroutineCreationDuringComposition"
)
@Composable
fun IslandExploreScreen(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    controller: NavHostController,
    navVisitOther: () -> Unit = {},
    marsViewModel: MarsViewModel
) {
    val scope = rememberCoroutineScope()
    var userId by remember {
        mutableStateOf(0)
    }
    //???????????????????????????id
    if (userViewModel.uiState.value.userList.size > 1) {
        userId = userViewModel.uiState.value.userList[userViewModel.uiState.value.userList.size - 1].id
        Log.i("code", "id: ${userId.toString()}")
    }


    LaunchedEffect(key1 = userViewModel._uiState.value.msgItem.value) {
        if (userViewModel._uiState.value.showTextMsg.value == true) {
            delay(3000)
            userViewModel._uiState.value.showTextMsg.value = false
        }
    }
    LaunchedEffect(key1 = userViewModel.uiState.value.mePos.value) {
        if (userViewModel.uiState.value.mePos.value.latitude != 0.0) {
            scope.launch {
                marsViewModel.updatePos(
                    userId,
                    userViewModel.uiState.value.mePos.value.toString()
                )
                Log.i("code", "lat: ${userViewModel.uiState.value.mePos.value.latitude.toString()}")

            }
        }
    }
    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    val context = LocalContext.current
    var currentLocation by remember {
        mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
    }
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    if (locationPermissionsState.allPermissionsGranted) {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (lo in p0.locations) {
                    // Update UI with location data
                    currentLocation = LocationDetails(lo.latitude, lo.longitude)
                }
            }
        }
        locationCallback.let {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
            }
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }

//        val pos = GPSUtils.getInstance().getProvince()
//        userViewModel._uiState.value.mePos.value = pos
//        scope.launch {
//            marsViewModel.updatePos(userId,pos.toString())
//        }

        //???????????????????????????????????????????????????????????????????????????
        LaunchedEffect(key1 = userViewModel._uiState.value.mePos.value) {
            if (userViewModel._uiState.value.mePos.value.latitude != 0.0) {
                userViewModel.uiState.value.exploreMemberListData.forEachIndexed { index, item ->
                    item.distance = GPSUtils.getInstance().getDistance(
                        userViewModel._uiState.value.mePos.value.longitude,
                        userViewModel._uiState.value.mePos.value.latitude,
                        item.location.longitude,
                        item.location.latitude
                    )
                }
            }

            userViewModel.uiState.value.exploreMemberListData.forEachIndexed { index, item ->
                item.animVisible = true
                delay(400)//??????1???
            }
//            userViewModel.uiState.value.exploreMemberListData.forEachIndexed { index, item ->
//                if (item.animVisible){
//                    return@LaunchedEffect
//                }
//                var a = GPSUtils.getInstance().getA(
//                    item.location.latitude,
//                    item.location.longitude,
//                    userViewModel.uiState.value.mePos.value.latitude,
//                    userViewModel.uiState.value.mePos.value.longitude,
//                )
//                var b = GPSUtils.getInstance().getB(
//                    userViewModel.uiState.value.mePos.value.latitude,
//                    userViewModel.uiState.value.mePos.value.longitude,
//                    item.location.latitude,
//                    item.location.longitude
//                )
//                if (a >= 0) {
//                    item.offsetX = GPSUtils.getInstance().mockFloatBetween2(0.5f, 1.6f)
//                } else {
//                    item.offsetX = GPSUtils.getInstance().mockFloatBetween2(-0.5f, -1.5f)
//                }
//                if (b >= 0) {
//                    item.offsetY = GPSUtils.getInstance().mockFloatBetween2(0.3f, 2.1f)
//                } else {
//                    item.offsetY = GPSUtils.getInstance().mockFloatBetween2(-0.3f, -1.5f)
//                }
//            }

        }
    }
//    else {
//
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
//
//            androidx.compose.material.Text(text = textToShow)
//            Spacer(modifier = Modifier.height(8.dp))
//            Button1(onClick = { locationPermissionsState.launchMultiplePermissionRequest() }) {
//                androidx.compose.material.Text(buttonText)
//            }
//        }
//    }

    //???????????????????????????
    rememberSystemUiController().setStatusBarColor(
        Flesh2, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        //???????????????
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "?????????",
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
                //??????????????????
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_ic_nearbylist),
                        contentDescription = null,
                        modifier = Modifier
                            .size(55.dp)
                            .offset(-15.dp, 0.dp)
                            .clickable(
                                onClick = nav02,
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }
                //????????????
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -65.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g4_2_bg_weather),
                        contentDescription = null,
                        modifier = Modifier
                            .width(270.dp)
                            .clickable(onClick = {
//                                marsViewModel.updatePos(
//                                    userId,
//                                    userViewModel.uiState.value.mePos.value.latitude.toString()
//                                )
                            }, interactionSource = MutableInteractionSource(), indication = null)
                    )
                }

                Box(modifier = Modifier.offset(y = -40.dp)) {
                    //????????????????????????
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = -70.dp)
                    ) {
                        AnimatedVisibility(
                            visible = userViewModel.uiState.value.showTextMsg.value,
                            enter = slideInVertically(initialOffsetY = { 100 }) + fadeIn(
                                initialAlpha = 0.3f
                            ),
                            exit = fadeOut(targetAlpha = 0f) + slideOutVertically()

                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .padding(horizontal = 60.dp) // ?????????
                                ,
                                backgroundColor = Color(0xfff5f5f7),
                                shape = MaterialTheme.shapes.medium,
                                elevation = 1.5.dp

                            ) {
                                //????????????
                                Column(
                                    //???????????????
                                    modifier = Modifier.padding(
                                        start = 0.dp,
                                        end = 0.dp,
                                        top = 10.dp,
                                        bottom = 30.dp
                                    )
                                ) {
                                    //???????????????
                                    Row(modifier = Modifier) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color(0xfff5f5f7)),
                                            contentAlignment = Alignment.TopStart
                                        ) {
                                            BasicTextField(readOnly = true,
                                                value = userViewModel.uiState.value.msgItem.value.textMsg,
                                                onValueChange = {
                                                },
                                                textStyle = TextStyle(
                                                    color = textGray,
                                                    fontWeight = FontWeight.W800
                                                ),
                                                modifier = Modifier
                                                    .background(Color(0xfff5f5f7), CircleShape)
                                                    .fillMaxWidth(),
                                                decorationBox = { innerTextField ->
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        modifier = Modifier.padding(horizontal = 10.dp)
                                                    ) {
//                                            IconButton(
//                                                onClick = { }
//                                            ) {
//                                                Icon(painterResource(id = R.drawable.g4_2_ic_friendlist), null)
//                                            }
                                                        Box(
                                                            modifier = Modifier
                                                                .weight(1f)
                                                                .fillMaxSize(),
                                                            contentAlignment = Alignment.TopStart
                                                        ) {
                                                            innerTextField()
                                                        }
//                                            IconButton(
//                                                onClick = { },
//                                            ) {
//                                                Icon(Icons.Filled.Send, null)
//                                            }
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }

                                //????????????
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 9.dp, horizontal = 16.dp),
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    //????????????????????????
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            buildAnnotatedString {
                                                append(userViewModel.uiState.value.msgItem.value.userName)
                                            },
                                            fontSize = 12.sp,
                                            color = Gray1
                                        )
                                        Text(
                                            buildAnnotatedString {
//                            withStyle(
//                                style = SpanStyle(
//                                    fontWeight = FontWeight.W600,
//                                    fontSize = 12.sp,
//                                    color = Color(0xFF4552B8)
//                                )
//                            ) {
//                                append("20")
//                            }
                                                append(userViewModel.uiState.value.msgItem.value.msgTime)
                                            },
                                            fontSize = 12.sp,
                                            color = Gray1
                                        )
                                    }
                                }


                            }
                        }


                    }

                    // ????????????????????????
                    ExploreMapBgAnimation(
                        nav03,
                        nav04,
                        navVisitOther = navVisitOther,
                        userViewModel = userViewModel,
                        controller
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 400.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        //????????????
                        if (locationPermissionsState.allPermissionsGranted) {

                            Image(
                                painter = painterResource(id = R.drawable.g4_2_btn_publish),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(60.dp)
                                    .clickable(
                                        onClick = nav03,
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    )
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.g4_2_btn_requestgps),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(60.dp)
                                    .clickable(
                                        onClick = { { locationPermissionsState.launchMultiplePermissionRequest() } },
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    )
                            )
                        }
                    }

                }


            }

            //??????????????????
            if (userViewModel.uiState.value.showImgMsgDialog.value) {
                Dialog(
                    onDismissRequest = {
                        userViewModel._uiState.value.showImgMsgDialog.value = false
                    },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //????????????????????????????????????????????????
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            //?????????????????????????????????
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = userViewModel.uiState.value.msgItem.value.userAvatar),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = userViewModel.uiState.value.msgItem.value.userName,
                                    style = TextStyle(
                                        fontWeight = FontWeight.W600, //??????????????????
                                        fontSize = 18.sp,
                                        color = Color.White
                                    ),
                                )
                                Spacer(modifier = Modifier.width(5.dp))
//                                Image(
//                                    painter = painterResource(id = R.drawable.g4_2_ic_friendtag),
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .width(40.dp)
//                                        .clickable { }
//                                )
                            }

                            //????????????
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = userViewModel.uiState.value.msgItem.value.msgTime,
                                    style = TextStyle(
                                        fontWeight = FontWeight.W400, //??????????????????
                                        fontSize = 15.sp,
                                        color = Color.White
                                    ),
                                )
                            }
                        }
                        //????????????
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = userViewModel.uiState.value.msgItem.value.imgMsg),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(300.dp).clip(RoundedCornerShape(15.dp))
                            )
                        }
                        //????????????
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp), horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(20.dp)
                                    .clickable(
                                        onClick = {
                                            userViewModel.uiState.value.showImgMsgDialog.value =
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

}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ExplorePlantModelItem(
    name: String,
    plantType: Int,//plantType
    res: Int,//userAvatar
    offsetX: Float = 0f,
    offsetY: Float = 0f,
    textMsg: String,
    imgMsg: Int,
    nav2: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    item: ExploreMemberItem,
    controller: NavHostController,
) {

    //??????????????????
    var plantSize = 0.dp
    if (item.distance > 100) {
        plantSize = 50.dp
    } else if (item.distance > 50) {
        plantSize = 60.dp
    } else if (item.distance > 20) {
        plantSize = 70.dp
    } else {
        plantSize = 80.dp
    }


    Column(
        modifier = Modifier.offset(offsetX * 100.dp, offsetY * 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = item.animVisible,
            enter = slideInVertically(
                initialOffsetY = { 30 },
                animationSpec = tween(
                    durationMillis = item.animDuration
                )
            ) + fadeIn(initialAlpha = 0.3f),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier, contentAlignment = Alignment.TopEnd) {
                    //????????????????????????
                    if (textMsg != "" || imgMsg != 0) {
                        Row(modifier = Modifier.padding(6.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.g4_2_ic_msgdot),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(7.dp)
                                    .clickable(
                                        onClick = {
                                        },
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    )
                            )
                        }
                    } else {
                    }

                    GIFimage(
                        gif = plantType, modifier = Modifier
                            .size(plantSize)
                            .clickable(
                                onClick =
                                {
                                    MsgHandleClick(
                                        userViewModel,
                                        textMsg,
                                        imgMsg,
                                        item,
                                        controller
                                    )
                                },
                                indication = null,
                                interactionSource = MutableInteractionSource()
                            )
                    )
                }
                //????????? +??????
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                        ) {
                            append(name)
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.W700,
                                fontSize = 9.sp,
                                color = Gray1
                            )
                        ) {
                            append(" (${item.distance}???)")
                        }

                    }
                )
            }

        }
    }

}

fun MsgHandleClick(
    userViewModel: UserViewModel,
    textMsg: String,
    imgMsg: Int,
    item: ExploreMemberItem,
    navController: NavHostController
) {
    if (textMsg != "") {
        userViewModel.uiState.value.msgItem.value =
            FriendItem(msgTime = "??????")
        userViewModel.uiState.value.msgItem.value.userName =
            item.userName
        userViewModel.uiState.value.msgItem.value.msgTime =
            item.msgTime
        userViewModel.uiState.value.msgItem.value.userAvatar =
            item.userAvatar
        userViewModel.uiState.value.msgItem.value.textMsg =
            item.textMsg
        userViewModel.uiState.value.msgItem.value.imgMsg =
            item.imgMsg

        userViewModel.uiState.value.showImgMsgDialog.value =
            false
        userViewModel.uiState.value.showTextMsg.value = true
        //?????????????????????,??????????????????
        item.imgMsg = 0
        item.textMsg = ""
    } else if (imgMsg != 0) {
        userViewModel.uiState.value.msgItem.value =
            FriendItem(msgTime = "??????")
        userViewModel.uiState.value.msgItem.value.userName =
            item.userName
        userViewModel.uiState.value.msgItem.value.msgTime =
            item.msgTime
        userViewModel.uiState.value.msgItem.value.userAvatar =
            item.userAvatar
        userViewModel.uiState.value.msgItem.value.textMsg =
            item.textMsg
        userViewModel.uiState.value.msgItem.value.imgMsg =
            item.imgMsg
        userViewModel.uiState.value.showTextMsg.value = false
        userViewModel.uiState.value.showImgMsgDialog.value =
            true
        //?????????????????????,??????????????????
        item.imgMsg = 0
        item.textMsg = ""
    } else {
        navController.navigate("4.5-island-visitOther/${item.userAvatar}/${item.userName}")
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ExploreMapBgAnimation(
    nav: () -> Unit = {},
    nav2: () -> Unit = {},
    navVisitOther: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    controller: NavHostController,
) {
    //????????????
    var isPlaying by remember {
        mutableStateOf(true)
    }
    //??????????????????
    var speed by remember {
        mutableStateOf(1f)
    }
    LaunchedEffect(1) {
        delay(3800)
        isPlaying = false
    }
    //???????????????????????? ????????????????????????????????????
    var interations by remember {
        mutableStateOf(3)
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animations/map.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = interations,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = true  // ???????????????????????????????????????
    )

    Box(modifier = Modifier.offset(y = -80.dp), contentAlignment = Alignment.Center) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(450.dp)
                .alpha(if (isPlaying == false) 0f else 1f)
        )
        //???????????????????????????
        plantModelItem(
            userViewModel.uiState.value.meItem.value.userName,
            userViewModel.uiState.value.meItem.value.userPlant,
            userViewModel.uiState.value.meItem.value.userAvatar,
            userViewModel.uiState.value.meItem.value.offsetX,
            userViewModel.uiState.value.meItem.value.offsetY,
            userViewModel.uiState.value.meItem.value.textMsg,
            userViewModel.uiState.value.meItem.value.imgMsg,
            nav2,
            userViewModel = userViewModel,
            userViewModel.uiState.value.meItem.value,
            controller,
            gifSize = 90.dp
        )

        userViewModel.uiState.value.exploreMemberListData.forEachIndexed { index, item ->
            ExplorePlantModelItem(
                item.userName,
                item.userPlant,
                item.userAvatar,
                item.offsetX,
                item.offsetY,
                item.textMsg,
                item.imgMsg,
                navVisitOther,
                userViewModel = userViewModel,
                item,
                controller
            )
        }
    }
}
