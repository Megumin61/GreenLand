package com.example.jetpacktest02.compose

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun MyTopAppBar() {
    val showMenu = remember {
        mutableStateOf(false)
    }
    TopAppBar(
        title = {
            Text(text = "Green Island", color = Color.White)
        },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = "", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Settings, contentDescription = "", tint = Color.White)
            }
            IconButton(onClick = { showMenu.value = !showMenu.value }) {
                Icon(Icons.Default.MoreVert, contentDescription = "", tint = Color.White)
            }

            DropdownMenu(expanded = showMenu.value, onDismissRequest = { showMenu.value = false }) {
                DropdownMenuItem(onClick = {}) {
                    TextButton(onClick = { showMenu.value = false }) {
                        Text(text = "Settings", color = MaterialTheme.colors.primary)
                    }
                }
                DropdownMenuItem(onClick = {}) {
                    TextButton(onClick = { showMenu.value = false }) {
                        Text(text = "Search", color = MaterialTheme.colors.primary)
                    }
                }
            }
        }
    )
}