package com.example.demoroomdatabasehilt.appdatabase

import androidx.room.*
import com.example.demoroomdatabasehilt.model.UserModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insert(contact: UserModel)

    @Query("SELECT * FROM contact ORDER BY id ASC")
    fun getAllCategory(): Flow<List<UserModel>>

     @Update
     fun updateUser(contact: UserModel)

}