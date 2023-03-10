@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.service.chooser.ChooserTargetService
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
import kotlinx.coroutines.delay
/*import kotlin.collections.EmptyList.size*/
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun AimNum(aimnum: String, onNumChange: (String) -> Unit) {

    Box(Modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(4.dp)) {

            OutlinedTextField(

                singleLine = false,
                shape = RoundedCornerShape(10.dp),
                value = aimnum,
                onValueChange = onNumChange,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.W900
                ),
                placeholder = {
                    androidx.compose.material3.Text(
                        "????????????",
                        fontSize = 16.sp,
                        color = Gray2
                    )
                },
                modifier = Modifier
                    .size(400.dp, 60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    containerColor = Gray3,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        /*if (aimnum.toInt()<1000){
            Text(text = "????????????1000", color = Red, fontSize = 16.sp
                , modifier = Modifier.offset(180.dp,20.dp))
        }else{
        }*/
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetPlanSportsScreen(nav01: () -> Unit={}) {
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    val remindListData = remember {
        mutableStateListOf(
            remindItemModel("9:00", 5f),

            remindItemModel("9:00", 5f),
            remindItemModel("12:00", 5f),// ??????????????????????????? ????????????
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
    //???????????????




    Surface(modifier = Modifier.fillMaxSize()) {
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
            Scaffold(
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
                                modifier = Modifier.offset(-150.dp, 0.dp)//????????????????????????
                            )
                        }
                    },
                        backgroundColor = Green1,
                        contentColor = Color.Black,
                        elevation = 0.dp, //????????????
                        //????????????
                        navigationIcon = {


                            Icon(
                                bitmap = ImageBitmap.imageResource(id = R.drawable.g1_2_0_ic_arrow_left),
                                contentDescription = null, modifier = Modifier.offset(19.dp).clickable(onClick =nav01, indication = null, interactionSource = MutableInteractionSource() )
                            )

                        },
                        //????????????
                        actions = { }

                    )
                }
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
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
                        painter = painterResource(id = R.drawable.g1_2_icbg_sports),
                        contentDescription = null, modifier = Modifier.fillMaxWidth()
                    )
                    var state by remember {
                        mutableStateOf(false)
                    }
                    LaunchedEffect(key1 = state) {
                        delay(100)
                        state = true
                    }
                    AnimatedVisibility(
                        visible = state,
                        enter =slideInVertically(initialOffsetY = { -40 }
                        ) + fadeIn(initialAlpha = 0.3f),
                        exit= fadeOut(targetAlpha = 0f) + shrinkVertically(shrinkTowards = Alignment.Top)
                    ) {

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
                                        text = "????????????", fontSize = 18.sp,
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
                                        text = "????????????", fontSize = 14.sp,
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
                                        text = "????????????", fontSize = 14.sp,
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
                                        text = "?????????", fontSize = 14.sp,
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


                                    //????????????????????? ????????????remindListData
                                    remindListData.forEachIndexed { index, remindItemModel ->
                                        AnimatedVisibility(
                                            visible = if (index == remindListData.size - 1) false else true,

                                            enter =/*slideInVertically(initialOffsetY = { -40 }
                                    ) +*/ expandVertically(
                                                expandFrom = Alignment.Top
                                            ) + fadeIn(initialAlpha = 0.3f),
                                            exit = fadeOut(targetAlpha = 0f) + shrinkVertically(
                                                shrinkTowards = Alignment.Top
                                            )
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
                                                            text = "??????????????? ${remindItemModel.remindInterval.roundToInt()}??????",
                                                            fontSize = 12.sp,
                                                            color = Color.Black,
                                                        )
                                                        Slider(
                                                            value = remindItemModel.remindInterval,
                                                            onValueChange = {
                                                                remindListData[index] =
                                                                    remindItemModel(

                                                                        remindItemModel.remindTime,
                                                                        it
                                                                    )
                                                            },
                                                            colors = SliderDefaults.colors(
                                                                thumbColor = Green5,
                                                                activeTrackColor = Green5,
                                                                inactiveTrackColor = Green7
                                                            ),
                                                            valueRange = 1f..15f,
                                                            steps = 13,
                                                            modifier = Modifier.width(150.dp)
                                                        )
//                                                BasicTextField(
//                                                    value = remindItemModel.remindInterval,
//                                                    onValueChange = {
//                                                        remindItemModel.remindInterval = it
//                                                    },
//                                                    modifier = Modifier
//                                                        .background(Gray6, CircleShape),
//                                                    decorationBox = { innerTextField ->
//                                                        Row(
//                                                            verticalAlignment = Alignment.CenterVertically,
//                                                            modifier = Modifier.padding(horizontal = 10.dp)
//                                                        ) {
//                                                            Box(
//                                                                modifier = Modifier
//                                                                    .weight(1f)
//                                                                    .fillMaxSize(),
//                                                                contentAlignment = Alignment.TopStart
//                                                            ) {
//                                                                innerTextField()
//                                                            }
//                                                        }
//                                                    }
//                                                )
                                                    }
                                                }
                                            }
                                        }

                                        if (index == remindListData.size - 1) {
                                        } else {
                                            Spacer(modifier = Modifier.height(20.dp))
                                        }
                                    }

                                    //??????????????????


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
                                        onClick = nav01,
                                        interactionSource = MutableInteractionSource(),
                                        modifier = Modifier
                                            .width(136.dp)
                                            .height(54.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Green5)
                                    ) {
                                        Text(
                                            text = "??????",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W900
                                        )
                                    }
                                    Spacer(modifier = Modifier.padding(10.dp))
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))

                }


            }
        }

    }
}


