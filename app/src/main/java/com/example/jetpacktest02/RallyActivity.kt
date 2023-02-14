package com.example.jetpacktest02

import MyCupBoardScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpacktest02.Entity.User
import com.example.jetpacktest02.ViewModel.MarsViewModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.MyBottomNavBar
import com.example.jetpacktest02.compose.StepCounter
import com.example.jetpacktest02.config.UsersApplication
import com.example.jetpacktest02.screen.*
import com.example.jetpacktest02.ui.main.MessageMsgScreen
import com.example.jetpacktest02.ui.main.*
import com.example.scaffolddemo.ui.theme.ScaffoldDemoTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


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
            RallyApp()
//            StepCounter() //全局计步器
//            WordBookApp()
        }
    }

    override fun onResume() {
        super.onResume()
//        GlobalScope.launch {
//            val user = User("jjuntan", "18148991553")
//            UsersApplication.database.userDao().insertUser(user)
//        }
    }


}

@Composable
fun ErrorScreen(error:String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(error)
    }
}

@Composable
fun ResultScreen(result: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "dddddddddddddddddddd")
        Text(result)
    }
}

//在这里演示如何在组件中实现对User表的增删改查
@Composable
fun WordBookApp(userViewModel: UserViewModel = viewModel()) {
//    val count by mainViewModel.counterLiveData.observeAsState(0)
    //    val users by viewModel.allUsers.observeAsState(listOf())

    //这里是viewmodel提供的所有user列表的数据
    val marsViewModel: MarsViewModel = viewModel()
    val users: List<User> by userViewModel.allUsers.observeAsState(mutableListOf())
    val userList = marsViewModel.getUserList()

    //增：往数据库中插入某个user对象，可以不传id，id为主键自增
    val user_insert = User("Hello", "13333","dada")

    //查：根据id查询某个user，
//    val user_query : User= userViewModel.getUser(1)

    //改：修改某个user对象信息,需要传入主键id构造user对象
    val user_edit = User(2,"Hello","183232","pposor2",132)

    //删：删除某个id为1的user对象
//    userViewModel.DeleteUser(1)


    Column {
//        Text(text = "query_name:"+user_query.name)
//        Text(text = "query_phone:"+user_query.phoneNumber)
        Button(onClick = {     userViewModel.insert(user_insert) }) {
            Text(text = "insert")
        }
        Button(onClick = {     marsViewModel.addUser(user_insert.name,user_insert.phoneNumber) }) {
            Text(text = "remoteInsert")
        }
        Button(onClick = {     userViewModel.UpdatePositionById(id=2, position = user_edit.position) }) {
            Text(text = "updatePosition")
        }
        Button(onClick = {     userViewModel.UpdateStepById(id=2, step = user_edit.step) }) {
            Text(text = "updateStep")
        }
//        Text(user_edit.position)
        Text(users.size.toString())
        users.forEach{user ->
            Text(user.position.toString())
        }
        Text(text = userList.getOrNull(0)?.position.toString())
        Text(text = userList.size.toString())
        userList.forEach{user ->
            Text(user.username.toString())
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@Composable
fun RallyApp() {

//    val users by viewModel.allUsers.observeAsState(listOf())
    var currentScreen: RallyDestination by remember { mutableStateOf(Overview) }
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val Textvalue: String = ""

    val userViewModel: UserViewModel = viewModel()
    // Fetch your currentDestination:
    val currentDestination = currentBackStack?.destination
    if (currentDestination != null) {
        userViewModel.uiState.value.currentRoot = currentDestination.route.toString()
    }

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
                composable(route = Plant.route) {
                    PlantScreen(
                        nav01 = {
                            navController.navigate(PlantPlan.route) { launchSingleTop = true; }
                        },
                        userViewModel = userViewModel,
                        navController = navController
                    )
                }
                composable(route = Test.route) {
                    TestScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }, userViewModel = userViewModel
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
                        },
                        nav03 = {
                            navController.navigate(Plant.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav05 = {
                            navController.navigate(PlanList.route) {
                                launchSingleTop = true;
                            }
                        }
                    )
                }

                composable(route = PlanList.route) {
                    PlanListScreen(
                        nav01 = {
                            navController.navigate(SetPlanSports.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(SetPlanDrink.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav03 = {
                            navController.navigate(SetPlanSleep.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav04 = {
                            navController.navigate(SetPlanEating.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav05 = {
                            navController.navigate(SetPlanDiy.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav06={navController.popBackStack()}

                        )
                }

                composable(route = SetPlanSports.route) {
                    SetPlanSportsScreen(
                        nav01 = {navController.popBackStack()}
                    )
                }
                composable(route = SetPlanDrink.route) {
                    SetPlanDrinkScreen(
                        nav01 = {navController.popBackStack()}
                    )
                }
                composable(route = SetPlanSleep.route) {
                    SetPlanSleepScreen(nav01 = {navController.popBackStack()})
                }
                composable(route = SetPlanEating.route) {
                    SetPlanEatingScreen(nav01 = {navController.popBackStack()})
                }

                composable(route = SetPlanDiy.route) {
                    SetPlanDiyScreen(
                        nav02 = {navController.popBackStack()},
                        nav01 = {
                            //终点，返回的
                            navController.navigate(PlanListAdded.route) {
                                launchSingleTop = true; popUpTo(PlantPlan.route)
                            }
                        }, userViewModel = userViewModel


                    )
                }
                composable(route = PlanListAdded.route) {
                    PlanListAddedScreen(
                        nav01 = {
                        navController.navigate(SetPlanSports.route) { launchSingleTop = true; }
                    },
                        nav02 = {
                            navController.navigate(SetPlanDrink.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav03 = {
                            navController.navigate(SetPlanSleep.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav04 = {
                            navController.navigate(SetPlanEating.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav05 = {
                            navController.navigate(SetPlanDiy.route) {
                                launchSingleTop = true;
                            }
                        },
                        nav06 = {
                        navController.navigate(PlantPlan.route) {
                            launchSingleTop = true;popUpTo(PlantPlan.route)
                        }
                    },nav07 = {
                        navController.navigate(SetPlanDiy.route) {
                            launchSingleTop = true;popUpTo(PlantPlan.route)
                        }
                    }
                        ,userViewModel = userViewModel, nav = {},)
                }

                composable(route = ChooseSeed.route) {
                    ChooseSeed(userViewModel = userViewModel)
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
                        },
                        nav02 = {
                            navController.navigate(IslandExplore.route) {
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
                        },
                        nav03 = {
                            //导航 目的地、返回路径
                            navController.navigate(IslandDeliver.route) {
                                launchSingleTop = true; popUpTo(Island.route) {}
                            }
                        },
                        nav04 = {
                            //导航 目的地、返回路径
                            navController.navigate(IslandVisitOther.route) {
                                launchSingleTop = true; popUpTo(Island.route) {}
                            }
                        },
                        userViewModel,
                        controller = navController
                    )
                }
                composable(route = IslandExplore.route) {
                    IslandExploreScreen(
                        nav01 = {
                            navController.popBackStack()//返回到popUpTo中设置的返回路径route
                        },
                        nav02 = {
                            //导航 目的地、返回路径
                            navController.navigate(IslandNearbyMemberList.route) {
                                launchSingleTop = true; popUpTo(IslandExplore.route) {}
                            }
                        },
                        nav03 = {
                            //导航 目的地、返回路径
                            navController.navigate(IslandDeliver.route) {
                                launchSingleTop = true; popUpTo(IslandExplore.route) {}
                            }
                        },
                        nav04 = {
                            //导航 目的地、返回路径
                            navController.navigate(IslandVisitOther.route) {
                                launchSingleTop = true; popUpTo(IslandExplore.route) {}
                            }
                        },
                        userViewModel,
                        controller = navController
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
                        userViewModel = userViewModel, navController = navController
                    )
                }
                composable(route = IslandNearbyMemberList.route) {
                    IslandNearbyMemberListScreen(
                        nav01 = {
                            navController.popBackStack()
                        },
                        nav02 = {
                            navController.navigate(IslandDeliver.route) {
                                launchSingleTop = true; popUpTo(IslandNearbyMemberList.route) {}
                            }

                        },

                        userViewModel = userViewModel, navController = navController
                    )
                }
                composable(route = IslandDeliver.route) {
                    IslandDeliverScreen(
                        nav01 = {
                            navController.popBackStack()
                        },
                        userViewModel = userViewModel,
                        navController = navController
                    )
                }
                composable(
                    route = IslandVisitOther.route,
                    //接收参数方
                    arguments = listOf(navArgument("res") { type = NavType.IntType },
                        navArgument("name") { type = NavType.StringType }
                    )
                ) {
                    IslandVisitOtherScreen(
                        res = it.arguments?.getInt("res"), //传递用户头像
                        name = it.arguments?.getString("name"), //传递用户名称
                        nav01 = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(route = Message.route) {
                    MessageScreen(
                        userViewModel,
                        nav01 = {
                            navController.navigate(MessageTap.route) { launchSingleTop = true; }
                        },
                        nav03 = {
                            navController.navigate(MessageMsg.route) { launchSingleTop = true; }
                        },
                        nav04 = {
                            navController.navigate(MessagePic.route) { launchSingleTop = true; }
                        },
                        nav05 = {
                            navController.navigate(MessageFriend.route) { launchSingleTop = true; }
                        },
                        controller = navController
                    )
                }
                composable(route = MessageMsg.route) {

                    MessageMsgScreen(
                        userViewModel,
                        //导航函数
                        nav01 = {
                            navController.navigate(Message.route) { launchSingleTop = true; }
                        },
                        controller = navController
                    )
                }
                composable(route = MessageTap.route) {
                    MessageTapScreen(
                        userViewModel,
                        nav01 = {
                            navController.navigate(Message.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = MessageFriend.route) {
                    MessageFriendScreen(
                        userViewModel = userViewModel,
                        nav01 = {
                            navController.navigate(Message.route) { launchSingleTop = true; }
                        },
                        //参数提供方，添加一个navController
                        controller = navController
                    )
                }
                composable(route = MessageID.route) {
                    MessageIDScreen(
                        controller = navController
                    )
                }
                composable(route = MessagePic.route) {
                    MessagePicScreen(
                        userViewModel = userViewModel,
                        nav01 = {
                            navController.navigate(Message.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(Message.route) { launchSingleTop = true; }
                        },
                        controller = navController
                    )
                }
                composable(route = My.route) {
                    MyScreen(
                        nav01 = {
                            navController.navigate(MyCupBoard.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(HealthPast.route) { launchSingleTop = true; }
                        },
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
                composable(route = MyCupBoard.route) {
                    MyCupBoardScreen(

                    )
                }
                composable(route = HealthConclusion.route) {
                    HealthConclusionScreen(
                        nav01 = {
                            navController.navigate(HealthShare.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = ReportCard.route) {
                    HealthSumCard(
                    )
                }
                composable(route = HealthShare.route) {
                    HealthShareScreen(
                    )
                }

                composable(route = My.route) {
                    BtnArea(
                        nav01 = {
                            navController.navigate(MyCupBoard.route) { launchSingleTop = true; }
                        })
                }

                composable(route = HealthPast.route) {
                    HealthPastScreen(
//                        nav01 = {
//                            navController.navigate(MyCupBoard.route) { launchSingleTop = true; }
//                        }

                    )
                }
                composable(route = SharePost.route) {
                    SharePostScreen()
                }

            }
        }
    }

}
