package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleStartEffect
import com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game.gameelements.TopGameBar
import com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game.model.GameBallModel
import com.jindoblu.bubblemazeadventure.ui.OnLifecycleEvent
import com.jindoblu.bubblemazeadventure.ui.toPx


const val BALL_SIZE = 50

@Composable
fun Game(goBack: () -> Unit) {
    val viewModel = hiltViewModel<GameViewModel>()
    val gameBallModel = viewModel.getGameBallModel.collectAsState()
    var scor by remember { mutableIntStateOf(0) }
    var isGameStarted by remember { mutableStateOf(false) }
    LifecycleStartEffect(key1 = Unit) {
        Log.e("Game", "onStart")
        onStopOrDispose {
            viewModel.stopGameSound()
            if (scor <= 0) return@onStopOrDispose
            viewModel.saveScore(scor)
            Log.e("Game", "onStopOrDispose")
        }
    }
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                viewModel.playGameSound()
            }

            Lifecycle.Event.ON_PAUSE -> {
                viewModel.pauseGameSound()
            }


            else -> Unit
        }
        if (event == Lifecycle.Event.ON_STOP) {
            viewModel.saveScore(scor)
        }
    }
    var ballModel by remember {
        mutableStateOf(GameBallModel.DEF_GAME_BALL_MODEL)
    }
    val screenConfig = LocalConfiguration.current
    val scaleAnimation = animateFloatAsState(
        targetValue = if (ballModel.isScaled && isGameStarted) 1f else 0.2f,
        label = "scaleBallAnimation",
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val visibilityAnimation = animateFloatAsState(
        targetValue = if (ballModel.isAnimated && isGameStarted) 0f else 1f,
        label = "scaleBallAnimation",
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing, delayMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(key1 = gameBallModel.value.offset.y) {
        ballModel = gameBallModel.value
    }
    LaunchedEffect(key1 = null) {
        viewModel.playGameSound()
        viewModel.setScreenSize(
            screenWidth = screenConfig.screenWidthDp.toPx,
            screenHeight = screenConfig.screenHeightDp.toPx
        )
    }
    if (ballModel.image == 0) return
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = gameBallModel.value.image), contentDescription = "Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 50.dp)
                .offset {
                    ballModel.offset
                }
                .graphicsLayer {
                    if (ballModel.isScaled && isGameStarted) {
                        scaleX = scaleAnimation.value
                        scaleY = scaleAnimation.value
                    }
                    alpha = visibilityAnimation.value
                }
                .clip(CircleShape)
                .size(BALL_SIZE.dp)
                .clickable(enabled = isGameStarted && ballModel.life >= 0) {
                    if (visibilityAnimation.value <= 0.45f) return@clickable
                    scor += 1
                    viewModel.setBallState()
                }
        )

        if (!isGameStarted) {
            Icon(
                Icons.Filled.PlayArrow, contentDescription = "Start game",
                modifier = Modifier
                    .padding(10.dp)
                    .size(60.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
                    .clickable {
                        isGameStarted = true
                        viewModel.startGame()
                    },
                tint = Color.Magenta
            )
        }
        TopGameBar(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            level = 2,
            ballImage = ballModel.image,
            scor = scor,
            ballLife = ballModel.life
        )
    }

    BackHandler {
        goBack()
    }
}




