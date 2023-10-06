package com.example.demoroomdatabasehilt.appdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.demoroomdatabasehilt.model.UserModel
import com.example.demoroomdatabasehilt.util.ImageConverter

@Database(entities = [UserModel::class], version = 3)
@TypeConverters(ImageConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun channelDao(): ContactsDAO
}
