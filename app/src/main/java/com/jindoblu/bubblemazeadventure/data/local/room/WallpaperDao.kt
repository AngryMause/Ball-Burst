package com.jindoblu.bubblemazeadventure.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jindoblu.bubblemazeadventure.data.model.room.BallsModel
import com.jindoblu.bubblemazeadventure.data.model.room.WalPapersModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpaperDao {
    @Query("SELECT * FROM wallpapers")
    fun getAll(): Flow<List<WalPapersModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg noteModel: WalPapersModel)

    @Query("UPDATE wallpapers SET is_bay = :isBay WHERE id = :id")
    fun updateIsBay(id: Long, isBay: Boolean)

}