package com.jindoblu.bubblemazeadventure.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallpapers")
data class WalPapersModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int ,
    @ColumnInfo(name = "wal_papers") val walPapers: Int,
    @ColumnInfo(name = "is_bay") var isBay: Boolean,
    @ColumnInfo(name = "price") val price: Int
) {
}