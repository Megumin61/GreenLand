package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


/*@Composable
fun DrawReactDemo() {
    Canvas(modifier = Modifier.size(200.dp), onDraw = {
        drawRoundRect(
            color = myRed,
            style = Stroke(width = 80f),
            cornerRadius = CornerRadius(80f, 80f)
        )
    })
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun DiyPlanName(PlanName: String, onNumChange: (String) -> Unit) {

    /*Box(Modifier.fillMaxWidth()) {*/

        Column(modifier = Modifier.padding(4.dp)) {
            OutlinedTextField(
                singleLine = false,
                shape = RoundedCornerShape(10.dp),
                value = PlanName,
                onValueChange = onNumChange,
                textStyle = TextStyle(
                    fontSize = 30.sp,
                    color = Green4,
                    fontWeight = FontWeight.W900
                ),
                placeholder = {
                    androidx.compose.material3.Text(
                        "?????????",
                        fontSize = 30.sp,
                        color = textGray,
                        fontWeight = FontWeight.W900
                    )
                },
                modifier = Modifier
                    .size(160.dp,80.dp)
                    .padding(start = 30.dp, top = 10.dp)
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Green4,
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
/*}*/

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")

@Composable
fun SetPlanDiyScreen(
    nav01: () -> Unit = {},nav02: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
){
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
            remindItemModel("12:00", 5f),

            //remindItemModel("12:00", 5f),// ??????????????????????????? ????????????
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
                                text = "????????????",
                                style = TextStyle(
                                    fontWeight = FontWeight.W900, //??????????????????
                                    fontSize = 18.sp,
                                ),
                                modifier = Modifier.offset(-135.dp, 0.dp)//????????????????????????
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
                                contentDescription = null, modifier = Modifier.offset(19.dp).clickable(onClick =nav02, indication = null, interactionSource = MutableInteractionSource() )
                            )
                        },
                        //????????????
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
                    Box(Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.g1_2_icbg_newdiy),
                            contentDescription = null, modifier = Modifier.fillMaxWidth()
                        )
                        var PlanName by rememberSaveable { mutableStateOf("")}

                        DiyPlanName(PlanName=userViewModel.uiState.value.diyPlanName.value,onNumChange={userViewModel.uiState.value.diyPlanName.value=it})

                    }
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
                                                        valueRange = 1f..15f,
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
                                    onClick =  nav01 ,interactionSource = MutableInteractionSource(),
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
                    }}
                    Spacer(Modifier.height(20.dp))


                }
            }

        }
    }
}

/*
@Composable
fun InputDiyPlanName(){

    DiyplanName
}
*/









