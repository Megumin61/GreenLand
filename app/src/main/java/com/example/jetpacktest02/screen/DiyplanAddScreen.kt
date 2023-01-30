package com.example.jetpacktest02.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R


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

@Composable
fun DayItem(){
    Text(text = "周一", fontSize = 12.sp)

}

@Preview
@Composable
fun DiyplanAddScreen(){
    Image(
        painter = painterResource(id = R.drawable.g1_2_5_bg),
        contentDescription = null,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
    )
    Column() {
        Row(Modifier.padding(top = 62.dp, start = 26.dp), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                contentDescription =null)
            Text(text = "自定义计划", color = Color.Black, fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 18.dp)
                    .offset(y = -4.dp) )
        }
        Image(painter = painterResource(id = R.drawable.g1_2_icbg_eating), contentDescription =null , modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 26.dp))
        /*Image(painter = painterResource(id = R.drawable.g1_2_5_bg_dailyaim), contentDescription =null, modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(top = 18.dp))*/
        Box(){
            Image(painter = painterResource(id = R.drawable.g1_2_5_bg_dailyaim), contentDescription =null, modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 18.dp))
            Row(Modifier.padding(start = 60.dp, top = 126.dp)) {
                DayItem()
            }
            Image(painter = painterResource(id = R.drawable.g1_2_3_btn_blankremind), contentDescription =null,
                modifier = Modifier
                .padding(horizontal = 48.dp)
                .padding(top = 200.dp))
            
        }
    }
}