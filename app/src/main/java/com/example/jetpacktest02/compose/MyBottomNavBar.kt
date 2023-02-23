package com.example.jetpacktest02.compose

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetpacktest02.*
import com.example.jetpacktest02.R
import com.example.scaffolddemo.ui.theme.Green1
import com.example.scaffolddemo.ui.theme.Green2

data class NavItem(val index: Int, val name: String, val icon: Int, val active_icon: Int)

//skc 初始化
@Composable
fun MyBottomNavBar(
    navControl: NavController,
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {},
    nav05: () -> Unit = {},

    ) {

    val navBackStackEntry by navControl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navItems = listOf(
        NavItem(
            0,
            "植物",
            icon = R.drawable.g_nav_plant,
            active_icon = R.drawable.g_nav_plant_active
        ),
        NavItem(1, "会员", icon = R.drawable.g_nav_vip, active_icon = R.drawable.g_nav_vip_active),
        NavItem(
            2,
            "群岛",
            icon = R.drawable.g_nav_island,
            active_icon = R.drawable.g_nav_island_active
        ),
        NavItem(
            4,
            "消息",
            icon = R.drawable.g_nav_message,
            active_icon = R.drawable.g_nav_message_active
        ),
        NavItem(5, "我的", icon = R.drawable.g_nav_my, active_icon = R.drawable.g_nav_my_active)
    )
    if (currentDestination?.route == Plant.route ||
        currentDestination?.route == PlantUnchosen.route ||
        currentDestination?.route == Vip.route ||
        currentDestination?.route == IslandChooseIsland.route ||
        currentDestination?.route == Message.route ||
        currentDestination?.route == My.route
    ) {
        BottomAppBar(backgroundColor = Color.White) {
            val activeColor = Color(0xff49d8b7)
            val graColor = Brush.linearGradient(
                listOf(
                    Green1,
                    Green2
                )
            )
            val normalColor = Color(0xffb8c2c0)
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == Plant.route } == true,
                onClick = nav01,
                icon = {
                    if (currentDestination?.route == Plant.route ||currentDestination?.route == PlantUnchosen.route ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[0].active_icon),
                            contentDescription = null,
                            tint = activeColor
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[0].icon),
                            contentDescription = null,
                            tint = normalColor
                        )
                    }
                },
                alwaysShowLabel = true,
                label = {
                    if (currentDestination?.route == Plant.route) {
                        Text(text = "植物", color = activeColor)
                    } else {
                        Text(text = "植物", color = normalColor)
                    }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
            )
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == Vip.route } == true,
                onClick = nav02,
                icon = {
                    if (currentDestination?.route == Vip.route) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[1].active_icon),
                            contentDescription = null,
                            tint = activeColor
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[1].icon),
                            contentDescription = null,
                            tint = normalColor
                        )
                    }
                },
                alwaysShowLabel = true,
                label = {
                    if (currentDestination?.route == Vip.route) {
                        Text(text = "会员", color = activeColor)
                    } else {
                        Text(text = "会员", color = normalColor)
                    }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
            )
            BottomNavigationItem(

                selected = currentDestination?.hierarchy?.any { it.route == IslandChooseIsland.route } == true,
                onClick = nav03,
                icon = {
                    if (currentDestination?.route == IslandChooseIsland.route) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[2].active_icon),
                            contentDescription = null,
                            tint = activeColor,
                            modifier = Modifier
                                .offset(0.dp, 4.dp)
                                .padding(2.dp, 0.dp, 0.dp, 0.dp)
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[2].icon),
                            contentDescription = null,
                            tint = normalColor,
                        )
                    }
                },
                alwaysShowLabel = true,
                label = {
                    if (currentDestination?.route == IslandChooseIsland.route) {
                        Text(text = "群岛", color = activeColor)
                    } else {
                        Text(text = "群岛", color = normalColor)
                    }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
            )
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == Message.route } == true,
                onClick = nav04,
                icon = {
                    if (currentDestination?.route == Message.route) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[3].active_icon),
                            contentDescription = null,
                            tint = activeColor
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[3].icon),
                            contentDescription = null,
                            tint = normalColor
                        )
                    }
                },
                alwaysShowLabel = true,
                label = {
                    if (currentDestination?.route == Message.route) {
                        Text(text = "消息", color = activeColor)
                    } else {
                        Text(text = "消息", color = normalColor)
                    }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
            )
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == My.route } == true,
                onClick = nav05,
                icon = {
                    if (currentDestination?.route == My.route) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[4].active_icon),
                            contentDescription = null,
                            tint = activeColor
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = navItems[4].icon),
                            contentDescription = null,
                            tint = normalColor
                        )
                    }
                },
                alwaysShowLabel = true,
                label = {
                    if (currentDestination?.route == My.route) {
                        Text(text = "我的", color = activeColor)
                    } else {
                        Text(text = "我的", color = normalColor)
                    }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
            )
        }
    }

}