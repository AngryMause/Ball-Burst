package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game.model

import androidx.compose.ui.unit.IntOffset

data class GameBallModel(
    val image: Int,
    val isAnimated: Boolean,
    val isScaled: Boolean,
    val isRotated: Boolean,
    val duration: Int,
    val offset: IntOffset,
    var life: Int
) {
    companion object {
        val DEF_GAME_BALL_MODEL = GameBallModel(
            0,
            isAnimated = false,
            isScaled = false,
            isRotated = false,
            duration = 0,
            offset = IntOffset.Zero,
            life = 0
        )
    }
}




