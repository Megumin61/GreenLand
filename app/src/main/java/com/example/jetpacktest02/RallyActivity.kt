package com.example.jetpacktest02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.MyBottomNavBar
import com.example.jetpacktest02.compose.MyTopAppBar
import com.example.jetpacktest02.screen.IslandMemberListScreen
import com.example.jetpacktest02.screen.IslandScreen
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

    ScaffoldDemoTheme {
        Scaffold(

            bottomBar = {
                MyBottomNavBar(
                    navControl = navController,
                    nav01 = {
                        navController.navigate(Plant.route) {
                            launchSingleTop = true; popUpTo(Plant.route)
                        }
                    },
                    nav02 = {
                        navController.navigate(VipUnsigned.route) {
                            launchSingleTop = true;popUpTo(
                            Plant.route
                        )
                        }
                    },
                    nav03 = {
                        navController.navigate(IslandChooseIsland.route) {
                            launchSingleTop = true;popUpTo(Plant.route)
                        }
                    },
                    nav04 = {
                        navController.navigate(Message.route) {
                            launchSingleTop = true;popUpTo(Plant.route)
                        }
                    },
                    nav05 = {
                        navController.navigate(My.route) {
                            launchSingleTop = true;popUpTo(Plant.route)
                        }
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
                            navController.navigate(Accounts.route)
                        }
                    )
                }
                composable(route = Plant.route) {
                    PlantScreen(
                        nav01 = {
                            navController.navigate(PlantPlan.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(Dailyhealthmessage.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav03 = {
                            navController.navigate(IslandChooseIsland.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav04 = {
                            navController.navigate(Message.route) { launchSingleTop = true; }
                        },
                        nav05 = {
                            navController.navigate(My.route) { launchSingleTop = true; }
                        },
                        nav06 = {
                            navController.navigate(PlantBagPossessed.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav07 = {
                            navController.navigate(VipUnsigned.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = PlantPlan.route) {
                    PlantPlanScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(PlantLookingForPlanFoot.route) {
                                launchSingleTop = true;
                            }
                        }
                    )
                }
                composable(route = Dailyhealthmessage.route) {
                    DailyhealthmessageScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = IslandChooseIsland.route) {
                    IslandChooseIslandScreen(
                        nav01 = {
                            navController.navigate(Island.route) {
                                launchSingleTop = true;popUpTo(
                                IslandChooseIsland.route
                            ) {}
                            }
                        }
                    )
                }
                composable(route = Island.route) {
                    IslandScreen(
                        nav01 = {
                            navController.popBackStack()
                        },
                        nav02 = {
                            navController.navigate(IslandMemberList.route) {
                                launchSingleTop = true; popUpTo(Island.route) {}
                            }
                        }
                    )
                }
                composable(route = IslandMemberList.route) {
                    IslandMemberListScreen(
                        nav01 = {
                            navController.popBackStack()
                        },
                    )
                }
                composable(route = Message.route) {
                    MessageScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = My.route) {
                    MyScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = PlantBagPossessed.route) {
                    PlantBagPossessedScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = PlantFoot.route) {
                    PlantFootScreen(
                        nav01 = {
                            navController.navigate(PlantLookingForPlanFoot.route) {
                                launchSingleTop = true;
                            }
                        }
                    )
                }
                composable(route = PlantLookingForPlanFoot.route) {
                    PlantLookingForPlanFootScreen(
                        nav01 = {
                            navController.navigate(PlantPlan.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = VipUnsigned.route) {
                    VipUnsignedScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }
                    )
                }

            }
        }
    }

}
