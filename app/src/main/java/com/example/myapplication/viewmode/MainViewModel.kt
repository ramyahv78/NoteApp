package com.example.myapplication.viewmode

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.DB.NoteDB
import com.example.myapplication.model.DB.NoteEntity
import com.example.myapplication.model.Repository.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NoteRepo) : ViewModel() {
    private val _noteLiveData = MutableLiveData<Boolean>(false)
    val notLiveData: LiveData<Boolean> = _noteLiveData


    fun saveData(date: String, note: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveNote(NoteEntity(date = date, note = note)).let {
                _noteLiveData.postValue(true)
            }
        }
    }

    fun getAllNotes() {
        viewModelScope.launch {
            repository.getAllNotes().let {

            }
        }
    }
}