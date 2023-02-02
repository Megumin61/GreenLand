package com.example.jetpacktest02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.ViewModel.MainViewModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.MyBottomNavBar
import com.example.jetpacktest02.config.UsersApplication
import com.example.jetpacktest02.compose.MyTopAppBar
import com.example.jetpacktest02.screen.IslandDeliverScreen
import com.example.jetpacktest02.screen.IslandMemberListScreen
import com.example.jetpacktest02.screen.IslandScreen
import com.example.jetpacktest02.screen.MessageMsgScreen
import com.example.jetpacktest02.ui.main.*
import com.example.scaffolddemo.ui.theme.ScaffoldDemoTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.Objects


/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class RallyActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)


        setContent {

//            WordBookApp()
//            CounterScreen()
            RallyApp()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        GlobalScope.launch {
//            val user = User("jjuntan","18148991553")
//            UsersApplication.database.userDao().insertUser(user)
//        }
//    }


}

@Composable
fun WordBookApp(userViewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
//    val count by mainViewModel.counterLiveData.observeAsState(0)
    val users: List<User> by userViewModel.allUsers.observeAsState(mutableListOf())
//    val users by viewModel.allUsers.observeAsState(listOf())
    val user : User= userViewModel.getUser(2)

    Column{
        Text("success")
        Text(user.name)
//        Text(users[0].name)
//        for (i :User in users)
//            Text(i.name.toString())
        Text(users.size.toString())
    }
}
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@Composable
fun RallyApp() {

//    val users by viewModel.allUsers.observeAsState(listOf())
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

            //管理路由：页面跳转
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
                            navController.popBackStack()//返回到popUpTo中设置的返回路径route
                        },
                        nav02 = {

                            //导航 目的地、返回路径
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
                        nav02 = {
                            navController.navigate(IslandDeliver.route) {
                                launchSingleTop = true; popUpTo(IslandMemberList.route) {}
                            }
                        },
                    )
                }
                composable(route = IslandDeliver.route) {
                    IslandDeliverScreen(
                        nav01 = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(route = Message.route) {
                    MessageScreen(
                        nav01 = {
                            navController.navigate(MessageTap.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = MessageMsg.route) {

                    MessageMsgScreen(
                        //导航函数
                        nav01 = {
                            navController.navigate(MessageTap.route) { launchSingleTop = true; }
                        },
                        nav02 ={
                            navController.navigate(MessageTap.route)
                        }
                    )
                }
                composable(route = MessageTap.route) {
                    MessageTapScreen(
                        nav01 = {
                            navController.navigate(Message.route) { launchSingleTop = true; }
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
