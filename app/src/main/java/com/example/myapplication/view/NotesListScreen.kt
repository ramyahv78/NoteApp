package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.model.DB.NoteEntity
import com.example.myapplication.utils.NavigationScreens
import com.example.myapplication.viewmode.MainViewModel

@Composable
fun NotesList(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    mainViewModel.getAllNotes()

    Surface {
        MyAppBar(navController, mainViewModel)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(navController: NavController, mainViewModel: MainViewModel) {
    val listOfNotes: List<NoteEntity>? = mainViewModel.notList.observeAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notes") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(NavigationScreens.NoteAdd.toString()) }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                listOfNotes?.let {
                    LazyColumn {
                        items(items = listOfNotes, key = { note ->
                            note.id
                        }) { note ->
                            NoteCard(note)
                        }
                    }

                }

            }
        })
}

@Composable
fun NoteCard(note: NoteEntity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(5.dp)
    ) {
        Card(

            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .safeContentPadding()


        ) {
            Text(
                text = note.date,
                textAlign = TextAlign.Center,
                color = Color.Blue,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(5.dp)
            )
            Text(
                text = note.note,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(5.dp)
                    .align(AbsoluteAlignment.Left)
            )
        }
    }
}

