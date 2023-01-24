
package com.example.jetpacktest02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpacktest02.ui.main.BillsScreen
import com.example.jetpacktest02.ui.main.DailyhealthmessageScreen
import com.example.jetpacktest02.ui.main.PlantPlanScreen
import com.example.jetpacktest02.ui.main.PlantScreen


/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
        var currentScreen: RallyDestination by remember { mutableStateOf(Overview) }
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        // Fetch your currentDestination:
        val currentDestination = currentBackStack?.destination
        // Change the variable to this and use Overview as a backup screen if this returns null
//        val currentScreen = rallyTabRowScreens.find { it.route == currentDestination?.route } ?: Overview

        Scaffold(
            topBar = {
            }
        ) { innerPadding ->
//            Box(Modifier.padding(innerPadding)) {
//                currentScreen.screen()
//            }
            NavHost(
                navController = navController,
                startDestination  = Plant.route,
                modifier = Modifier.padding(innerPadding)

            ){
                composable(route = Accounts.route) {
                    AccountsScreen()
                }
                composable(route = Bills.route) {
                    BillsScreen(
                        bills = {
                            navController.navigateSingleTopTo(Accounts.route)
                        }
                    )
                }
                composable(route = Plant.route) {
                    PlantScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(PlantPlan.route)
                        } ,
                        nav02 = {
                            navController.navigateSingleTopTo(Dailyhealthmessage.route)
                        }
                    )
                }
                composable(route = PlantPlan.route) {
                    PlantPlanScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        }
                    )
                }
                composable(route = Dailyhealthmessage.route) {
                    DailyhealthmessageScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        }
                    )
                }


            }
        }

}
