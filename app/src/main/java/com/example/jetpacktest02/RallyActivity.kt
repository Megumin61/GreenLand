package com.example.jetpacktest02

import MyCupBoardScreen
import MySettingScreen
import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.jetpacktest02.Entity.ZUser
import com.example.jetpacktest02.ViewModel.MarsViewModel
import com.example.jetpacktest02.ViewModel.NotificationTestViewModel
import com.example.jetpacktest02.ViewModel.UserViewModel
import com.example.jetpacktest02.compose.MyBottomNavBar
import com.example.jetpacktest02.compose.StepCounter
import com.example.jetpacktest02.screen.*
import com.example.jetpacktest02.ui.main.MessageMsgScreen
import com.example.jetpacktest02.ui.main.*
import com.example.jetpacktest02.utils.StepPremission
import com.example.jetpacktest02.utils.TimeUtil
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
fun UnityTest() {
    val context = LocalContext.current
    val intent = Intent(context, UnityPlayerActivity::class.java)
    var state2 by remember {
        mutableStateOf(false)
    }
    Button(onClick = {
        state2 = true
    }) {
        Text(text = "调用unity")
    }
    if (state2 == true) {
        context.startActivity(intent)
    }
}

@Composable
fun NotificationTest(viewModel: NotificationTestViewModel = viewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            viewModel.showNotification(context, "外卖提醒", "您好，您的外卖到了！")
        }) {
            Text(text = "创建一个新通知")
        }
        Button(onClick = {
            viewModel.updateNotification(context, "订单提醒", "您有一条新的外卖订单，请及时接单！")
        }) {
            Text(text = "更新通知")
        }
    }
}


