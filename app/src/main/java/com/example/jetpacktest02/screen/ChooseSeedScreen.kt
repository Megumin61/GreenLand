package com.example.jetpacktest02.screen

import androidx.compose.material.Button
import androidx.compose.material.Text
import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * ??????????????????
 * ????????????skc
 */


@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun ChooseSeed(
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    userViewModel: UserViewModel
) {

    val titles = listOf("?????????", "??????")
    //state????????????tab?????????????????????
    val state = userViewModel.uiState.value.chooseSeedPageState
    //pagerState?????????viewpager??????
    val pagerState: PagerState = remember { PagerState() }

    val flowerpic = userViewModel.uiState.value.flowerid

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

    //???????????????????????????
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(0.dp, 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = "????????????",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //??????????????????
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-28.dp, 0.dp)//????????????????????????
                        )
                    }
                },
                //????????????
                navigationIcon = {
                    IconButton(onClick = nav02, modifier = Modifier.offset(0.dp, 10.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = ""
                        )

                    }
                },


                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp, //????????????
            )
        }
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .offset(0.dp, 0.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                TabRow(
                    modifier = Modifier.width(270.dp),
                    backgroundColor = Color.White,
                    selectedTabIndex = userViewModel.uiState.value.chooseSeedPageState.value,
                    indicator = @Composable { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.customTabIndicatorOffset(tabPositions[userViewModel.uiState.value.chooseSeedPageState.value]),
                            color = LightGreen
                        )
                    },

                    ) {
                    //??????
                    //0 "??????"
                    //1 "?????????"
                    titles.forEachIndexed { index, title ->
                        Tab(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(0.dp),
                            selected = userViewModel.uiState.value.chooseSeedPageState.value == index,
                            onClick = {
                                // ????????????
                                userViewModel.uiState.value.chooseSeedPageState.value = index;
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
            }

            HorizontalPager(count = 2, state = pagerState) { page ->
//                Text(text = "Page: $page")
                //??????????????????????????????????????????????????????page??????????????????
//                Text(page.toString())
                if (page == 0) {
                    Box {
                        Page1(userViewModel)
                        //????????????
                        if (userViewModel.uiState.value.flowerid.value == 1) {
                            flower1(userViewModel)
                        }
                        if (userViewModel.uiState.value.flowerid.value == 2) {
                            flower2(userViewModel)
                        }
                        if (userViewModel.uiState.value.flowerid.value == 3) {
                            flower3(userViewModel)
                        }
                        if (userViewModel.uiState.value.flowerid.value == 4) {
                            flower4(userViewModel)
                        }

                    }


                    //Page1(userViewModel)
//                    Text("12333")
                }
                if (page == 1) {
                    Page2(userViewModel)
//                    Text("page2")
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 600.dp, start = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = nav02,

                interactionSource = MutableInteractionSource(),
                shape = RoundedCornerShape(27.dp), border = BorderStroke(1.dp, GreenMain),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain

                ),

                modifier = Modifier
                    .size(width = 136.dp, height = 48.dp)
                    .offset(0.dp, 0.dp)

            ) {
                Text(text = "??????", color = Color.White, fontSize = 16.sp)
            }

        }


    }


}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Page1(userViewModel: UserViewModel) {
    var ifcolor by remember {
        mutableStateOf(1)
    }
    var ifshape by remember {
        mutableStateOf(1)
    }
    var ifmeaning by remember {
        mutableStateOf(1)
    }
    val flower_id = userViewModel.uiState.value.flowerid.value


    //????????????
    var state by remember {
        mutableStateOf(false)
    }
    var state1 by remember {
        mutableStateOf(false)
    }
    var state2 by remember {
        mutableStateOf(false)
    }
    var state3 by remember {
        mutableStateOf(false)
    }




    if (ifshape == 1 && ifmeaning == 1) {
        userViewModel.uiState.value.flowerid.value = 1
    }
    if (ifshape == 1 && ifmeaning == 2) {
        userViewModel.uiState.value.flowerid.value = 2
    }
    if (ifshape == 1 && ifmeaning == 3) {
        userViewModel.uiState.value.flowerid.value = 3
    }
    if (ifshape == 1 && ifmeaning == 4) {
        userViewModel.uiState.value.flowerid.value = 4
    }
    if (ifshape == 2 && ifmeaning == 1) {
        userViewModel.uiState.value.flowerid.value = 2
    }
    if (ifshape == 2 && ifmeaning == 2) {
        userViewModel.uiState.value.flowerid.value = 3
    }
    if (ifshape == 2 && ifmeaning == 3) {
        userViewModel.uiState.value.flowerid.value = 4
    }
    if (ifshape == 2 && ifmeaning == 4) {
        userViewModel.uiState.value.flowerid.value = 1
    }
    if (ifshape == 3 && ifmeaning == 1) {
        userViewModel.uiState.value.flowerid.value = 3
    }
    if (ifshape == 3 && ifmeaning == 2) {
        userViewModel.uiState.value.flowerid.value = 4
    }
    if (ifshape == 3 && ifmeaning == 3) {
        userViewModel.uiState.value.flowerid.value = 1
    }
    if (ifshape == 3 && ifmeaning == 4) {
        userViewModel.uiState.value.flowerid.value = 2
    }

    Column(
        modifier = Modifier
            .padding(top = 0.dp, start = 30.dp)
            .offset(0.dp, -75.dp),
//        horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
//            ,
//            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "01 ??????",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.offset(0.dp, 2.dp)
            )

            if (ifcolor == 1) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(15.dp, -5.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifcolor = 1 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(15.dp, -5.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }

            if (ifcolor == 2) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(25.dp, -5.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifcolor = 2 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(25.dp, -5.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }

            if (ifcolor == 3) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifcolor = 3 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 10.dp)
//            ,
//            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "02 ????????????",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.offset(0.dp, 2.dp)
            )

            if (ifshape == 1) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(15.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifshape = 1 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(15.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }

            if (ifshape == 2) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(25.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifshape = 2 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(25.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }

            if (ifshape == 3) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifshape = 3 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 20.dp)
//            ,
//            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "03 ????????????",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.offset(0.dp, 2.dp)
            )

            if (ifmeaning == 1) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(15.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifmeaning = 1 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(15.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }

            if (ifmeaning == 2) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(25.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifmeaning = 2 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(25.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }

            if (ifmeaning == 3) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifmeaning = 3 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }
            if (ifmeaning == 4) {

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(45.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = Color.White, fontSize = 12.sp)
                }
            } else {
                Button(
                    onClick = { ifmeaning = 4 },
                    shape = RoundedCornerShape(15.dp), border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(45.dp, -5.dp)
                        .padding(0.dp), contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "??????", color = GreenMain, fontSize = 12.sp)
                }

            }


        }

        Text(
            text = "???????????????????????????",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .offset(-14.dp, 50.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_img_plant),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(width = 178.dp, height = 158.dp)
                .offset(62.dp, 190.dp)
        )


