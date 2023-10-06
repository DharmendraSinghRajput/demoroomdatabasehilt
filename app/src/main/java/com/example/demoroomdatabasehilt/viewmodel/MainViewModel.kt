package com.example.demoroomdatabasehilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.demoroomdatabasehilt.model.UserModel
import com.example.demoroomdatabasehilt.repositary.UserRepositary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( var userRepositary: UserRepositary):ViewModel() {
    val getUser: LiveData<List<UserModel>>
        get() =
        userRepositary.getUserData.flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)


     fun insertData(contact:UserModel) =viewModelScope.launch {
        userRepositary.insert(contact)
    }
    fun updateRecord(contact:UserModel) =viewModelScope.launch {
        userRepositary.updateRecord(contact)
    }

}