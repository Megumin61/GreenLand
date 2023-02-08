package com.example.jetpacktest02.screen

import androidx.compose.material.Button
import androidx.compose.material.Text
import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
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
import kotlinx.coroutines.launch


/**
 * 种子选择界面
 * 负责人：skc
 */



@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun ChooseSeed(nav01: () -> Unit={},userViewModel:UserViewModel)
{

    val titles = listOf("自定义", "随机")
    //state为顶部的tab导航栏绑定参数
    val state = userViewModel.uiState.value.chooseSeedPageState
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

    //配置顶部状态栏颜色
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
                            text = "选择种子",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-28.dp,0.dp)//向左偏移一段距离
                        )
                    }
                },
                //左侧按钮
                navigationIcon = {
                    IconButton(onClick = nav01, modifier = Modifier.offset(0.dp,10.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = ""
                        )

                    }
                },


                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .height(40.dp).offset(0.dp,20.dp)
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
                    //遍历
                    //0 "随机"
                    //1 "自定义"
                    titles.forEachIndexed { index, title ->
                        Tab(
                            modifier = Modifier
                                .background(Color.White)
                                .padding(0.dp),
                            selected = userViewModel.uiState.value.chooseSeedPageState.value == index,
                            onClick = {
                                // 点击事件
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
                //下面为要滑动切换的界面，可以通过判断page调用不同页面
//                Text(page.toString())
                if (page == 0) {
                    Page1()
//                    Text("12333")
                }
                if (page == 1) {
                    Page2()
//                    Text("page2")
                }
            }
        }
        Column(modifier=Modifier
            .padding(top = 600.dp,start=30.dp),
            horizontalAlignment= Alignment.CenterHorizontally
        ) {
            Button(onClick = {},
                shape = RoundedCornerShape(27.dp),border = BorderStroke(1.dp, GreenMain),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),

                modifier = Modifier
                    .size(width = 136.dp, height = 48.dp)
                    .offset(100.dp, 60.dp)

            ) {
                Text(text = "种下", color = Color.White, fontSize = 16.sp)
            }

        }



    }



}
@Composable
fun Page1(){
    var ifcolor by remember{
        mutableStateOf(1)}
    var ifshape by remember{
        mutableStateOf(1)}
    var ifmeaning by remember{
        mutableStateOf(1)}

    Column(
        modifier=Modifier
            .padding(top = 60.dp,start=30.dp),
//        horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
//            ,
//            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "01 颜色", color = Color.Black , fontSize = 14.sp,fontWeight = FontWeight.W600, modifier = Modifier.offset(0.dp,2.dp)
            )

            if(ifcolor==1){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(20.dp, -5.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "黄色", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifcolor=1 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(20.dp, -5.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "黄色", color = GreenMain, fontSize = 12.sp)
                }

            }

            if(ifcolor==2){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "蓝色", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifcolor=2 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "蓝色", color = GreenMain, fontSize = 12.sp)
                }

            }

            if(ifcolor==3){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(50.dp, -5.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "红色", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifcolor=3 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(50.dp, -5.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "红色", color = GreenMain, fontSize = 12.sp)
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
            Text(text = "02 植物形态", color = Color.Black , fontSize = 14.sp,fontWeight = FontWeight.W600, modifier = Modifier.offset(0.dp,2.dp)
            )

            if(ifshape==1){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(20.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "自然", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifshape=1 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(20.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "自然", color = GreenMain, fontSize = 12.sp)
                }

            }

            if(ifshape==2){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "清新", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifshape=2 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "清新", color = GreenMain, fontSize = 12.sp)
                }

            }

            if(ifshape==3){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(50.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "幽静", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifshape=3 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(50.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "幽静", color = GreenMain, fontSize = 12.sp)
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
            Text(text = "03 植物意义", color = Color.Black , fontSize = 14.sp,fontWeight = FontWeight.W600, modifier = Modifier.offset(0.dp,2.dp)
            )

            if(ifmeaning==1){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(20.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "魅力", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifmeaning=1 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(20.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "魅力", color = GreenMain, fontSize = 12.sp)
                }

            }

            if(ifmeaning==2){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "希望", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifmeaning=2 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(35.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "希望", color = GreenMain, fontSize = 12.sp)
                }

            }

            if(ifmeaning==3){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(50.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "友爱", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifmeaning=3 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(50.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "友爱", color = GreenMain, fontSize = 12.sp)
                }

            }
            if(ifmeaning==4){

                Button(onClick = {  },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GreenMain,
                        contentColor = GreenMain
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(65.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "和谐", color = Color.White, fontSize = 12.sp)
                }
            }else{
                Button(onClick = { ifmeaning=4 },
                    shape = RoundedCornerShape(15.dp),border = BorderStroke(1.dp, GreenMain),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 50.dp, height = 30.dp)
                        .offset(65.dp, -5.dp)
                        .padding(0.dp),contentPadding = PaddingValues(0.dp)

                ) {
                    Text(text = "和谐", color = GreenMain, fontSize = 12.sp)
                }

            }



        }

        Text(text = "生成本周的个性植物", color = Color.Black , fontSize = 16.sp,fontWeight = FontWeight.W600, modifier = Modifier
            .offset(-10.dp, 50.dp)
            .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_img_plant),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(width = 178.dp, height = 158.dp)
                .offset(80.dp, 190.dp)
        )



        if (ifmeaning==1){
            Image(

                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_flower),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 89.dp, height = 171.dp)
                    .offset(120.dp, -90.dp)
            )


        }
        if (ifmeaning==2){

            Image(

                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_meatmuch),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 112.dp, height = 171.dp)
                    .offset(110.dp, -50.dp)
            )


        }
        if (ifmeaning==3){

            Image(

                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_godpeople),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 110.dp, height = 171.dp)
                    .offset(110.dp, -68.dp)
            )

        }
        if (ifmeaning==4){

            Image(

                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_grass),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 108.dp, height = 171.dp)
                    .offset(110.dp, -64.dp)
            )

        }
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
//            Text(text = "种下", color = Color.White, fontSize = 16.sp)
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
//            Text(text = "种下", color = Color.White, fontSize = 16.sp)
//        }
//
//    }





}

