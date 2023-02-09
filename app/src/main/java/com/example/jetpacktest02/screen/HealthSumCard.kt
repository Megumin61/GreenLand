package com.example.jetpacktest02.screen

import android.os.health.HealthStats
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.ProgressBar
import com.example.scaffolddemo.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable

fun HealthSumCard(){
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
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
            androidx.compose.material3.Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(136.dp)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green5)
            ) {
                Text(
                    text = "本周报告",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W900
                )
            }
            Column( horizontalAlignment = Alignment.CenterHorizontally) {
                androidx.compose.material3.Card(
//                    onClick = { /* Do something */ },
                    modifier = Modifier.size(width = 380.dp, height = 530.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Image(painter = painterResource(id = R.drawable.g0_1_ic_report_cup), contentDescription =null
                            , modifier = Modifier
                                .size(300.dp)
                                .offset(0.dp, -15.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .offset(0.dp, -50.dp), horizontalArrangement =Arrangement.Center) {
                            Text(
                                text = "你完成了",
                                fontSize = 15.sp,
                                color = Gray7
                            )
                            Text(
                                text = "97%",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W900,
                                color = Gray7, modifier = Modifier
                                    .offset(0.dp, -8.dp)
                                    .padding(horizontal = 3.dp)

                            )
                            Text(
                                text = "的本周计划！",
                                fontSize = 15.sp,
                                color = Gray7
                            )
                            
                        }
                        Text(
                            text = "植物已经成熟，查看本周健康总结",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W900,
                            color = Gray7, modifier = Modifier
                                .offset(0.dp, -45.dp)
                                .padding(horizontal = 3.dp)

                        )
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .offset(0.dp, -30.dp)
                            , horizontalArrangement =Arrangement.SpaceBetween) {
                            Image(painter = painterResource(id = R.drawable.g0_1_ic_report_flower), contentDescription =null )
                            var progress by remember { mutableStateOf(0.1f) }
                            val animatedProgress by animateFloatAsState(
                                targetValue = progress,
                                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                            )

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                ProgressBar(
                                    modifier = Modifier
                                        .width(165.dp) // 指定进度条宽度
                                        .height(10.dp)
                                        .offset(5.dp, 7.dp), // 指定进度条高度
                                    progress = 0.8f,
                                    color = Color(26,207,163),
                                    cornerRadius = 12.dp,
                                    backgroundColor = ProgressGray
                                )


                                Row(modifier = Modifier.offset(0.dp,12.dp)) {
                                    Text(
                                        text = "94",
                                        fontSize = 12.sp,
                                        color = LightGreen

                                    )
                                    Text(
                                        text = "/100",
                                        fontSize = 12.sp,
                                        color = Color.Black
                                    )


                                }
                            }
                            Text(
                                text = "能量+",
                                fontSize = 16.sp,
                                color = LightGreen,
                                fontWeight = FontWeight.W900,
                                modifier = Modifier.offset(5.dp,1.dp)

                            )
                            Text(
                                text = "17",
                                fontSize = 16.sp,
                                color = LightGreen,
                                fontWeight = FontWeight.W900,
                                modifier = Modifier.offset(-4.dp,0.5.dp)
                            )
                            Image(painter = painterResource(id = R.drawable.g0_1_ic_report_lighting), contentDescription =null
                                , modifier = Modifier.offset(-8.dp,5.dp).size(15.dp))

                        }
                        androidx.compose.material3.Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .width(136.dp)
                                .height(54.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = LightGreen)
                        ) {
                            Text(
                                text = "查看报告",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W900
                            )
                        }




                    }
                }
                Spacer(modifier = Modifier.height(40.dp))

            }
        }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthDialogCard(openDialog : MutableState<Boolean>) {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    if (openDialog.value) {
        Dialog(
            onDismissRequest = { openDialog.value = false },
        ) {
            Column() {
                Card(
//                    onClick = { /* Do something */ },
                    modifier = Modifier.size(width = 380.dp, height = 350.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "好友申请",
                            style = TextStyle(
                                fontWeight = FontWeight.ExtraBold, //设置字体粗细
                                fontSize = 22.sp,
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g2_1_img_user04),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .scale(1.2f)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "施&SHI", modifier = Modifier.align(
                                Alignment.CenterHorizontally
                            )
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        TextField(
                            value = text,
                            shape = RoundedCornerShape(10.dp),
                            onValueChange = { text = it },
                            singleLine = false,
                            placeholder = { Text("写两句话和好友打招呼吧", fontSize = 14.sp, color = Gray2) },
//                            label={ Text("写两句话和好友打招呼吧", fontSize = 14.sp, color = Gray2) },
                            modifier = Modifier
                                .size(250.dp, 60.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Gray2,
                                containerColor = Gray3,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(id = R.drawable.g4_4_btn_addfriend),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .clickable(onClick = { openDialog.value = false }),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.g4_4_btn_close),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = { openDialog.value = false })
                )
            }
        }
    }
}}
