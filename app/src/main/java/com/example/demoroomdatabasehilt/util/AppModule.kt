package com.example.demoroomdatabasehilt.util

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.example.demoroomdatabasehilt.appdatabase.AppDatabase
import com.example.demoroomdatabasehilt.appdatabase.ContactsDAO
import com.example.demoroomdatabasehilt.repositary.UserRepositary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(app, AppDatabase::class.java, "Contact"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideYourDao(db: AppDatabase) = db.channelDao()


   fun providesUserRepository(userDao: ContactsDAO) :UserRepositary =UserRepositary(userDao)

}