package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game

import androidx.compose.ui.unit.IntOffset
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game.model.GameBallModel
import com.jindoblu.bubblemazeadventure.repository.room.BallsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.Random
import javax.inject.Inject


@ViewModelScoped
class GameEngin @Inject constructor(
    private val ballsRepository: BallsRepository,
    private val sharedServices: SharedServices
) {
    private var screenHeight = 0
    private var screenWidth = 0
    private var ballCount = 3
    private var updateVelocity = 15
    private var count = 0

    fun setScreenSize(screenWidth: Int, screenHeight: Int) {
        this.screenWidth = screenWidth
        this.screenHeight = screenHeight
    }

    private val _ballModel = MutableStateFlow(GameBallModel.DEF_GAME_BALL_MODEL)
    val ballModel = _ballModel.asStateFlow()

    private val _ballState = MutableStateFlow(BallState.UPDATE_INT_OFFSET)
    private val ballState = _ballState.asStateFlow()
    suspend fun getGameBallModel() {
        if (screenWidth == 0 || screenHeight == 0) return
        ballsRepository.getAll().collectLatest {
            _ballModel.value = GameBallModel(
                it[sharedServices.getSelectedBall()].ball,
                it[sharedServices.getSelectedBall()].ballBehaviorModel.isAnimated,
                it[sharedServices.getSelectedBall()].ballBehaviorModel.isScaled,
                it[sharedServices.getSelectedBall()].ballBehaviorModel.isRotated,
                2000,
                offset = IntOffset(screenWidth / 2 - BALL_SIZE, -1),
                life = 3
            )
        }
    }


    suspend fun startGame() {
        while (ballCount >= 0) {
            when (_ballState.value) {
                BallState.UPDATE_INT_OFFSET -> {
                    update()
                }

                BallState.DOWNGRADE_INT_OFFSET -> {
                    if (count <= 10) {
                        updateVelocity += 5
                        count = 0
                    } else {
                        count++
                    }
                    downgrade()
                }

                BallState.OUT_OF_SCREEN -> {
                    outOfScreen()
                }
            }
        }
    }

    private fun outOfScreen() {
        if (_ballModel.value.offset.y <= -200) {
            resetPosition()
        } else {
            ballCount--
            _ballModel.value = _ballModel.value.copy(
                life = ballCount
            )
            resetPosition()
        }
    }

    private fun resetPosition() {
        _ballModel.value = _ballModel.value.copy(
            offset = IntOffset(
                Random().nextInt(screenWidth - BALL_SIZE),
                -300
            )
        )
        _ballState.value = BallState.UPDATE_INT_OFFSET
    }

    private suspend fun downgrade() {
        while (_ballModel.value.offset.y <= screenHeight) {
            delay(30)
            if (_ballModel.value.offset.y <= -200) {
                _ballState.value = BallState.OUT_OF_SCREEN
                return
            }
            _ballModel.value = _ballModel.value.copy(
                offset = IntOffset(
                    _ballModel.value.offset.x,
                    _ballModel.value.offset.y - 50
                )
            )
        }
    }

    suspend fun setDowngradeIntOffsetState() {
        _ballState.emit(BallState.DOWNGRADE_INT_OFFSET)
    }

    private suspend fun update() {
        if (_ballModel.value.offset.y >= screenHeight) {
            _ballState.value = BallState.OUT_OF_SCREEN
        }
        while (_ballModel.value.offset.y <= screenHeight) {
            delay(30)
            if (ballState.value == BallState.DOWNGRADE_INT_OFFSET) return
            _ballModel.value = _ballModel.value.copy(
                offset = IntOffset(
                    x = _ballModel.value.offset.x,
                    y = _ballModel.value.offset.y + updateVelocity
                )
            )
        }
    }
}


enum class BallState {
    UPDATE_INT_OFFSET,
    DOWNGRADE_INT_OFFSET,
    OUT_OF_SCREEN
}