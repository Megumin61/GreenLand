package com.example.jetpacktest02.screen

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.PlanItem
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2


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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun NewDiyplanAddScreen(){
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
                                text = "自定义计划",
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

//                            IconButton(onClick = {}) {
//                                Icon(
//                                    Icons.Default.Menu,
//                                    contentDescription = "",
//                                )
//                            }
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
                    PlanItem(iconRes = R.drawable.g1_2_icbg_diy)
                    Box(){
                        Image(painter = painterResource(id = R.drawable.g1_2_5_bg_dailyaim), contentDescription =null, modifier = Modifier
                            .padding(horizontal = 32.dp))
                        Row(Modifier.padding(start = 60.dp, top = 108.dp)) {
                            DayItem()
                        }
                        Image(painter = painterResource(id = R.drawable.g1_2_3_btn_blankremind), contentDescription =null,
                            modifier = Modifier
                                .padding(horizontal = 48.dp)
                                .padding(top = 182.dp))

                        Button(onClick = { /*TODO*/ }, modifier = Modifier
                            .padding(top = 420.dp, start = 122.dp)
                            .width(136.dp)
                            .height(54.dp)
                           // .background(color = Color(0xff7FC7A8))


                        ) {
                            Text(text = "确认", fontSize = 20.sp, fontWeight = FontWeight.W900)


                        }
                    }


                }
            }

        }
    }
}


@Composable
fun DayItem(){
    Text(text = "周一", fontSize = 12.sp, color = Color(0xff9598AC),style = MaterialTheme.typography.bodyMedium)

}