//        if (userViewModel.uiState.value.flowerid.value==1){
//
//            LaunchedEffect(key1 = state) {
//        state = true
//    }
//
//    Column(modifier= Modifier
//        .padding(top = 0.dp, start = 0.dp)
//        .offset(120.dp, -90.dp)
//    ) {
//
//        AnimatedVisibility(
//            visible = state,
//            enter = scaleIn(transformOrigin = TransformOrigin(1f, 2f)) +
//                    fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.BottomCenter),
//            exit = scaleOut(transformOrigin = TransformOrigin(1f, 2f)) +
//                    fadeOut() + shrinkOut(shrinkTowards = Alignment.BottomCenter)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.g3_flower),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(89.dp)
//                    .height(171.dp)
//            )
//
//
//    }
//}
//        }else{state=false}


//        if (userViewModel.uiState.value.flowerid.value==2){
//
//            Image(
//
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_meatmuch),
//                contentDescription = null,
//                alignment = Alignment.Center,
//                modifier = Modifier
//                    .size(width = 112.dp, height = 171.dp)
//                    .offset(110.dp, -50.dp)
//            )
//
//
//        }
//        if (userViewModel.uiState.value.flowerid.value==3){
//
//            Image(
//
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_godpeople),
//                contentDescription = null,
//                alignment = Alignment.Center,
//                modifier = Modifier
//                    .size(width = 110.dp, height = 171.dp)
//                    .offset(110.dp, -68.dp)
//            )
//
//        }
//        if (userViewModel.uiState.value.flowerid.value==4){
//
//            Image(
//
//                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_grass),
//                contentDescription = null,
//                alignment = Alignment.Center,
//                modifier = Modifier
//                    .size(width = 108.dp, height = 171.dp)
//                    .offset(110.dp, -64.dp)
//            )
//
//        }
//        Image(
//
//            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g4_2_img_flower_shadowed),
//            contentDescription = null,
//            alignment = Alignment.Center,
//            modifier = Modifier
//                .size(width = 89.dp, height = 171.dp)
//                .offset(120.dp, -75.dp)
//        )

