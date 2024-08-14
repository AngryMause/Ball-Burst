package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game.gameelements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jindoblu.bubblemazeadventure.R
import com.jindoblu.bubblemazeadventure.ui.theme.BackGroundShape

@Composable
fun TopGameBar(
    modifier: Modifier = Modifier,
    score: Int = 0,
    level: Int = 1,
    ballImage: Int,
    ballLife: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(10.dp)
    ) {
        Text(
            text = "Score: $score",
            color = Color.Black,
            modifier = Modifier
                .background(Color.Magenta.copy(0.3f), BackGroundShape)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Level: $level",
            color = Color.Black,
            modifier = Modifier
                .background(Color.Magenta.copy(0.3f), BackGroundShape)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.Magenta.copy(0.3f), BackGroundShape)
        ) {
            repeat(ballLife) {
                Image(
                    painter = painterResource(id = ballImage),
                    contentDescription = "Live image",
                    modifier = Modifier
                        .padding(3.dp)
                        .size(30.dp)
                )
            }
        }
    }
}