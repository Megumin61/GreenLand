package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.service.chooser.ChooserTargetService
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.PlanItem
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.roundToInt


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun SetPlanDrinkScreen(){
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    val remindListData = remember {
        mutableStateListOf(
            remindItemModel("10:45", 30f),

            remindItemModel("12:00", 5f),// 数组的最后一个元素 不会显示
            /*remindItemModel("12:30", 5f),
            remindItemModel("18:20", 5f),*/
        )
    }

    val showstate = remember {
        mutableStateOf(false)
    }

    var changedIndex by remember {
        mutableStateOf(0)
    }
    var changedRemindTime by remember {
        mutableStateOf("")
    }
    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
            changedRemindTime = mTime.value
            remindListData[changedIndex] = remindItemModel(changedRemindTime, 5f)
        },
        mHour, mMinute, false,
    )

    var aimnum by rememberSaveable { mutableStateOf("") }


    Surface(modifier = Modifier.fillMaxSize()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Green1,
                            Green2
                        )
                    )
                )
        ){
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "修改计划",
                                style = TextStyle(
                                    fontWeight = FontWeight.W900, //设置字体粗细
                                    fontSize = 18.sp,
                                ),
                                modifier = Modifier.offset(-150.dp, 0.dp)//向左偏移一段距离
                            )
                        }
                    },
                        backgroundColor = Green1,
                        contentColor = Color.Black,
                        elevation = 0.dp, //设置阴影
                        //左侧按钮
                        navigationIcon = {

                            IconButton(onClick = {}) {
                                Icon(
                                    bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                    contentDescription = null
                                ) }
                        },
                        //右侧按钮
                        actions = {
//                            IconButton(onClick = {}) {
//                                Icon(
//                                    Icons.Default.Settings,
//                                    contentDescription = "",
//                                )
//                            }
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
                                    Green1,
                                    Green2
                                )
                            )
                        )
                ) {

                    Spacer(Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.g1_2_icbg_drinkwater),
                        contentDescription = null, modifier = Modifier.fillMaxWidth()
                    )

                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "每日目标", fontSize = 18.sp,
                                    fontWeight = FontWeight.W900,
                                    color = Color.Black,
                                    textAlign = TextAlign.Justify
                                )
                            }

                            Spacer(modifier = Modifier.padding(5.dp))
                            androidx.compose.material.Divider(
                                color = Gray4,
                                modifier = Modifier.padding(horizontal = 10.dp),
                                thickness = 2.dp
                            )
                            Spacer(modifier = Modifier.padding(5.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "提醒时间", fontSize = 14.sp,
                                    fontWeight = FontWeight.W900,
                                    color = Gray5,
                                    textAlign = TextAlign.Justify
                                )
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                DayItem()
                            }
                            Spacer(modifier = Modifier.padding(8.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "目标次数", fontSize = 14.sp,
                                    fontWeight = FontWeight.W900,
                                    color = Gray5,
                                    textAlign = TextAlign.Justify
                                )
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            AimNum(aimnum = aimnum, onNumChange = { aimnum = it })
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "轻提醒", fontSize = 14.sp,
                                    fontWeight = FontWeight.W900,
                                    color = Gray5,
                                    textAlign = TextAlign.Justify
                                )
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Column(
                                Modifier.padding(horizontal = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {


                                //轻提醒项目卡片 遍历数组remindListData
                                remindListData.forEachIndexed { index, remindItemModel ->
                                    AnimatedVisibility(
                                        visible = if (index == remindListData.size - 1) false else true,

                                        enter =/*slideInVertically(initialOffsetY = { -40 }
                                    ) +*/ expandVertically(
                                            expandFrom = Alignment.Top
                                        ) + fadeIn(initialAlpha = 0.3f),
                                        exit= fadeOut(targetAlpha = 0f) + shrinkVertically(shrinkTowards = Alignment.Top)
                                    ) {
                                        Card(
                                            shape = RoundedCornerShape(10.dp),
                                            colors = CardDefaults.cardColors(containerColor = Gray3),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                Modifier
                                                    .padding(
                                                        start = 13.dp,
                                                        end = 20.dp,
                                                        top = 10.dp
                                                    )
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.clickable(
                                                        onClick = {
                                                            mTimePickerDialog.show()
                                                            changedIndex = index
                                                        },
                                                        indication = null,
                                                        interactionSource = MutableInteractionSource()
                                                    )
                                                ) {
                                                    Spacer(modifier = Modifier.width(6.dp))
                                                    Text(
                                                        text = remindItemModel.remindTime,
                                                        fontSize = 24.sp,
                                                        fontWeight = FontWeight.W700,
                                                        color = Color.Black,
                                                    )
                                                }
                                                Image(
                                                    painter = painterResource(id = R.drawable.g1_2_4_ic_deleteclock),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(15.dp)
                                                        .clickable(
                                                            onClick = {
                                                                /* Anistate= !Anistate*/
                                                                remindListData.removeAt(index)
                                                            },
                                                            indication = null,
                                                            interactionSource = MutableInteractionSource()
                                                        )
                                                )
                                            }
                                            Row(
                                                Modifier
                                                    .padding(start = 20.dp, top = 0.dp)
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Text(
                                                        text = "再提醒间隔 ${remindItemModel.remindInterval.roundToInt()}分钟",
                                                        fontSize = 12.sp,
                                                        color = Color.Black,
                                                    )
                                                    Slider(
                                                        value = remindItemModel.remindInterval,
                                                        onValueChange = {
                                                            remindListData[index] = remindItemModel(

                                                                remindItemModel.remindTime,
                                                                it
                                                            )
                                                        },
                                                        colors = SliderDefaults.colors(
                                                            thumbColor = Green5,
                                                            activeTrackColor = Green5,
                                                            inactiveTrackColor = Green7
                                                        ),
                                                        valueRange = 1f..45f,
                                                        steps = 13,
                                                        modifier = Modifier.width(150.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    if (index == remindListData.size - 1) {
                                    } else {
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                                }

                                //增加提醒按钮


                                Card(
                                    shape = RoundedCornerShape(10.dp),
                                    colors = CardDefaults.cardColors(containerColor = Gray3),
                                    modifier = Modifier
                                        .height(35.dp)
                                        .fillMaxWidth()
                                        .clickable(
                                            onClick = {
//                                                    showstate.value = !showstate.value
                                                remindListData.add(
                                                    remindItemModel(
                                                        "12:00",
                                                        5f
                                                    )
                                                )
                                            },
                                            indication = null,
                                            interactionSource = MutableInteractionSource()
                                        )

                                ) {
                                    Row(
                                        Modifier
                                            .padding(horizontal = 20.dp)
                                            .fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.g1_2_4_ic_addclock),
                                            contentDescription = null

                                        )
                                    }
                                }








                                Spacer(modifier = Modifier.padding(16.dp))
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .width(136.dp)
                                        .height(54.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Green5)
                                ) {
                                    Text(
                                        text = "确认",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.W900
                                    )
                                }
                                Spacer(modifier = Modifier.padding(10.dp))
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))

                }


            }
        }

    }

}

