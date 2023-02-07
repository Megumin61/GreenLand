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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.colorResource
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
import androidx.navigation.NavHostController
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.HealthTabTest
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.config.UsersApplication
import com.example.jetpacktest02.ui.main.BarChart
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.*


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun TabViewPager() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pages by mutableStateOf(
            listOf("社会", "军事", "科技", "财经", "娱乐")
        )
        val pagerState = rememberPagerState(initialPage = 0)//初始化页面，0就表示第一个页面
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            // 使用提供的 pagerTabIndicatorOffset 修饰符自定义指示器
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            backgroundColor = Color.White,
            contentColor = colorResource(id = R.color.black)
        ) {
            //给全部页面添加标签栏
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,//是否选中
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    modifier = Modifier.alpha(0.9f),//透明度
                    enabled = true,//是否启用
                    selectedContentColor = Color.Black,//选中的颜色
                    unselectedContentColor = Color.LightGray,//未选中的颜色
                )
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState,//用于控制或观察viewpage状态的状态对象。
            modifier = Modifier.padding(top = 4.dp),
            itemSpacing = 2.dp
        ) { page ->
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Page: $page",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

