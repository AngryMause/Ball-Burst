package com.jindoblu.bubblemazeadventure.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jindoblu.bubblemazeadventure.data.model.room.BallsModel
import com.jindoblu.bubblemazeadventure.data.model.room.WalPapersModel

@Database(entities = [BallsModel::class, WalPapersModel::class], version = 1, exportSchema = false)
abstract class BallsDataBase : RoomDatabase() {

    abstract fun balsDao(): BalsDao
    abstract fun wallpapersDao(): WallpaperDao
}