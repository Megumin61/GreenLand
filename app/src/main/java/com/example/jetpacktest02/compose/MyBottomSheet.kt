package com.example.jetpacktest02.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.screen.AvatarItem
import kotlinx.coroutines.launch


/*
* 底部抽屉 公共组件案例
* 复制修改即可使用
* (注意：使用该组件需要在@Composable上方增加“@ExperimentalMaterialApi”的声明,详细用法可参考IslandDeliverScreen.kt)
* */

@ExperimentalMaterialApi
@Composable
fun MyBottomSheet(){

    //底部抽屉 状态变量
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    //选择可见好友 抽屉组件（需要放在背景颜色同级）
    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = MaterialTheme.shapes.large,
        sheetContent = {
            Column {
                ListItem(
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "选择可见好友",
                                style = TextStyle(
                                    fontWeight = FontWeight.W900, //设置字体粗细
                                    fontSize = 16.sp,
                                ),
                                modifier = Modifier
                            )

                            Image(
                                painter = painterResource(id = R.drawable.g4_6_1_ic_cancel),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .offset(x = 160.dp)
                                    .clickable(onClick = { scope.launch { state.hide() } }),
                            )
                        }

                    }
                )

                //第一行头像
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AvatarItem("全部好友", R.drawable.g4_6_1_ic_allcansee)
                    AvatarItem("幻想世界", R.drawable.g4_3_avatar3)
                    AvatarItem("幻想世界", R.drawable.g4_3_avatar3)
                    AvatarItem("幻想世界", R.drawable.g4_3_avatar3)
                }

                //第二行头像

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AvatarItem("幻想世界", R.drawable.g4_3_avatar3)
                    AvatarItem("幻想世界", R.drawable.g4_3_avatar3)
                    AvatarItem("幻想世界", R.drawable.g4_3_avatar3)
                    AvatarItem("幻想世界", R.drawable.g4_3_avatar3)
                }


            }
        }
    ) {
    }

    //处理”返回键“事件，当抽屉展开时，返回键触发“关闭抽屉”
    BackHandler(
        enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                || state.currentValue == ModalBottomSheetValue.Expanded),
        onBack = {
            scope.launch {
                state.hide()
            }
        }
    )

}