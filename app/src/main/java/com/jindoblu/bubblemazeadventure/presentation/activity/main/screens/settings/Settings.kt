package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jindoblu.bubblemazeadventure.R
import com.jindoblu.bubblemazeadventure.data.model.room.BallsModel
import com.jindoblu.bubblemazeadventure.presentation.ScreenAlert
import com.jindoblu.bubblemazeadventure.ui.theme.BackGroundShape
import com.jindoblu.bubblemazeadventure.ui.theme.ScreenNameTextStale

@Composable
fun Settings(goBack: () -> Unit, soundOnOff: (boolean: Boolean) -> Unit) {
    val viewModel = hiltViewModel<SettingsViewModel>()

    val ballList = viewModel.getAll().collectAsState(initial = emptyList())
    var onShowInfo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.Magenta.copy(alpha = 0.3f))
    ) {
        Text(
            text = "Settings Screen",
            style = ScreenNameTextStale,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(10.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(10.dp)
                .padding(top = 60.dp)
                .background(Color.Magenta.copy(alpha = 0.3f))
                .border(3.dp, Color.Black, BackGroundShape)
                .align(Alignment.TopCenter)
        ) {
            items(ballList.value.size) { index ->
                SettingsListItem(
                    ball = ballList.value[index],
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.Center), onBallSelected = {
                        viewModel.saveSelectedBall(index)
                    }
                )
            }
        }

        if (onShowInfo) {
            ScreenAlert(
                modifier = Modifier
                    .align(Alignment.Center),
                onHide = { onShowInfo = false },
                text = stringResource(id = R.string.settings_screen_info)
            )
        }


        Icon(imageVector = Icons.Filled.Info,
            contentDescription = "Screen Info",
            modifier = Modifier
                .align(
                    Alignment.BottomStart
                )
                .padding(10.dp)
                .clickable {
                    onShowInfo = true
                })
    }
    BackHandler {
        goBack()
    }

}

@Composable
fun SettingsListItem(ball: BallsModel, modifier: Modifier, onBallSelected: (Int) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Magenta.copy(alpha = 0.3f))
    ) {
        if (ball.isBay) {
            Image(
                painter = painterResource(id = ball.ball),
                contentDescription = "Ball",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        onBallSelected(ball.id.toInt())
                    }
            )
        }
    }
}