@Composable
public fun WorkDaySlider() {

    Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {


        Column(
            modifier = Modifier
                .height(28.dp)
                .width(205.dp)
                .background(color = Color(0xff7FC7A8))
                .clip(RoundedCornerShape(10.dp))
                .offset(-5.dp, -5.dp)
        ) {

        }
        Column {
            DayItem()

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
public fun DayItem() {
    val daysList = remember {
        mutableStateListOf(
            weekdaysModel("??????", true),
            weekdaysModel("??????", true),
            weekdaysModel("??????", true),
            weekdaysModel("??????", true),
            weekdaysModel("??????", true),
            weekdaysModel("??????", false),
            weekdaysModel("??????", false),
        )
    }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

//      ??????...??????
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            daysList.forEachIndexed { index, item ->
                //????????????
                if (index < 4)
                    FilterChip(
                        border = null,

                        onClick = { daysList[index] = weekdaysModel(item.name, !item.selected) },
                        selected = item.selected,
                        label = {
                            Text(
                                text = item.name,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W900
                            )
                        },
                        /*leadingIcon = if (item.selected) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Localized Description",
                                    modifier = Modifier.size(10.dp),
                                    tint=Color.White
                                )
                            }
                        } else {
                            null
                        },*/
                        shape = RoundedCornerShape(20.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedLabelColor = Color.White,
                            selectedContainerColor = Green5,
                            labelColor = Color.Black,
                            containerColor = Gray3

                        )
                    )
            }
        }
        //??????...??????
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            daysList.forEachIndexed { index, item ->
                //????????????
                if (index >= 4)
                    FilterChip(
                        onClick = { daysList[index] = weekdaysModel(item.name, !item.selected) },
                        selected = item.selected,
                        label = {
                            Text(
                                text = item.name,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W900
                            )
                        },
                        /*leadingIcon = if (item.selected) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Localized Description",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                                    tint = Color.White
                                )
                            }
                        } else {
                            null
                        }*/
                        border = null,

                        shape = RoundedCornerShape(20.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedLabelColor = Color.White,
                            selectedContainerColor = Green5,
                            labelColor = Color.Black,
                            containerColor = Gray3
                        )
                    )
            }
        }
    }


}

data class remindItemModel(
    var remindTime: String,
    var remindInterval: Float,
)

data class weekdaysModel(
    var name: String,
    var selected: Boolean
)
