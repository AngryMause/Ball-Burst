package com.jindoblu.bubblemazeadventure.di

import android.content.Context
import androidx.room.Room
import com.jindoblu.bubblemazeadventure.data.local.room.BallsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    fun providesDataBase(context: Context) = Room.databaseBuilder(
        context,
        BallsDataBase::class.java, "balls_database"
    ).build()

    @Provides
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    fun providesNoteDao(noteDatabase: BallsDataBase) = noteDatabase.balsDao()

    @Provides
    fun providesWallpaperDao(noteDatabase: BallsDataBase) = noteDatabase.wallpapersDao()
}