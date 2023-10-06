package com.example.demoroomdatabasehilt.repositary

import androidx.annotation.WorkerThread
import com.example.demoroomdatabasehilt.appdatabase.ContactsDAO
import com.example.demoroomdatabasehilt.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositary  @Inject constructor( var contactsDAO:ContactsDAO) {

    @WorkerThread
    suspend fun insert(user:UserModel) = withContext(Dispatchers.IO){
        contactsDAO.insert(user)
    }
    var getUserData: Flow<List<UserModel>> = contactsDAO.getAllCategory()


    @WorkerThread
    suspend fun updateRecord(user:UserModel) = withContext(Dispatchers.IO){
        contactsDAO.updateUser(user)
    }
}