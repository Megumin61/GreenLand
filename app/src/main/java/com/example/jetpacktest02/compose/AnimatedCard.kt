package com.example.jetpacktest02.compose

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.BlueGray3
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCard() {
    var state1 by remember {
        mutableStateOf(false)
    }
    var state2 by remember {
        mutableStateOf(false)
    }
    var state3 by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = state1){
        delay(250)
        state1 = true
    }
    LaunchedEffect(key1 = state2){
        delay(500)
        state2 = true
    }
    LaunchedEffect(key1 = state3){
        delay(750)
        state3 = true
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.offset(0.dp, -20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(
                visible = state1,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_feeling),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                    )
                    Text(
                        text ="234g",
                        fontWeight = FontWeight.W900,
                        color = BlueGray3,
                        modifier = Modifier.offset(80.dp, 30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(
                visible = state2,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_water),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                    )
                    Text(
                        text = "233g",
                        fontWeight = FontWeight.W900,
                        color = BlueGray3,
                        modifier = Modifier.offset(80.dp, 30.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(
                visible = state3,
                enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeIn(initialAlpha = 0.3f) + expandIn(expandFrom = Alignment.TopStart),
                exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                        fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
            ) {
                Box(modifier = Modifier.align(Alignment.Start)) {
                    Image(
                        painter = painterResource(id = R.drawable.g1_1_ic_huoli),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                    )
                    Text(
                        text = "23g",
                        fontWeight = FontWeight.W900,
                        color = BlueGray3,
                        modifier = Modifier.offset(80.dp, 30.dp)
                    )
                }
            }
        }
}}