import android.icu.text.CurrencyPluralInfo
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.HealthTopAppBar
import com.example.jetpacktest02.ui.main.HealthViewTabRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

// 水平指示器

//pagerState为底部viewpager参数
@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun MySettingScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(

                color = Color(243, 243, 243)
            ),

        contentAlignment = Alignment.TopCenter
    ){

        Column() {
            SettingTopAppBar()
            Column(modifier = Modifier.background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
                , verticalAlignment =Alignment.CenterVertically) {
                    Text(text = "头像", fontSize = 16.sp, fontWeight = W400, color = Color(73,74,89))
                    Image(
                        painter = painterResource(id = R.drawable.g7_0_userprofile),
                        contentDescription = null,
                        Modifier.clip(CircleShape)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(339.dp)
                )
            }

            Column(modifier = Modifier.background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp, bottom = 17.dp, start = 20.dp, end = 20.dp)
                    , verticalAlignment =Alignment.CenterVertically) {
                    Text(text = "名称", fontSize = 16.sp, fontWeight = W400, color = Color(73,74,89))
                    Text(text = "Miguminnnn", fontSize = 16.sp, fontWeight = W400, color = Color(149,152,172)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(350.dp)
                )
            }

            Column(modifier = Modifier.background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 17.dp, bottom = 17.dp, start = 20.dp, end = 20.dp)
                    , verticalAlignment =Alignment.CenterVertically) {
                    Text(text = "个性签名", fontSize = 16.sp, fontWeight = W400, color = Color(73,74,89))
                    Text(text = "996是年轻人的福报啊", fontSize = 16.sp, fontWeight = W400, color = Color(149,152,172)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(350.dp)
                )
            }

            Column(modifier = Modifier.background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 20.dp)
                    , verticalAlignment =Alignment.CenterVertically) {
                    Text(text = "成就展示", fontSize = 16.sp, fontWeight = W400, color = Color(73,74,89))
                    SwitchDemo()

                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(350.dp)
                )
            }

            Column(modifier = Modifier.background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 20.dp)
                    , verticalAlignment =Alignment.CenterVertically) {
                    Text(text = "允许陌生人添加好友", fontSize = 16.sp, fontWeight = W400, color = Color(73,74,89))
                    SwitchDemo()

                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(350.dp)
                )
            }

            Column(modifier = Modifier.background(color = Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.SpaceBetween,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 20.dp)
                    , verticalAlignment =Alignment.CenterVertically) {
                    Text(text = "允许通过二维码添加我", fontSize = 16.sp, fontWeight = W400, color = Color(73,74,89))
                    SwitchDemo()

                }
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(350.dp)
                )
            }
        }
    }


}

@Composable
fun SettingTopAppBar() {
    androidx.compose.material.TopAppBar(title = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "设置",
                style = TextStyle(
                    fontWeight = FontWeight.W900, //设置字体粗细
                    fontSize = 18.sp,
                ),
                modifier = Modifier.offset(-35.dp, 0.dp)//向左偏移一段距离
            )
        }
    },
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        elevation = 0.dp, //设置阴影
        //左侧按钮
        navigationIcon = {

            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "",
                )
            }
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


//    val showMenu = remember {
//        mutableStateOf(false)
//    }

//    TopAppBar(
//        title = {
//            Text(text =, color = Color.White)
//        },
//        backgroundColor = MaterialTheme.colors.primary,
//        navigationIcon = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Menu, contentDescription = "", tint = Color.White)
//            }
//        },
//        actions = {
//            IconButton(onClick = {}) {
//                Icon(Icons.Default.Settings, contentDescription = "", tint = Color.White)
//            }
//            IconButton(onClick = { showMenu.value = !showMenu.value }) {
//                Icon(Icons.Default.MoreVert, contentDescription = "", tint = Color.White)
//            }
//
//            DropdownMenu(expanded = showMenu.value, onDismissRequest = { showMenu.value = false }) {
//                DropdownMenuItem(onClick = {}) {
//                    TextButton(onClick = { showMenu.value = false }) {
//                        Text(text = "Settings", color = MaterialTheme.colors.primary)
//                    }
//                }
//                DropdownMenuItem(onClick = {}) {
//                    TextButton(onClick = { showMenu.value = false }) {
//                        Text(text = "Search", color = MaterialTheme.colors.primary)
//                    }
//                }
//            }
//        }
//    )


@Composable
fun SwitchDemo(){
    val status= rememberSaveable() {
        mutableStateOf(false)
    }
    val num= rememberSaveable() {
        mutableStateOf(0f)
    }
    Column(modifier = Modifier
        .statusBarsPadding()
        ) {
        Switch(
            checked = status.value,
            colors = SwitchDefaults.colors(
                checkedThumbColor=Color.White,
                checkedBorderColor = Color(127,199,168),
                checkedTrackColor=Color(127,199,168),
                uncheckedThumbColor=Color.White,
                uncheckedTrackColor=Color(211,211,211),
                        uncheckedBorderColor = Color(211,211,211)



            ),
            onCheckedChange = {
                status.value=it
            },
            modifier=Modifier.offset(10.dp),
            enabled=true,

            /**
             * checkedThumbColor - 启用和选中时用于拇指的颜色
             * checkedTrackColor - 启用和选中时用于轨道的颜色
             * checkedTrackAlpha - 应用于 checkedTrackColor 和 disabledCheckedTrackColor 的 alpha
             * uncheckedThumbColor - 启用和取消选中时用于拇指的颜色
             * uncheckedTrackColor - 启用和未选中时用于轨道的颜色
             * uncheckedTrackAlpha - 应用于 uncheckedTrackColor 和 disabledUncheckedTrackColor 的 alpha
             * disabledCheckedThumbColor - 禁用和选中时用于拇指的颜色
             * disabledCheckedTrackColor - 禁用和选中时用于轨道的颜色
             * disabledUncheckedThumbColor - 禁用和未选中时拇指使用的颜色
             * disabledUncheckedTrackColor - 禁用和未选中时用于轨道的颜色
             */
           /* colors= SwitchDefaults.colors(
                checkedThumbColor= Color.Black,          //启用和选中时用于拇指的颜色
                checkedTrackColor= Color.White,             //启用和选中时用于轨道的颜色
            )*/


        )

    }
}
