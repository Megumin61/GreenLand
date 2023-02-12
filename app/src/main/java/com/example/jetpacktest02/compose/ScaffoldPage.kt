package com.example.jetpacktest02.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scaffolddemo.ui.theme.LightGreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScaffoldPage() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier.width(300.dp),
                    snackbarData = data,
                    containerColor = LightGreen,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(30.dp)
                )
            }
        },
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
                onClick = {
                    // show snackbar as a suspend function
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Snackbar # ${++clickCount}"
                        )
                    }
                }
            ) { Text("Show snackbar", modifier = Modifier.align(Alignment.CenterVertically)) }
        },
        content = { innerPadding ->
            Text(
                text = "GreenLand ScaffoldPage",
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
//                    .align(Alignment.Center)
                    .wrapContentSize(),
                textAlign = TextAlign.Center
            )
        }
    )
}
