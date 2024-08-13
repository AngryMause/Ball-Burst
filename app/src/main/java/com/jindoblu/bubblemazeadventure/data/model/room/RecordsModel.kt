package com.jindoblu.bubblemazeadventure.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jindoblu.bubblemazeadventure.data.model.BallBehaviorModel

@Entity(tableName = "records")
data class RecordsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "ball") val ball: Int,
    @ColumnInfo(name = "is_bay") val isBay: Boolean,
    @ColumnInfo(name = "is_selected") val isSelected: Boolean,
    )