//        Button(onClick = {},
//            shape = RoundedCornerShape(27.dp),border = BorderStroke(1.dp, GreenMain),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = GreenMain,
//                contentColor = GreenMain
//            ),
//
//            modifier = Modifier
//                .size(width = 136.dp, height = 48.dp)
//                .offset(100.dp, 60.dp)
//
//        ) {
//            Text(text = "??????", color = Color.White, fontSize = 16.sp)
//        }


    }
//    Column(modifier=Modifier
//        .padding(top = 600.dp,start=30.dp),
//        horizontalAlignment= Alignment.CenterHorizontally
//    ) {
//        Button(onClick = {},
//            shape = RoundedCornerShape(27.dp),border = BorderStroke(1.dp, GreenMain),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = GreenMain,
//                contentColor = GreenMain
//            ),
//
//            modifier = Modifier
//                .size(width = 136.dp, height = 48.dp)
//                .offset(100.dp, 60.dp)
//
//        ) {
//            Text(text = "??????", color = Color.White, fontSize = 16.sp)
//        }
//
//    }


}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Page2(userViewModel: UserViewModel) {
    var ifseed by remember {
        mutableStateOf(1)
    }
    val flower_id1 = userViewModel.uiState.value.flowerid.value
    Column(
        modifier = Modifier
            .padding(top = 120.dp, start = 30.dp),
//        horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
//            ,
//            horizontalArrangement = Arrangement.Center
        ) {


            Text(
                text = "??????????????????",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .offset(-14.dp, 100.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(18.dp, -45.dp)
        ) {


            if (ifseed == 1) {
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_seed_1),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(0.dp, -0.dp)
                )
            } else {
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_segmentedbtn_seed3),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(0.dp, -0.dp)
                        .clickable() { ifseed = 1;userViewModel.uiState.value.flowerid.value = 1 }
                )
            }

            if (ifseed == 2) {

                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_seed_2),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(20.dp, -0.dp)
                )
            } else {
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_segmentedbtn_seed2),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(20.dp, -0.dp)
                        .clickable() { ifseed = 2;userViewModel.uiState.value.flowerid.value = 2 }
                )
            }

            if (ifseed == 3) {

                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_seed_3),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(40.dp, -0.dp)
                )
            } else {
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_segmentedbtn_seed1),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(40.dp, -0.dp)
                        .clickable() { ifseed = 3;userViewModel.uiState.value.flowerid.value = 3 }
                )

            }

        }
        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_img_plant),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(width = 178.dp, height = 158.dp)
                .offset(62.dp, 130.dp)
        )
        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_img_plant),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(width = 178.dp, height = 158.dp)
                .offset(62.dp, 250.dp)
        )
    }
