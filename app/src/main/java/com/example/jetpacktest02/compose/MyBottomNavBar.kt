package com.example.jetpacktest02.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.jetpacktest02.*

data class NavItem(val index: Int, val name: String, val icon: ImageVector)


@Composable
fun MyBottomNavBar(
//    navControl: NavController,
    nav01: () -> Unit = {},
    nav02: () -> Unit = {},
    nav03: () -> Unit = {},
    nav04: () -> Unit = {},
    nav05: () -> Unit = {},

) {

    var selectedItem by remember { mutableStateOf(0) }
    val navItems = listOf(
        NavItem(0, "植物", icon = Icons.Default.Home),
        NavItem(1, "会员", icon = Icons.Default.Home),
        NavItem(2, "群岛", icon = Icons.Default.Home),
        NavItem(4, "消息", icon = Icons.Default.Home),
        NavItem(5, "我的", icon = Icons.Default.Home)
    )
    BottomAppBar(backgroundColor = MaterialTheme.colors.primary) {
        BottomNavigationItem(
            selected = selectedItem == 0,
            onClick = nav01,
            icon = { Icon(navItems[0].icon, null) },
            alwaysShowLabel = true,
            label = { Text(navItems[0].name) },
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp),

            )
        BottomNavigationItem(
            selected = selectedItem == 1,
            onClick = nav02,
            icon = { Icon(navItems[1].icon, null) },
            alwaysShowLabel = true,
            label = { Text(navItems[1].name) },
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
        )
        BottomNavigationItem(
            selected = selectedItem == 2,
            onClick = nav03,
            icon = { Icon(navItems[2].icon, null) },
            alwaysShowLabel = true,
            label = { Text(navItems[2].name) },
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
        )
        BottomNavigationItem(
            selected = selectedItem == 3,
            onClick = nav04,
            icon = { Icon(navItems[3].icon, null) },
            alwaysShowLabel = true,
            label = { Text(navItems[3].name) },
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
        )
        BottomNavigationItem(
            selected = selectedItem == 4,
            onClick = nav05,
            icon = { Icon(navItems[4].icon, null) },
            alwaysShowLabel = true,
            label = { Text(navItems[4].name) },
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp)
        )
    }
}