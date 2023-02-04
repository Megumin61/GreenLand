package com.example.jetpacktest02.ViewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.Gray2
import com.example.scaffolddemo.ui.theme.Gray3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MsgDialogCard(openDialog : MutableState<Boolean>) {
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
                        androidx.compose.material3.Text(
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
                        androidx.compose.material3.Text(text = "施&SHI", modifier = Modifier.align(
                            Alignment.CenterHorizontally))
                        Spacer(modifier = Modifier.height(15.dp))
                        TextField(
                            value = text,
                            shape = RoundedCornerShape(10.dp),
                            onValueChange = { text = it },
                            singleLine=false,
                            placeholder = { androidx.compose.material3.Text("写两句话和好友打招呼吧", fontSize = 14.sp, color = Gray2) },
//                            label={ Text("写两句话和好友打招呼吧", fontSize = 14.sp, color = Gray2) },
                            modifier = Modifier
                                .size(250.dp, 60.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Gray2,
                                containerColor = Gray3,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor  = Color.Transparent
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
                        .align(Alignment.CenterHorizontally).clickable(onClick = {openDialog.value=false})
                )
            }
        }
    }
}
