import android.annotation.SuppressLint
import android.icu.text.CurrencyPluralInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ui.main.HealthTopAppBar
import com.example.jetpacktest02.ui.main.HealthViewTabRow
import com.example.scaffolddemo.ui.theme.DateBlue
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2
import com.example.scaffolddemo.ui.theme.GreenMain
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// 水平指示器

//pagerState为底部viewpager参数
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun MyCupBoardScreen(nav01: () -> Unit={},
                     nav02: () -> Unit={},) {

    var ifdialog by remember{
        mutableStateOf(false)}
    rememberSystemUiController().setStatusBarColor(
        Green1, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )

    androidx.compose.material.Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Miguminnn的展柜",
                            style = TextStyle(
                                fontWeight = FontWeight.W900, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-25.dp, 0.dp)//向左偏移一段距离
                        )
                    }
                },
                //左侧按钮
                navigationIcon = {
                    IconButton(onClick = nav01) {
                        Icon(
                            painter = painterResource(id = R.drawable.g1_2_0_ic_arrow_left),
                            contentDescription = ""
                        )

                    }
                },
                //右侧按钮
                actions = {
//                    Image(
//                        painter = painterResource(id = R.drawable.g2_5_btn_friend),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .width(100.dp)
//                            .height(100.dp)
//                            .offset(-10.dp, 0.dp)
////                            .clickable(onClick = {userViewModel.uiState.value.pageState.value=3})
//                    )
                },

                backgroundColor = Green1,
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ){Box(
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

        Box(){

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val pagerState: PagerState = remember { PagerState() }

                HorizontalPager(count = 3, state = pagerState) { page ->
                    if (page == 0) {
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.g7_2_cupboard
                                ),
                                contentDescription = null,
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)
                                , modifier = Modifier.padding(top=5.dp)) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Plant01()
                                    Plant02()
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_add),
                                            contentDescription = null,
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 12.sp)
                                    }
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Plant03()
                                    Plant04()
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_add),
                                            contentDescription = null,
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                    if (page == 1) {
                        Box(contentAlignment = Alignment.Center){
                            Image(painter = painterResource(
                                id = R.drawable.g7_2_cupboard),
                                contentDescription = null,
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)){
                                Column(horizontalAlignment = Alignment.CenterHorizontally){
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                }
                                Column (horizontalAlignment = Alignment.CenterHorizontally){
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                }
                            }
                    }


                }
                    if (page == 2) {
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.g7_2_cupboard
                                ),
                                contentDescription = null,
                            )
                            Row(modifier=Modifier.padding(top=0.dp),
                                horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(0.dp)
                                    ) {
                                        Image(painter = painterResource(
                                            id = R.drawable.g7_2_img_locked),
                                            contentDescription = null,
                                            modifier = Modifier.clickable(onClick =  {ifdialog=true })
                                        )
                                        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
                                    }
                                }
                            }
                        }
                    }

            }
                HorizontalPagerIndicator(pagerState = pagerState,
                    activeColor = Color(127,199,168),
                    inactiveColor = Color.White,
                    spacing = 13.dp)
        }

    }

    }



}
    if (ifdialog) {
        //背景颜色
        Image(
            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_backblur),
            contentDescription = null,
            alpha = 0.34f,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )
        Box{


            Image(


                painter = painterResource(id = com.example.jetpacktest02.R.drawable.g7_2_1_lockdialog),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 291.dp, height = 362.dp)
                    .offset(50.dp, 200.dp)
            )






            Button(onClick = nav02,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenMain,
                    contentColor = GreenMain
                ),
                modifier = Modifier
                    .size(width = 201.dp, height = 42.dp)
                    .offset(96.dp, 494.dp)

            ) {
                Text(text = "了解绿岛会员", color = Color.White , fontSize = 16.sp)
            }


        }
//        Image(
//            painter = painterResource(id = com.example.jetpacktest02.R.drawable.g8_dialog),
//            contentDescription = null,
//            alignment = Alignment.Center,
//            modifier = Modifier
//                .size(width = 291.dp, height = 362.dp)
//                .offset(50.dp, 200.dp)
//        )
        if (!ifdialog){}
    }

@Composable
fun CupBoard1 (){

}
@Composable
fun CupBoard2 (){

}



}
@Composable
fun CupBoard3 (){



}










@Composable
fun Plant01(){//------------第一个植物及其名称—---------------

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Image(painter = painterResource(
            id = R.drawable.g7_2_img_sunflower),
            contentDescription = null,
        )
        Text(text = "向白葵", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
    }
}
@Composable
fun Plant02(){//------------第一个植物及其名称—---------------
    Column(horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Image(painter = painterResource(
            id = R.drawable.g7_2_img_cactus),
            contentDescription = null,
        )
        Text(text = "仙人掌", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
    }
}
@Composable
fun Plant03(){//------------第一个植物及其名称—---------------
    Column(horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Image(painter = painterResource(
            id = R.drawable.g7_2_img_grass),
            contentDescription = null,
        )
        Text(text = "螳螂草", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
    }
}
@Composable
fun Plant04(){//------------第一个植物及其名称—---------------
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)

    ) {
        Image(painter = painterResource(
            id = R.drawable.g7_2_img_lotus),
            contentDescription = null,
        )
        Text(text = "八爪花", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
    }
}
//@Composable
//fun PlantLocked(){//------------第一个植物及其名称—---------------
//    Column(horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(0.dp)
//    ) {
//        Image(painter = painterResource(
//            id = R.drawable.g7_2_img_locked),
//            contentDescription = null,
//            modifier = Modifier.clickable(onClick =  {ifdialog=true })
//        )
//        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
//    }
//}
//@Composable
//fun PlantAdd(){//------------第一个植物及其名称—---------------
//    Column(horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(0.dp)
//    ) {
//        Image(painter = painterResource(
//            id = R.drawable.g7_2_img_add),
//            contentDescription = null,
//        )
//        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 12.sp)
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupTopAppBar() {
    androidx.compose.material.TopAppBar(title = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Miguminnn的展柜",
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