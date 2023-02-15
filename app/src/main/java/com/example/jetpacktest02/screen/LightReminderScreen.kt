package com.example.jetpacktest02.screen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import androidx.annotation.Px
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.jetpacktest02.Plant
import com.example.jetpacktest02.R

import com.example.scaffolddemo.ui.theme.GreenLightReminder
import com.example.scaffolddemo.ui.theme.textGray2
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collect
import okhttp3.internal.format
import java.util.*

enum class Status{ CLOSE, OPEN }
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableDemo() {
    val blockSize = 48.dp
    var blockSizePx = with(LocalDensity.current) { blockSize.toPx() }
    // 创建并获取一个SwipeableState实例
    val swipeableState = rememberSwipeableState(initialValue = Status.CLOSE)
    // 定义锚点，锚点以Pair表示，每个状态对应一个锚点
    val anchors = mapOf(
        0f to Status.CLOSE,
        blockSizePx*2 to Status.OPEN
    )
    Box(
        Modifier
            .size(height = blockSize, width = blockSize * 3)
            .clip(RoundedCornerShape(50))
            .background(Color.Gray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.toInt(), 0) }
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { from, to ->
                        // 从关闭到开启状态时，滑块移动超过30%距离自动吸附到开启状态
                        if (from == Status.CLOSE) {
                            FractionalThreshold(0.3f)
                        } else { // 从开启状态到关闭状态时，滑块移动超过50%才会自动吸附到关闭状态
                            FractionalThreshold(0.5f)
                        }
                    },
                    orientation = Orientation.Horizontal
                )
                .size(blockSize)
                .clip(RoundedCornerShape(50))
                .background(Color.Red)
        )
    }
}


@Composable
fun GIFimage(
    modifier: Modifier = Modifier,
    gif:Int
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data =gif ).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),

        contentDescription = null,
        modifier = modifier.fillMaxWidth()
    )
}


@OptIn(ExperimentalMaterialApi::class)

@Composable
fun LightReminderScreen(
    navController: NavController,

) {

    rememberSystemUiController().setStatusBarColor(
        GreenLightReminder, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    Box(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(id = R.drawable.g10_bg_lightreminder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )
        /*val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.showNotification(context,"外卖提醒", "您好，您的外卖到了！")
            }) {
                Text(text = "创建一个新通知")
            }
            Button(onClick = {
                viewModel.updateNotification(context,"订单提醒", "您有一条新的外卖订单，请及时接单！")
            }) {
                Text(text = "更新通知")
            }
        }*/
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = textGray2,
                text = "2月24日 星期五",
                style = TextStyle(
                    fontWeight = W900, //设置字体粗细
                    fontSize = 20.sp,
                )
            )
            Spacer(modifier = Modifier.padding(3.dp))
            val sdf=SimpleDateFormat("HH:mm").format(Date())
            val strTime=sdf
            Text(
                color = textGray2,
                text = strTime,
                style = TextStyle(
                    fontWeight = W400,
                    fontSize = 96.sp,
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 300.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GIFimage(modifier = Modifier.size(460.dp), gif = R.drawable.gif_04)

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 500.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val blockSize = 1000.dp
                var blockSizePx = with(LocalDensity.current) { blockSize.toPx() }
                // 创建并获取一个SwipeableState实例
                val swipeableState = rememberSwipeableState(initialValue = Status.CLOSE)
                // 定义锚点，锚点以Pair表示，每个状态对应一个锚点
                val anchors = mapOf(
                    0f to Status.CLOSE,
                    blockSizePx*2 to Status.OPEN
                )

                Image(
                    painter = painterResource(id = R.drawable.g10_ic_wait),
                    contentDescription = null, modifier = Modifier
                        .offset { IntOffset(swipeableState.offset.value.toInt(), 0) }
                        .swipeable(
                            state = swipeableState,
                            anchors = anchors,
                            thresholds = { from, to ->
                                // 从关闭到开启状态时，滑块移动超过30%距离自动吸附到开启状态
                                if (from == Status.CLOSE) {
                                    FractionalThreshold(0.8f)
                                } else { // 从开启状态到关闭状态时，滑块移动超过50%才会自动吸附到关闭状态
                                    FractionalThreshold(0.8f)

                                }
                            },
                            orientation = Orientation.Horizontal
                        )
                )
                val blockSize2 = 10.dp
                var blockSizePx2 = with(LocalDensity.current) { blockSize2.toPx() }
                // 创建并获取一个SwipeableState实例
                var swipeableState2 = rememberSwipeableState(initialValue = Status.CLOSE)
                // 定义锚点，锚点以Pair表示，每个状态对应一个锚点
                val anchors2 = mapOf(
                    0f to Status.CLOSE,
                    blockSizePx2*2 to Status.OPEN
                )
                LaunchedEffect(swipeableState2){
                    snapshotFlow {swipeableState2.currentValue  }.collect{ value ->
                        if (value == Status .OPEN){navController.navigate(Plant.route){launchSingleTop=true}}
                    }}

                Image(
                    painter = painterResource(id = R.drawable.g10_ic_drinknow),
                    contentDescription = null,modifier = Modifier
                        .offset { IntOffset(swipeableState2.offset.value.toInt(), 0) }
                        .swipeable(
                            state = swipeableState2,
                            anchors = anchors2,
                            thresholds = { from, to ->
                                // 从关闭到开启状态时，滑块移动超过30%距离自动吸附到开启状态
                                if (from == Status.CLOSE) {
                                    FractionalThreshold(0.3f)
                                } else { // 从开启状态到关闭状态时，滑块移动超过50%才会自动吸附到关闭状态
                                    FractionalThreshold(0.8f)
                                }

                            },
                            orientation = Orientation.Horizontal
                        )



                )

            }

        }

    }
}