@Composable
fun Page2(){
    var ifseed by remember{
        mutableStateOf(1)}

    Column(
        modifier=Modifier
            .padding(top = 120.dp,start=30.dp),
//        horizontalAlignment= Alignment.CenterHorizontally
//             verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
//            ,
//            horizontalArrangement = Arrangement.Center
        ) {



            Text(text = "选取神秘植物", color = Color.Black , fontSize = 16.sp,fontWeight = FontWeight.W600, modifier = Modifier
                .offset(-10.dp, 120.dp)
                .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        }


        Row(modifier = Modifier
            .fillMaxWidth()
            .offset(36.dp, -10.dp)) {




            if(ifseed==1){
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_seed_1),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(0.dp, -0.dp)
                )
            }else{
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_segmentedbtn_seed3),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(0.dp, -0.dp)
                        .clickable() { ifseed = 1 }
                )
            }

            if (ifseed==2){

                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_seed_2),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(20.dp, -0.dp)
                )
            }else{
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_segmentedbtn_seed2),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(20.dp, -0.dp)
                        .clickable() { ifseed = 2 }
                )
            }

            if (ifseed==3){

                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_seed_3),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(40.dp, -0.dp)
                )
            }else{
                Image(

                    painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_segmentedbtn_seed1),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 78.dp, height = 78.dp)
                        .offset(40.dp, -0.dp)
                        .clickable() { ifseed = 3 }
                )

            }

        }
        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_img_plant),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(width = 178.dp, height = 158.dp)
                .offset(80.dp, 130.dp)
        )
        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g3_2_1_img_plant),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(width = 178.dp, height = 158.dp)
                .offset(80.dp, 250.dp)
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
//            Text(text = "种下", color = Color.White, fontSize = 16.sp)
//        }
//
//    }





}

