package com.example.jetpacktest02.screen

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.GreenLightReminder
import com.example.scaffolddemo.ui.theme.textGray2
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import okhttp3.internal.format
import java.util.*

@Composable
fun GIFimage(
    modifier: Modifier = Modifier,
    gif:Int
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data =gif ).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),

        contentDescription = null,
        modifier = modifier.fillMaxWidth()
    )
}


@Preview
@Composable
fun LightReminderScreen() {
    rememberSystemUiController().setStatusBarColor(
        GreenLightReminder, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.g10_bg_lightreminder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize(), contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = textGray2,
                text = "2月24日 星期五",
                style = TextStyle(
                    fontWeight = W900, //设置字体粗细
                    fontSize = 20.sp,
                )
            )
            Spacer(modifier = Modifier.padding(3.dp))
            val sdf=SimpleDateFormat("HH:mm").format(Date())
            val strTime=sdf
            Text(
                color = textGray2,
                text = strTime,
                style = TextStyle(
                    fontWeight = W400,
                    fontSize = 96.sp,
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 300.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GIFimage(modifier = Modifier.size(460.dp), gif = R.drawable.gif_04)

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 500.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.g10_ic_wait),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.g10_ic_drinknow),
                    contentDescription = null
                )

            }

        }

    }
}