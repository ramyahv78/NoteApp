package com.example.myapplication.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.utils.NavigationScreens

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreens.NoteAdd.toString()) {
        composable(route = NavigationScreens.NoteAdd.toString()) {
            NoteAddScreen(navController = navController)
        }
        composable(NavigationScreens.NoteList.toString()) {
            NotesList(navController = navController)
        }
    }
}
