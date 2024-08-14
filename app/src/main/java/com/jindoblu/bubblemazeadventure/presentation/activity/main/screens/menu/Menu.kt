package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.menu

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jindoblu.bubblemazeadventure.R
import com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game.BALL_SIZE
import com.jindoblu.bubblemazeadventure.ui.toPx
import java.util.Random

@Composable
fun Menu(openGame: () -> Unit, shop: () -> Unit) {
    val viewModel = hiltViewModel<MenuViewModel>()
    var score by remember {
        mutableIntStateOf(0)
    }
    var isAnimationStarted by remember {
        mutableStateOf(false)
    }
    val offsetAnimation = remember {
        mutableStateOf(IntOffset.Zero)
    }
    val config = LocalConfiguration.current
    val animation = animateIntOffsetAsState(
        targetValue = if (isAnimationStarted) offsetAnimation.value.copy(
            x = Random().nextInt(
                config.screenWidthDp.toPx - BALL_SIZE
            ),
            y = config.screenHeightDp.toPx - 60
        ) else offsetAnimation.value.copy(
            x = Random().nextInt(
                config.screenWidthDp.toPx - BALL_SIZE
            )
        ), label = "",
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(300)
        )
    )

    LaunchedEffect(key1 = null) {
        score = viewModel.getScore()
        isAnimationStarted = true
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Point: $score", color = Color.White, modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp), fontSize = 20.sp
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center)
                .border(
                    3.dp,
                    Color.Yellow.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(20.dp)

        ) {
            Button(
                onClick = openGame,
                modifier = Modifier.size(120.dp, 40.dp),
                colors = ButtonDefaults.buttonColors().copy(Color.Cyan.copy(alpha = 0.5f))
            ) {
                Text("Game", color = Color.Black)
            }
            Button(
                onClick = shop,
                modifier = Modifier.size(120.dp, 40.dp),
                colors = ButtonDefaults.buttonColors().copy(Color.Cyan.copy(alpha = 0.5f))
            ) {
                Text("Shop", color = Color.Black)
            }
        }
        Image(
            painter = painterResource(id = R.mipmap.first_ball),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .offset { animation.value }
                .clickable {
                    isAnimationStarted = !isAnimationStarted
                }

        )
    }

}