//    Column(modifier=Modifier
//        .padding(top = 600.dp,start=30.dp),
//        horizontalAlignment= Alignment.CenterHorizontally
//    ) {
//        Button(onClick = {},
//            shape = RoundedCornerShape(27.dp),border = BorderStroke(1.dp, GreenMain),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = GreenMain,
//                contentColor = GreenMain
//            ),
//
//            modifier = Modifier
//                .size(width = 136.dp, height = 48.dp)
//                .offset(100.dp, 60.dp)
//
//        ) {
//            Text(text = "??????", color = Color.White, fontSize = 16.sp)
//        }
//
//    }


}


@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun flower1(userViewModel: UserViewModel) {


    val flower_id = userViewModel.uiState.value.flowerid.value

    var state by remember {
        mutableStateOf(false)
    }



    if (userViewModel.uiState.value.flowerid.value == 1) {
        Box {
            LaunchedEffect(key1 = state) {
                state = true
            }

            Column(
                modifier = Modifier
                    .padding(top = 0.dp, start = 0.dp)
                    .offset(132.dp, 100.dp)
            ) {

                AnimatedVisibility(
                    visible = state,
                    enter = scaleIn(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.BottomCenter),
                    exit = scaleOut(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.BottomCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g3_flower),
                        contentDescription = null,
                        modifier = Modifier
                            .width(89.dp)
                            .height(171.dp)
                    )
                }

            }
        }
    } else {
        state = false
    }


}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun flower2(userViewModel: UserViewModel) {


    val flower_id = userViewModel.uiState.value.flowerid.value

    var state by remember {
        mutableStateOf(false)
    }



    if (userViewModel.uiState.value.flowerid.value == 2) {
        Box {
            LaunchedEffect(key1 = state) {
                state = true
            }

            Column(
                modifier = Modifier
                    .padding(top = 0.dp, start = 0.dp)
                    .offset(121.dp, 140.dp)
            ) {

                AnimatedVisibility(
                    visible = state,
                    enter = scaleIn(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.BottomCenter),
                    exit = scaleOut(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.BottomCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g3_meatmuch),
                        contentDescription = null,
                        modifier = Modifier
                            .width(117.dp)
                            .height(171.dp)
                    )
                }

            }
        }
    } else {
        state = false
    }


}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun flower3(userViewModel: UserViewModel) {


    val flower_id = userViewModel.uiState.value.flowerid.value

    var state by remember {
        mutableStateOf(false)
    }



    if (userViewModel.uiState.value.flowerid.value == 3) {
        Box {
            LaunchedEffect(key1 = state) {
                state = true
            }

            Column(
                modifier = Modifier
                    .padding(top = 0.dp, start = 0.dp)
                    .offset(121.dp, 122.dp)
            ) {

                AnimatedVisibility(
                    visible = state,
                    enter = scaleIn(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.BottomCenter),
                    exit = scaleOut(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.BottomCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g3_godpeople),
                        contentDescription = null,
                        modifier = Modifier
                            .width(110.dp)
                            .height(171.dp)
                    )
                }

            }
        }
    } else {
        state = false
    }


}

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun flower4(userViewModel: UserViewModel) {


    val flower_id = userViewModel.uiState.value.flowerid.value

    var state by remember {
        mutableStateOf(false)
    }



    if (userViewModel.uiState.value.flowerid.value == 4) {
        Box {
            LaunchedEffect(key1 = state) {
                state = true
            }

            Column(
                modifier = Modifier
                    .padding(top = 0.dp, start = 0.dp)
                    .offset(121.dp, 126.dp)
            ) {

                AnimatedVisibility(
                    visible = state,
                    enter = scaleIn(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.BottomCenter),
                    exit = scaleOut(transformOrigin = TransformOrigin(1f, 2f)) +
                            fadeOut() + shrinkOut(shrinkTowards = Alignment.BottomCenter)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g3_grass),
                        contentDescription = null,
                        modifier = Modifier
                            .width(108.dp)
                            .height(171.dp)
                    )
                }

            }
        }
    } else {
        state = false
    }


}