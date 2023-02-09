/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetpacktest02.ui.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R


/**
 * The Bills screen.
 */
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun MyScreen(
//            bills : (String) -> Unit = {},
    nav01: () -> Unit={},

) {
    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(

                brush = Brush.verticalGradient(
                    listOf(
                        Color(187, 238, 232),
                        Color(227, 237, 227)
                    )
                )
            ),

        contentAlignment = Alignment.TopCenter
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier.padding(top=50.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
            UserInfo()
            UserSign()
            Image(
                painter = painterResource(id = R.drawable.g8_1_img_card),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 13.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.g7_0_btnbg),
                    contentDescription = null,
                )
                Column(verticalArrangement = Arrangement.spacedBy(27.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.outlinedButtonColors(),
                        contentPadding = PaddingValues(0.dp),
                        shape = RectangleShape
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.g7_0_ic_health),
                            contentDescription = null,
                            modifier = Modifier.size(width=300.dp,height=54.dp)
                        )

                    }
                    Button(onClick = nav01,
                        colors = ButtonDefaults.outlinedButtonColors(),
                        contentPadding = PaddingValues(0.dp),
                        shape = RectangleShape) {
                        Image(
                            painter = painterResource(id = R.drawable.g7_0_ic_cupboard),
                            contentDescription = null,
                            modifier = Modifier.size(width=300.dp,height=54.dp)
                        )

                    }
                    Button(onClick = { /*TODO*/ },
                        colors = ButtonDefaults.outlinedButtonColors(),
                        contentPadding = PaddingValues(0.dp),
                        shape = RectangleShape) {
                        Image(
                            painter = painterResource(id = R.drawable.g7_0_ic_settings),
                            contentDescription = null,
                            modifier = Modifier.size(width=300.dp,height=54.dp)
                        )

                    }

                }
            }

        }
    }


   /* Column{
        Text("7.0-My")
        Button(
            onClick = nav01,
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding

        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("1.1-Plant")
        }
    }*/


}


@Composable
fun BtnArea(nav01: () -> Unit={}){

}
@Composable
fun UserInfo(){
 Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 15.dp)){
     Image(
         painter = painterResource(id = R.drawable.g7_0_userprofile),
         contentDescription = null,
         Modifier.clip(CircleShape)
     )
     Spacer(modifier = Modifier.width(10.dp))
     Column() {
         Text(text = "Miguminnn", fontSize = 18.sp,
             fontWeight = W700, 
             color = Color(73,74,89))
         Spacer(modifier = Modifier.height(10.dp))
         Row(horizontalArrangement = Arrangement.spacedBy(10.dp)){
             Image(
                 painter = painterResource(id = R.drawable.g7_0_achi1),
                 contentDescription = null)
             Image(
                 painter = painterResource(id = R.drawable.g7_0_achi2),
                 contentDescription = null)
         }

     }
     Spacer(modifier = Modifier.width(40.dp))
     Button(onClick = { /*TODO*/ },
         colors = ButtonDefaults.outlinedButtonColors(),
         contentPadding = PaddingValues(0.dp),
         shape = RectangleShape) {
         Image(
             painter = painterResource(id = com.google.android.material.R.drawable.material_ic_keyboard_arrow_right_black_24dp),
             contentDescription = null,
             modifier = Modifier.size(40.dp)
         )

     }

     }

}
@Composable
fun UserSign(){
    Box(contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.g7_0_signbg),
            contentDescription = null,
        )
        var text by remember { mutableStateOf("") }
        CustomEditHint(
            text = text,

            onValueChange = {
                text = it.trim()
            },
            modifier = Modifier
                .width(250.dp)
                .background(Color.Transparent)
                .padding(bottom = 5.dp),
            hint = "请输入个性签名",
        )




    }
}


/**
 * 只增加 hint;  适合多行输入框, 没有前后Icon的小输入框; 背景 尺寸等 定义在modifier中;
 * @param hint: 空字符时的提示
 */
@Composable
fun CustomEditHint(
    text: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    hint: String = "请输入",

    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    
) {
    // 焦点, 用于控制是否显示 右侧叉号
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = singleLine,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,

        decorationBox = @Composable { innerTextField ->
            if(text.isEmpty())
                Text(text = hint,
                    color =Color.LightGray,
                    style = textStyle,
                    fontSize = 16.sp,
                )
            innerTextField()
        }
    )
}
