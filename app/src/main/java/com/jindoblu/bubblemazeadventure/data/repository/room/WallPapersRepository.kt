package com.jindoblu.bubblemazeadventure.data.repository.room

import com.jindoblu.bubblemazeadventure.R
import com.jindoblu.bubblemazeadventure.data.local.room.WallpaperDao
import com.jindoblu.bubblemazeadventure.data.model.room.WalPapersModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WallPapersRepository @Inject constructor(
    private val wallPapersDao: WallpaperDao
) {
    fun getAll() = wallPapersDao.getAll()

    suspend fun insertWallPapers() {
        if (wallPapersDao.getAll().first().isEmpty()) {
            wallpapersList.forEach {
                wallPapersDao.insertAll(it)
            }
        }
    }

    suspend fun getWallPapers(index: Int): Int {

        return wallPapersDao.getAll().map { it[index].walPapers }.first()
    }

    fun updateIsBay(index: Int, isBay: Boolean) {
        wallPapersDao.updateIsBay(index.toLong(), isBay)
    }
}

private val wallpapersList = listOf(
    WalPapersModel(
        id = 1,
        walPapers = R.mipmap.fiirst_back,
        isBay = false,
        price = 300,
    ),
    WalPapersModel(
        id = 2,
        walPapers = R.mipmap.second_back,
        isBay = false,
        price = 500,
    ),
    WalPapersModel(
        id = 3,
        walPapers = R.mipmap.therds_back,
        isBay = false,
        price = 700,
    ),
    WalPapersModel(
        id = 4,
        walPapers = R.mipmap.mistical_back,
        isBay = false,
        price = 1000,
    ),
)




