package com.example.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.utils.NavigationScreens
import com.example.myapplication.viewmode.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAddScreen(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val formatter = SimpleDateFormat("dd/MMM/yyyy", Locale.US)

    val calender = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calender.timeInMillis)
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableStateOf(formatter.format(calender.timeInMillis))
    }

    val textNote = remember {
        mutableStateOf("")
    }
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    selectedDate = formatter.format(datePickerState.selectedDateMillis!!)
                }) {
                    Text(text = "Confirm")

                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }) {
                    Text(text = "Cancel")

                }
            }) {

            DatePicker(state = datePickerState)
        }
    }




    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextField(
                modifier = Modifier
                    .clickable { showDatePicker = true },
                enabled = false,
                label = { Text("Select Date") },
                value = selectedDate,
                onValueChange = { selectedDate = it },
                placeholder = {
                    Text(text = "Enter Date")

                },

                )



            TextField(
                value = textNote.value, onValueChange = { textNote.value = it },
                label = {
                    Text(
                        text = "Enter Note"
                    )
                },
                maxLines = 5,

                )

            Button(onClick = { mainViewModel.saveData(selectedDate, textNote.value) }) {
                Text(text = "Save")
            }
        }
    }
    val result = mainViewModel.notLiveData.observeAsState().value
    result?.let {
        if (it) {
            navController.navigate(NavigationScreens.NoteList.toString()) {
                popUpTo(NavigationScreens.NoteAdd.toString())
            }
            textNote.value = ""
            selectedDate = ""
        }
    }

}

