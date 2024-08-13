package com.jindoblu.bubblemazeadventure.repository.room

import com.jindoblu.bubblemazeadventure.R
import com.jindoblu.bubblemazeadventure.data.local.room.BalsDao
import com.jindoblu.bubblemazeadventure.data.model.BallBehaviorModel
import com.jindoblu.bubblemazeadventure.data.model.room.BallsModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BallsRepository @Inject constructor(
    private val ballsDao: BalsDao
) {
    fun getAll() = ballsDao.getAll()

    suspend fun insertBall() {
        if (ballsDao.getAll().first().isEmpty()) {
            ballList.forEach {
                ballsDao.insertAll(it)
            }
        }
    }
    fun updateIsBay(index: Int, isBay: Boolean) {
        ballsDao.updateIsBay(index.toLong(), isBay)
    }
}

private val ballList = listOf(
    BallsModel(
        ball = R.mipmap.first_ball,
        isBay = true,
        isSelected = true,
        price = 0,
        ballBehaviorModel = BallBehaviorModel(
            isRotated = false,
            isAnimated = false,
            isScaled = false
        )
    ),
    BallsModel(
        ball = R.mipmap.yellow_ball,
        isBay = false,
        isSelected = false,
        price = 500,
        ballBehaviorModel = BallBehaviorModel(
            isRotated = true,
            isAnimated = false,
            isScaled = false
        )
    ),
    BallsModel(
        ball = R.mipmap.blue_ball,
        isBay = false,
        isSelected = false,
        price = 800,
        ballBehaviorModel = BallBehaviorModel(
            isRotated = true,
            isAnimated = false,
            isScaled = false
        )
    ),
    BallsModel(
        ball = R.mipmap.ligth_ball,
        isBay = false,
        isSelected = false,
        price = 1000,
        ballBehaviorModel = BallBehaviorModel(
            isRotated = true,
            isAnimated = true,
            isScaled = true
        )
    ),
    BallsModel(
        ball = R.mipmap.purple_ball,
        isBay = false,
        isSelected = false,
        price = 1500,
        ballBehaviorModel = BallBehaviorModel(
            isRotated = true,
            isAnimated = true,
            isScaled = true
        )
    ),
)
