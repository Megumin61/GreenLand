package com.example.jetpacktest02.compose

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpacktest02.ViewModel.UserViewModel

@Composable
fun StepCounter(){
    val userViewModel: UserViewModel = viewModel()

    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val stepSensor1: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) // 自应用运行以来STEP_DETECTOR检测到的步数
    val stepSensor2: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) // 自系统开机以来STEP_COUNTER检测到的步数

    // for sensor event listener and initializing it.
    val stepSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // method to check accuracy changed in sensor.
        }
        // on below line we are creating a sensor on sensor changed
        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                if (event.values[0] == 1.0f) {
                    userViewModel.uiState.value.stepDetector.value++
//                    mStepDetector++
                }
            } else if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                userViewModel.uiState.value.stepCounter.value = event.values[0].toInt()
//                mStepCounter = event.values[0].toInt()
            }
//            val desc = String.format("设备检测到您当前走了%d步，自开机以来总数为%d步", mStepDetector, mStepCounter)

        }
    }
    sensorManager.registerListener(
        stepSensorEventListener,
        stepSensor1,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    sensorManager.registerListener(
        stepSensorEventListener,
        stepSensor2,
        SensorManager.SENSOR_DELAY_NORMAL
    )
}