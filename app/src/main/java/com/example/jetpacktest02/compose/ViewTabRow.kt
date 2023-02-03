package com.example.jetpacktest02.compose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.screen.customTabIndicatorOffset
import com.example.scaffolddemo.ui.theme.Gray1
import com.example.scaffolddemo.ui.theme.LightGreen

@Preview
@Composable
fun ViewTabRow() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("好友", "附近", "可能认识")
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

