package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import android.service.chooser.ChooserTargetService
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AimNum(aimnum: String, onNumChange: (String) -> Unit){
    Column(modifier = Modifier.padding(4.dp)) {

        OutlinedTextField(

            singleLine=false,
            shape = RoundedCornerShape(10.dp),
            value = aimnum,
            onValueChange = onNumChange,
            placeholder = { androidx.compose.material3.Text("设定目标", fontSize = 16.sp, color = Gray2)},
            modifier = Modifier
                .size(400.dp, 60.dp)
                .align(Alignment.CenterHorizontally),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = Gray3,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor  = Color.Transparent
            ))

    }
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetPlanSportsScreen(){
    var aimnum by rememberSaveable  {mutableStateOf("")}

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
                        painter = painterResource(id = R.drawable.g1_2_icbg_sports),
                        contentDescription = null, modifier = Modifier.fillMaxWidth()
                    )
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ){
                        Column(modifier = Modifier
                            .animateContentSize()
                            .padding(20.dp)) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "每日目标", fontSize = 18.sp,
                                    fontWeight = FontWeight.W900,
                                    color = Color.Black,
                                    textAlign = TextAlign.Justify)}

                            Spacer(modifier = Modifier.padding(5.dp))
                            androidx.compose.material.Divider(
                                color = Gray4,
                                modifier = Modifier.padding(horizontal = 10.dp),
                                thickness = 2.dp
                            )
                            Spacer(modifier = Modifier.padding(5.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "提醒时间", fontSize = 14.sp,
                                    fontWeight = FontWeight.W900,
                                    color = Gray5,
                                    textAlign = TextAlign.Justify)}
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                DayItem()}
                            Spacer(modifier = Modifier.padding(8.dp))

                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "目标次数", fontSize = 14.sp,
                                fontWeight = FontWeight.W900,
                                color = Gray5,
                                textAlign = TextAlign.Justify)}
                            Spacer(modifier = Modifier.padding(8.dp))
                            AimNum(aimnum = aimnum, onNumChange = {aimnum=it})
                            Spacer(modifier = Modifier.padding(8.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "轻提醒", fontSize = 14.sp,
                                    fontWeight = FontWeight.W900,
                                    color = Gray5,
                                    textAlign = TextAlign.Justify)}
                            Spacer(modifier = Modifier.padding(8.dp))
                            Column(Modifier.padding(horizontal = 4.dp)) {
                                Card(shape = RoundedCornerShape(10.dp)
                                    ,colors = CardDefaults.cardColors(containerColor = Gray3)
                                    , modifier = Modifier
                                        .height(60.dp)
                                        .fillMaxWidth()) {
                                    Row(Modifier.padding(horizontal = 10.dp)) {
                                        Text(text = "上午", fontSize = 18.sp,
                                            color = Color.Black, modifier = Modifier.offset(5.dp,10.dp))
                                        Text(text = "9 : 00", fontSize = 24.sp,
                                            fontWeight = FontWeight.W900,
                                            color = Color.Black, modifier = Modifier.offset(10.dp,6.dp))
                                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.offset(145.dp,0.dp)) {
                                            Icon(painter = painterResource(id = R.drawable.g1_2_4_ic_deleteclock), contentDescription =null )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(10.dp))
                                Card(shape = RoundedCornerShape(10.dp)
                                    ,colors = CardDefaults.cardColors(containerColor = Gray3)
                                    , modifier = Modifier
                                        .height(35.dp)
                                        .fillMaxWidth()) {
                                    Row(Modifier.padding(horizontal = 10.dp)) {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(painter = painterResource(id = R.drawable.g1_2_4_ic_deleteclock), contentDescription =null )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(16.dp))
                                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 80.dp)
                                    .width(136.dp)
                                    .height(54.dp)
                                    ,colors = ButtonDefaults.buttonColors(containerColor = Green5)
                                ) {
                                    Text(text = "确认", fontSize = 20.sp, fontWeight = FontWeight.W900)
                                }
                                Spacer(modifier = Modifier.padding(10.dp))
                            }
                        }




                    }

//                        Image(painter = painterResource(id = R.drawable.g1_2_1_bg_dailyaim), contentDescription =null, modifier = Modifier
//                            .padding(horizontal = 32.dp))
//                        AimNum(aimnum = aimnum, onNumChange = {aimnum=it})
//
//                        Row(Modifier.padding(vertical = 100.dp)) {
//                            WorkDaySlider()
//                        }
//
//                        Image(painter = painterResource(id = R.drawable.g1_2_3_btn_blankremind1), contentDescription =null,
//                            modifier = Modifier
//                                .padding(horizontal = 48.dp)
//                                .padding(top = 182.dp))
//                        Image(painter = painterResource(id = R.drawable.g1_2_4_btn_deleteclock), contentDescription =null,modifier = Modifier
//                            .padding(horizontal = 48.dp)
//                            .padding(top = 280.dp))
//                        Image(painter = painterResource(id = R.drawable.g1_2_3_btn_blankremind), contentDescription =null,modifier = Modifier
//                            .padding(horizontal = 48.dp)
//                            .padding(top = 390.dp))
//
//                        Button(onClick = { /*TODO*/ }, modifier = Modifier
//                            .padding(top = 460.dp, start = 122.dp)
//                            .width(136.dp)
//                            .height(54.dp)
//                            ,colors = ButtonDefaults.buttonColors(containerColor = Green5)
//
//
//                        ) {
//                            Text(text = "确认", fontSize = 20.sp, fontWeight = FontWeight.W900)
//
//
//                        }
                    }


                }
            }

        }
    }



@Composable
public fun WorkDaySlider(){

    Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {


        Column(modifier = Modifier
            .height(28.dp)
            .width(205.dp)
            .background(color = Color(0xff7FC7A8))
            .clip(RoundedCornerShape(10.dp))
            .offset(-5.dp, -5.dp)) {

        }
        Column {
            DayItem()

        }

    }

}
@Composable
public fun DayItem(){
    Row() {
        Text(
            text = "周一",
            fontSize = 12.sp,
            color = Color(0xff9598AC),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 17.dp)
        )
        Text(
            text = "周二",
            fontSize = 12.sp,
            color = Color(0xff9598AC),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 17.dp)
        )
        Text(
            text = "周三",
            fontSize = 12.sp,
            color = Color(0xff9598AC),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 17.dp)
        )
        Text(
            text = "周四",
            fontSize = 12.sp,
            color = Color(0xff9598AC),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 17.dp)
        )
        Text(
            text = "周五",
            fontSize = 12.sp,
            color = Color(0xff9598AC),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 17.dp)
        )
        Text(
            text = "周六",
            fontSize = 12.sp,
            color = Color(0xff9598AC),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 17.dp)
        )
        Text(
            text = "周日",
            fontSize = 12.sp,
            color = Color(0xff9598AC),
            style = MaterialTheme.typography.bodyMedium,

            )
    }}

