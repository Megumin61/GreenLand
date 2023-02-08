package com.example.jetpacktest02.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Looper
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.jetpacktest02.R
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.utils.GPSUtils
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TestScreen(
    nav01: () -> Unit = {},
    userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    var distance by remember {
        mutableStateOf(0.0)
    }

    LaunchedEffect(key1 = userViewModel._uiState.value.mePos.value) {
        distance = GPSUtils.getInstance().getDistance(
            userViewModel._uiState.value.mePos.value.longitude,
            userViewModel._uiState.value.mePos.value.latitude, 113.25937,
            23.169966,
        )
    }

    //配置顶部状态栏颜色
    rememberSystemUiController().setStatusBarColor(
        Color.White, darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = "test",
                            style = TextStyle(
                                fontWeight = FontWeight.W500, //设置字体粗细
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.offset(-15.dp, 0.dp)//向左偏移一段距离
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
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp, //设置阴影
            )
        }
    ) {

        var locationCallback: LocationCallback? = null
        var fusedLocationClient: FusedLocationProviderClient? = null
        val context = LocalContext.current
        var currentLocation by remember {
            mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        val locationPermissionsState = rememberMultiplePermissionsState(
            listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )

        if (locationPermissionsState.allPermissionsGranted) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    for (lo in p0.locations) {
                        // Update UI with location data
                        currentLocation = LocationDetails(lo.latitude, lo.longitude)
                    }
                }
            }
            locationCallback.let {
                val locationRequest = LocationRequest.create().apply {
                    interval = 10000
                    fastestInterval = 5000
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                }
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    it,
                    Looper.getMainLooper()
                )
            }

            val pos = GPSUtils.getInstance().getProvince()
            userViewModel._uiState.value.mePos.value = pos
            Column() {
                Text("Thanks! I can access your exact location :D")
                Text("latitude" + currentLocation.latitude.toString())
                Text("longitude" + currentLocation.longitude.toString())
                Text("latitude2:" + pos.latitude)
                Text("longitude2:" + pos.longitude)
                Text("实时距离:" + distance)
            }
        } else {
            Column {
                val allPermissionsRevoked =
                    locationPermissionsState.permissions.size ==
                            locationPermissionsState.revokedPermissions.size

                val textToShow = if (!allPermissionsRevoked) {
                    // If not all the permissions are revoked, it's because the user accepted the COARSE
                    // location permission, but not the FINE one.
                    "Yay! Thanks for letting me access your approximate location. " +
                            "But you know what would be great? If you allow me to know where you " +
                            "exactly are. Thank you!"
                } else if (locationPermissionsState.shouldShowRationale) {
                    // Both location permissions have been denied
                    "Getting your exact location is important for this app. " +
                            "Please grant us fine location. Thank you :D"
                } else {
                    // First time the user sees this feature or the user doesn't want to be asked again
                    "This feature requires location permission"
                }

                val buttonText = if (!allPermissionsRevoked) {
                    "Allow precise location"
                } else {
                    "Request permissions"
                }

                Text(text = textToShow)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { locationPermissionsState.launchMultiplePermissionRequest() }) {
                    Text(buttonText)
                }
            }
        }


    }

}

data class LocationDetails(var latitude: Double, var longitude: Double)