package com.example.jetpacktest02.screen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
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
import androidx.compose.runtime.*
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.jetpacktest02.Plant
import com.example.jetpacktest02.R
import com.example.jetpacktest02.RallyActivity
import com.example.jetpacktest02.UnityPlayerActivity
import com.example.jetpacktest02.ViewModel.UserViewModel

import com.example.scaffolddemo.ui.theme.GreenLightReminder
import com.example.scaffolddemo.ui.theme.textGray2
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collect
import okhttp3.internal.format
import java.util.*

//enum class Status{ CLOSE, OPEN }
//@OptIn(ExperimentalMaterialApi::class)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableDemo() {
    val blockSize = 48.dp
    var blockSizePx = with(LocalDensity.current) { blockSize.toPx() }
    // ?????????????????????SwipeableState??????
    val swipeableState = rememberSwipeableState(initialValue = Status.CLOSE)
    // ????????????????????????Pair???????????????????????????????????????
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
                        // ????????????????????????????????????????????????30%?????????????????????????????????
                        if (from == Status.CLOSE) {
                            FractionalThreshold(0.3f)
                        } else { // ??????????????????????????????????????????????????????50%?????????????????????????????????
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


@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun LightReminderScreen(
    userViewModel: UserViewModel,
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
                viewModel.showNotification(context,"????????????", "??????????????????????????????")
            }) {
                Text(text = "?????????????????????")
            }
            Button(onClick = {
                viewModel.updateNotification(context,"????????????", "???????????????????????????????????????????????????")
            }) {
                Text(text = "????????????")
            }
        }*/
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = textGray2,
                text = "2???24??? ?????????",
                style = TextStyle(
                    fontWeight = W900, //??????????????????
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
                // ?????????????????????SwipeableState??????
                val swipeableState = rememberSwipeableState(initialValue = Status.CLOSE)
                // ????????????????????????Pair???????????????????????????????????????
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
                                // ????????????????????????????????????????????????30%?????????????????????????????????
                                if (from == Status.CLOSE) {
                                    FractionalThreshold(0.8f)
                                } else { // ??????????????????????????????????????????????????????50%?????????????????????????????????
                                    FractionalThreshold(0.8f)

                                }
                            },
                            orientation = Orientation.Horizontal
                        )
                )
                val blockSize2 = 10.dp
                var blockSizePx2 = with(LocalDensity.current) { blockSize2.toPx() }
                // ?????????????????????SwipeableState??????
                var swipeableState2 = rememberSwipeableState(initialValue = Status.CLOSE)
                // ????????????????????????Pair???????????????????????????????????????
                val anchors2 = mapOf(
                    0f to Status.CLOSE,
                    blockSizePx2*2 to Status.OPEN
                )
                val context = LocalContext.current
                val intent = Intent(context, RallyActivity::class.java)
//                var state2 by remember {
//                    mutableStateOf(false)
//                }
//                Button(onClick = {
//                    state2=true
//                }) {
//                    androidx.compose.material.Text(text = "??????unity")
//                }
//                if(state2==true)
//                {
//                    context.startActivity(intent)
//                }
                LaunchedEffect(swipeableState2){
                    snapshotFlow {swipeableState2.currentValue  }.collect{ value ->
                        if (value == Status .OPEN){
                            userViewModel.uiState.value.isLockScreen.value=1
//                            navController.navigate(Plant.route){launchSingleTop=true}
                            context.startActivity(intent)
                        }
                    }}

                Image(
                    painter = painterResource(id = R.drawable.g10_ic_drinknow),
                    contentDescription = null,modifier = Modifier
                        .offset { IntOffset(swipeableState2.offset.value.toInt(), 0) }
                        .swipeable(
                            state = swipeableState2,
                            anchors = anchors2,
                            thresholds = { from, to ->
                                // ????????????????????????????????????????????????30%?????????????????????????????????
                                if (from == Status.CLOSE) {
                                    FractionalThreshold(0.3f)
                                } else { // ??????????????????????????????????????????????????????50%?????????????????????????????????
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





