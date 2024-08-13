package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.menu

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Menu(openGame: () -> Unit, shop: () -> Unit) {
    val viewModel = hiltViewModel<MenuViewModel>()
    var score by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(key1 = null) {
        score = viewModel.getScore()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Point: $score", modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp), fontSize = 20.sp
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center)
                .border(3.dp, Color.Yellow.copy(alpha = 0.5f), shape = RoundedCornerShape(10.dp))
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
    }

}