@Composable
fun ErrorScreen(error: String) {
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
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WordBookApp(userViewModel: UserViewModel = viewModel()) {
//    val count by mainViewModel.counterLiveData.observeAsState(0)
    //    val users by viewModel.allUsers.observeAsState(listOf())
    StepPremission()
    StepCounter(userViewModel)
    //这里是viewmodel提供的所有user列表的数据
//    val marsViewModel: MarsViewModel = viewModel()

    val users: List<User> by userViewModel.allUsers.observeAsState(mutableListOf())
//    var userList = marsViewModel.getUserList()

    val user_insert = User("ajun", "1812222222", "dada")
    //如果没有数据则插入
    if (users.isEmpty()) {
        userViewModel.insert(user_insert)
    }
    val user_query: User = userViewModel.getUser(1)
    val step = user_query.step
    val weekStep = user_query.weekStep
//    val step2 = UsersApplication.database.userDao().getUserById(1).step
    //增：往数据库中插入某个user对象，可以不传id，id为主键自增

    //查：根据id查询某个user，
//    val user_query : User= userViewModel.getUser(1)

    //改：修改某个user对象信息,需要传入主键id构造user对象
    val user_edit = User(2, "Hello", "183232", "pposor2", 132)

    //删：删除某个id为1的user对象
//    userViewModel.DeleteUser(1)

    //每当步数变化，更新uiState 开机来总步数
//    LaunchedEffect(key1 = userViewModel.uiState.value.stepCounter.value, block = {
//        userViewModel.UpdateStepById(id = 1, step = userViewModel.uiState.value.stepCounter.value)
//    })

    //获取当前日期，并赋值储存至uistate
    userViewModel.uiState.value.currentDate.value = TimeUtil.getCurrentDate()

    //日期变化代表一天结束
    LaunchedEffect(key1 = userViewModel.uiState.value.currentDate.value, block = {
        //更新本周数据
        userViewModel.UpdateWeekStepById(
            1,
            weekStep + userViewModel.uiState.value.stepCounter.value - step
        )
        //每当日期变化，更新步数，此时数据库中用户数据仍为开机以来数据
        userViewModel.UpdateStepById(
            id = 1, step = userViewModel.uiState.value.stepCounter.value
        )
    })

    Column {
        Text("现在表列表里有${users.size}条数据")

//        var text1 by remember {
//            mutableStateOf("false")
//        }
////        com.DefaultCompany.MyPlant
//        Button(onClick = {
//            ApkUtils.StartLaunchAPK(UsersApplication.context,"com.DefaultCompany.MyPlant","com.unity3d.player.UnityPlayerActivity")
//
//            if(            ApkUtils.CheckApkExist(UsersApplication.context,"com.DefaultCompany.MyPlant")){
//                text1="找到了"
//                ApkUtils.StartLaunchAPK(UsersApplication.context,"com.DefaultCompany.MyPlant","com.unity3d.player.UnityPlayerActivity")
//            }else{
//                text1="没找到"
//            }
//        }) {
//            Text(text = text1)
//        }
        val context = LocalContext.current
        val intent = Intent(context, MainActivity::class.java)
        var state2 by remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            state2 = true
        }) {
            Text(text = "调用验证码")
        }
        if (state2 == true) {
            context.startActivity(intent)
        }

        Button(onClick = { userViewModel.insert(user_insert) }) {
            Text(text = "insert")
        }
//        Button(onClick = {
//            marsViewModel.addUser(
//                user_insert.name,
//                user_insert.phoneNumber
//            ); userList = marsViewModel.getUserList()
//        }) {
//            Text(text = "remoteInsert")
//        }
        Button(onClick = {
            userViewModel.UpdatePositionById(
                id = 1,
                position = user_edit.position
            )
        }) {
            Text(text = "updatePosition")
        }
        Button(onClick = {
            userViewModel.UpdateStepById(
                id = 1,
                step = userViewModel.uiState.value.stepCounter.value
            )
        }) {
            Text(text = "updateStep")
        }
//        Text(user_edit.position)
        users.forEach { user ->
            Text("step" + user.step.toString())
        }
        Text(text = "今日步数：" + users.getOrNull(0)?.step.toString())
//        Text(text = userList.size.toString())
//        userList.forEach { user ->
//            Text("username" + user.username.toString())
//        }
        Column() {
            Text(text = userViewModel.uiState.value.stepDetector.value.toString())
            Text(text = userViewModel.uiState.value.stepCounter.value.toString())
            Text(text = "现在日期：" + TimeUtil.getCurrentDate().toString())
            Text(text = "现在是周几：" + TimeUtil.getWeekStr(TimeUtil.getCurrentDate()).toString())
            //日期变化时，uistate存储的本机步数减去数据库存储的昨日本机步数即为今日步数
            Text(text = "今日步数：" + (userViewModel.uiState.value.stepCounter.value - step))
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
    val marsViewModel: MarsViewModel = viewModel()

//    userViewModel.uiState.value.userList = marsViewModel.getUserList() as MutableList<ZUser>

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
                        navController.navigate(Vip.route) {
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

                    startDestination = LoginLoading.route,


                modifier = Modifier.padding(innerPadding)

            ) {
                composable(route = Vip.route) {
                    VipScreen(
                        userViewModel = userViewModel
                    )
                }
                composable(route = Plant.route) {
                    PlantScreen(
                        userViewModel = userViewModel,
                        nav01 = {
                            navController.navigate(PlantPlan.route) { launchSingleTop = true; }
                        },                        nav02 = {
                            navController.navigate(PlantBagPossessed.route) { launchSingleTop = true; }
                        }, navController = navController
                    )
                }
                composable(route = PlantUnchosen.route) {
                    PlantUnchosenScreen(
                        userViewModel = userViewModel,
                        nav01 = {
                            navController.navigate(PlantPlan.route) { launchSingleTop = true; }
                        }, nav02 = {
                            navController.navigate(PlantBagPossessed.route) {
                                launchSingleTop = true;
                            }
                        }, nav03 = {
                            navController.navigate(ChooseSeed.route) {
                                launchSingleTop = true;
                            }
                        }, navController = navController
                    )
                }
                composable(route = Test.route) {
                    TestScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }, userViewModel = userViewModel
                    )
                }
                composable(route = LoginFront.route) {
                    LoginFrontScreen(
                        navController = navController
                    )
                }
                composable(route = PhoneLogin.route) {
                    PhoneLoginScreen(
                        navController = navController, userViewModel = userViewModel,
                        marsViewModel = marsViewModel
                    )
                }
                composable(route = AppIntroduction.route) {
                    AppIntroductionScreen(
                        navController = navController
                    )
                }
                composable(route = CreateAccount.route) {
                    CreateAccountScreen(
                        navController = navController, userViewModel = userViewModel,marsViewModel=marsViewModel
                    )
                }
                composable(route = LoginLoading.route) {
                    LoginLoadingScreen(
                        navController = navController
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
                        }, navController = navController
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
                        nav06 = {
                            navController.navigate(PlantPlan.route) {
                                launchSingleTop = true;
                            }
                        }

                    )
                }

                composable(route = SetPlanSports.route) {
                    SetPlanSportsScreen(
                        nav01 = { navController.popBackStack() }
                    )
                }
                composable(route = SetPlanDrink.route) {
                    SetPlanDrinkScreen(
                        nav01 = { navController.popBackStack() }
                    )
                }
                composable(route = SetPlanSleep.route) {
                    SetPlanSleepScreen(nav01 = { navController.popBackStack() })
                }
                composable(route = SetPlanEating.route) {
                    SetPlanEatingScreen(nav01 = { navController.popBackStack() })
                }

                composable(route = SetPlanDiy.route) {
                    SetPlanDiyScreen(
                        nav02 = { navController.popBackStack() },
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
                                launchSingleTop = true;popUpTo(PlanListAdded.route)
                            }
                        },
                        nav07 = {
                            navController.navigate(SetPlanDiy.route) {
                                launchSingleTop = true;popUpTo(PlantPlan.route)
                            }
                        },
                        userViewModel = userViewModel, nav = {},
                    )
                }

                composable(route = ChooseSeed.route) {
                    ChooseSeed(userViewModel = userViewModel,
                        nav01 = {
                            navController.navigate(Island.route) {
                                launchSingleTop = true;popUpTo(Plant.route) {}
                            }
                        },
                        nav02 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        }
                    )
                }
                composable(route = VipPage.route) {
                    VipScreen(userViewModel = userViewModel)
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
                            navController.navigate(IslandVisitMe.route) {
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
                            navController.navigate(IslandVisitMe.route) {
                                launchSingleTop = true; popUpTo(IslandExplore.route) {}
                            }
                        },
                        userViewModel,
                        controller = navController,
                        navVisitOther = {
                            navController.navigate(IslandVisitOther.route) {
                                launchSingleTop = true; popUpTo(IslandExplore.route) {}
                            }
                        },
                        marsViewModel = marsViewModel
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
                    route = IslandVisitMe.route,
                    //接收参数方
                    arguments = listOf(navArgument("res") { type = NavType.IntType },
                        navArgument("name") { type = NavType.StringType }
                    )
                ) {
                    IslandVisitMeScreen(
                        res = it.arguments?.getInt("res"), //传递用户头像
                        name = it.arguments?.getString("name"), //传递用户名称
                        nav01 = {
                            navController.popBackStack()
                        },
                        navController = navController,
                        userViewModel = userViewModel
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
                        },
                        navController = navController,
                        userViewModel = userViewModel
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
                    MyScreen(userViewModel = userViewModel,
                        nav01 = {
                            navController.navigate(MyCupBoard.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(HealthPast.route) { launchSingleTop = true; }
                        },
                        nav03 = {
                            navController.navigate(MySetting.route) { launchSingleTop = true; }
                        },
                        nav04 = {
                            navController.navigate(Vip  .route) { launchSingleTop = true; }
                        },

                        )
                }
                composable(route = PlantBagPossessed.route) {
                    PlantBagPossessedScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(PlantBagAchievement.route) {
                                launchSingleTop = true;
                            }
                        }

                    )
                }
                composable(route = PlantBagAchievement.route) {
                    PlantBagAchievementScreen(
                        nav01 = {
                            navController.navigate(Plant.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(PlantBagPossessed.route) {
                                launchSingleTop = true;
                            }
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
                        nav01 = {
                            navController.navigate(My.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(Vip.route) { launchSingleTop = true; }
                        },
                    )
                }

                composable(route = ReportCard.route) {
                    HealthSumCard(
                    )
                }
//                composable(route = HealthShare.route) {
//                    HealthShareScreen(
//                    )
//                }


                composable(route = HealthPast.route) {
                    HealthPastScreen(
                        nav01 = {
                            navController.navigate(My.route) { launchSingleTop = true; }
                        },
                        nav02 = {
                            navController.navigate(HealthShare.route) { launchSingleTop = true; }
                        },

                        )
                }

                composable(route = MySetting.route) {
                    MySettingScreen(
                        nav01 = {
                            navController.navigate(My.route) {
                                launchSingleTop = true;
                            }
                        }

                    )
                }

//                composable(route = HealthShare.route) {
//                    HealthShareScreen(
////                        nav01 = {
////                            navController.navigate(HealthShare.route) { launchSingleTop = true; }
////                        }
//
//                    )
//                }
                composable(route = SharePost.route) {
                    SharePostScreen(nav01 = {
                        navController.navigate(HealthPast.route) {
                            launchSingleTop = true;
                        }
                    })
                }
                composable(route = LightReminder.route) {
                    LightReminderScreen(
//                        navController = navController
                    )
                }


            }
        }
    }

}
