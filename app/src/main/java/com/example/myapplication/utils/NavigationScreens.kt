package com.example.myapplication.utils

sealed class NavigationScreens(route: String) {
     object NoteAdd : NavigationScreens("note_add_screen")
     object NoteList : NavigationScreens("note_list_screen")

}