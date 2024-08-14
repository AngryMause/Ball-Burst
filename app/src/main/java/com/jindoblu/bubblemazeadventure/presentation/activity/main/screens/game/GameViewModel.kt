package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import com.jindoblu.bubblemazeadventure.data.music.GameMusicManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameEngine: GameEngin,
    private val sharedServices: SharedServices,
    private val gameMusicManager: GameMusicManager
) : ViewModel() {

    val getGameBallModel = gameEngine.ballModel
    fun setScreenSize(screenWidth: Int, screenHeight: Int) {
        gameEngine.setScreenSize(screenWidth, screenHeight)
        viewModelScope.launch(Dispatchers.IO) {
            gameEngine.getGameBallModel()
        }
    }

    fun startGame() {
        viewModelScope.launch(Dispatchers.IO) {
            gameEngine.startGame()
        }
    }

    // TODO #1 fix ppoint update
    fun saveScore(score: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (score <= 4) return@launch
            val tempScore = score / 5
            sharedServices.saveScore(sharedServices.getScore() + tempScore) // every 5 score give 1 point
        }
    }

    fun setBallState() {
        viewModelScope.launch(Dispatchers.IO) {
            gameEngine.setDowngradeIntOffsetState()
        }
    }

    fun playGameSound() {
        gameMusicManager.playGameSound()
    }

    fun stopGameSound() {
        gameMusicManager.stopGameSound()
    }

    fun pauseGameSound() {
        gameMusicManager.pauseGameSound()
    }

    fun resumeGameSound() {
        gameMusicManager.resumeGameSound()
    }
}