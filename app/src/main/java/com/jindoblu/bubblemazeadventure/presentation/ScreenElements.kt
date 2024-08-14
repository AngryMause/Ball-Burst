package com.jindoblu.bubblemazeadventure.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jindoblu.bubblemazeadventure.ui.theme.BackGroundShape


@Composable
fun ScreenAlert(modifier: Modifier, onHide: () -> Unit, text: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            20.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .border(2.dp, Color.Black, BackGroundShape)
            .background(Color.White, BackGroundShape)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(40.dp)
                .border(3.dp, shape = CircleShape, color = Color.Black)
                .clickable {
                    onHide()
                },
            tint = Color.Black
        )
    }
}

@Composable
fun AcceptAlert(modifier: Modifier, onHide: (Boolean) -> Unit, text: String) {
    Box(

        modifier = modifier
            .border(2.dp, Color.Black, BackGroundShape)
            .background(Color.White, BackGroundShape)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(40.dp)
                .clickable {
                    onHide(false)
                })
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = "Confirm",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(40.dp)
                .clickable {
                    onHide(true)
                },
            tint = Color.Black
        )
    }
}

