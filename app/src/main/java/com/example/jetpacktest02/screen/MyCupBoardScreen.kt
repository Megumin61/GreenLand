import android.icu.text.CurrencyPluralInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

// 水平指示器

//pagerState为底部viewpager参数
@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground=true,widthDp=393,heightDp=851)
@Composable
fun MyCupBoardScreen() {

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
        Box(){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val pagerState: PagerState = remember { PagerState() }
                CupTopAppBar()
                HorizontalPager(count = 3, state = pagerState) { page ->
                    if (page == 0) {
                        CupBoard1()
                    }
                    if (page == 1) {
                        CupBoard2()
                    }
                    if (page == 2) {
                        CupBoard3()
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

@Composable
fun CupBoard1 (){
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
                PlantAdd()
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Plant03()
                Plant04()
                PlantAdd()
            }
        }
    }
}
@Composable
fun CupBoard2 (){
Box(contentAlignment = Alignment.Center){
    Image(painter = painterResource(
        id = R.drawable.g7_2_cupboard),
        contentDescription = null,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(18.dp)){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            PlantLocked()
            PlantLocked()
            PlantLocked()
        }
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            PlantLocked()
            PlantLocked()
            PlantLocked()
        }
    }
}



}
@Composable
fun CupBoard3 (){
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
                PlantLocked()
                PlantLocked()
                PlantLocked()
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                PlantLocked()
                PlantLocked()
                PlantLocked()
            }
        }
    }


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
@Composable
fun PlantLocked(){//------------第一个植物及其名称—---------------
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Image(painter = painterResource(
            id = R.drawable.g7_2_img_locked),
            contentDescription = null,
        )
        Text(text = "", color = Color.White, fontWeight = FontWeight.W700, fontSize = 13.sp)
    }
}
@Composable
fun PlantAdd(){//------------第一个植物及其名称—---------------
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