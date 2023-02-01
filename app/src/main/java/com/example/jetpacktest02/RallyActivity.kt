package com.example.jetpacktest02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.MyBottomNavBar
import com.example.jetpacktest02.screen.LoginFrontScreen
import com.example.jetpacktest02.screen.LoginLoadingScreen
import com.example.jetpacktest02.screen.PhoneLoginScreen
import com.example.jetpacktest02.ui.main.*
import com.example.scaffolddemo.ui.theme.ScaffoldDemoTheme
import dagger.hilt.android.AndroidEntryPoint


/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

//@AndroidEntryPoint
class RallyActivity : ComponentActivity() {
//    private val newUserActivityRequestCode = 1
//    private val userViewModel: UserViewModel by viewModels {
//        UserViewModelFactory((application as UsersApplication).repository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()

        }
    }
}

@Composable
fun RallyApp(viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
//    val weatherNow = viewModel.weatherNow.observeAsState()
//    println(weatherNow.value.toString())
    var currentScreen: RallyDestination by remember { mutableStateOf(Overview) }
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val Textvalue: String = ""


    // Fetch your currentDestination:
    val currentDestination = currentBackStack?.destination
    // Change the variable to this and use Overview as a backup screen if this returns null
//        val currentScreen = rallyTabRowScreens.find { it.route == currentDestination?.route } ?: Overview

    //skc 初始化
    ScaffoldDemoTheme {

        Scaffold(
//                topBar = { MyTopAppBar() },
            bottomBar = {
                MyBottomNavBar(
                    navControl = navController,
                    nav01 = {
                        navController.navigateSingleTopTo(Plant.route)
                    },
                    nav02 = {
                        navController.navigateSingleTopTo(VipUnsigned.route)
                    },
                    nav03 = {
                        navController.navigateSingleTopTo(IslandChooseIsland.route)
                    },
                    nav04 = {
                        navController.navigateSingleTopTo(Message.route)
                    },
                    nav05 = {
                        navController.navigateSingleTopTo(My.route)
                    }
                )
            }
        ) { innerPadding ->

//            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Plant.route,
                modifier = Modifier.padding(innerPadding)

            ) {
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
                        },
                        nav02 = {
                            navController.navigateSingleTopTo(Dailyhealthmessage.route)
                        },
                        nav03 = {
                            navController.navigateSingleTopTo(IslandChooseIsland.route)
                        },
                        nav04 = {
                            navController.navigateSingleTopTo(Message.route)
                        },
                        nav05 = {
                            navController.navigateSingleTopTo(My.route)
                        },
                        nav06 = {
                            navController.navigateSingleTopTo(PlantBagPossessed.route)
                        },
                        nav07 = {
                            navController.navigateSingleTopTo(VipUnsigned.route)
                        },
                        nav08 = {
                            navController.navigateSingleTopTo(PhoneLogin.route)
                        }


                    )
                }
                composable(route = PlantPlan.route) {
                    PlantPlanScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        },
                        nav02 = {
                            navController.navigateSingleTopTo(PlantLookingForPlanFoot.route)
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
                composable(route = IslandChooseIsland.route) {
                    IslandChooseIslandScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        }
                    )
                }
                composable(route = Message.route) {
                    MessageScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        }
                    )
                }
                composable(route = My.route) {
                    MyScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        }
                    )
                }
                composable(route = PlantBagPossessed.route) {
                    PlantBagPossessedScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        }
                    )
                }
                composable(route = PlantFoot.route) {
                    PlantFootScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(PlantLookingForPlanFoot.route)
                        }
                    )
                }
                composable(route = PlantLookingForPlanFoot.route) {
                    PlantLookingForPlanFootScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(PlantPlan.route)
                        }
                    )
                }
                composable(route = VipUnsigned.route) {
                    VipUnsignedScreen(
                        nav01 = {
                            navController.navigateSingleTopTo(Plant.route)
                        }
                    )
                }
                composable(route = LoginLoading.route) {
                    LoginLoadingScreen(

                    )
                }
                composable(route = LoginFront.route) {
                   LoginFrontScreen(

                    )
                }
                composable(route = PhoneLogin.route) {
                    PhoneLoginScreen(

                    )
                }
                composable(route =VipPage.route) {
                    PhoneLoginScreen(

                    )
                }
            }
        }
    }

}
