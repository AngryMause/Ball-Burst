package com.jindoblu.bubblemazeadventure.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jindoblu.bubblemazeadventure.data.model.room.BallsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BalsDao {
    @Query("SELECT * FROM balls")
    fun getAll(): Flow<List<BallsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg noteModel: BallsModel)

    @Query("UPDATE balls SET is_bay = :isBay WHERE id = :id")
    fun updateIsBay(id: Long, isBay: Boolean)

}