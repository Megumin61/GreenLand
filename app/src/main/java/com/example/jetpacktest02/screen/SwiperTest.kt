package com.example.jetpacktest02.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

enum class Status{
    CLOSE, OPEN
}

//@ExperimentalMaterialApi
//@Preview
//@Composable
//fun SwipeableDemo() {
//    var blockSize = 48.dp
//    var blockSizePx = with(LocalDensity.current) { blockSize.toPx() }
//    var swipeableState = rememberSwipeableState(initialValue = Status.CLOSE)
//
//
//    LaunchedEffect(key1 = swipeableState){
//        if (swipeableState.currentValue == Status.CLOSE){
//            //navigator跳转
//        }
//    }
//
//    var anchors = mapOf(
//        0f to Status.CLOSE,
//        blockSizePx to Status.OPEN
//    )
//    Box(
//        modifier = Modifier
//            .size(height = blockSize, width = blockSize * 2)
//            .background(Color.LightGray)
//    ) {
//        Box(
//            modifier = Modifier
//                .offset {
//                    IntOffset(swipeableState.offset.value.toInt(), 0)
//                }
//                .swipeable(
//                    state = swipeableState,
//                    anchors = mapOf(
//                        0f to Status.CLOSE,
//                        blockSizePx to Status.OPEN
//                    ),
//                    thresholds = { from, to ->
//                        if (from == Status.CLOSE) {
//                            FractionalThreshold(0.3f)
//                        } else {
//                            FractionalThreshold(0.5f)
//                        }
//                    },
//                    orientation = Orientation.Horizontal
//                )
//                .size(blockSize)
//                .background(Color.DarkGray)
//        )
//    }
//}