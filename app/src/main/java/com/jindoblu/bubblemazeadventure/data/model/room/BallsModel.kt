package com.jindoblu.bubblemazeadventure.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jindoblu.bubblemazeadventure.data.model.BallBehaviorModel

@Entity(tableName = "balls")
data class BallsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "ball") val ball: Int,
    @ColumnInfo(name = "is_bay") var isBay: Boolean,
    @ColumnInfo(name = "is_selected") var isSelected: Boolean,
    @ColumnInfo(name = "price") val price: Int,
    @Embedded val ballBehaviorModel: BallBehaviorModel,
)
