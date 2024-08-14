package com.jindoblu.bubblemazeadventure.presentation.activity.main.shop

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jindoblu.bubblemazeadventure.R
import com.jindoblu.bubblemazeadventure.data.model.room.BallsModel
import com.jindoblu.bubblemazeadventure.data.model.room.WalPapersModel
import com.jindoblu.bubblemazeadventure.presentation.AcceptAlert
import com.jindoblu.bubblemazeadventure.presentation.ScreenAlert
import com.jindoblu.bubblemazeadventure.ui.theme.BackGroundShape
import com.jindoblu.bubblemazeadventure.ui.theme.ScreenNameTextStale

data class OnBay(var index: Int = 0, var onShow: Boolean = false, var isWallpaper: Boolean = false)

@Composable
fun ShopScreen(back: () -> Unit, soundOnOff: (boolean: Boolean) -> Unit) {
    val viewModel: ShopViewModel = hiltViewModel()
    val ballsList = viewModel.getAll().collectAsState(initial = emptyList())
    val wallpaperList = viewModel.getWallPapers().collectAsState(initial = emptyList())
    var points by remember {
        mutableIntStateOf(viewModel.getPoint())
    }
    var onShowInfo by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var isSoundOn by remember { mutableStateOf(viewModel.isSoundOn()) }
    var onShowAlert by remember { mutableStateOf(OnBay()) }
    LaunchedEffect(key1 = null) {
        isSoundOn = viewModel.isSoundOn()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Shop",
            style = ScreenNameTextStale,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(10.dp)
        )
        Text(
            text = "Points: $points",
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopStart),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Sound",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = Color.White
            )
            Icon(
                painter = painterResource(id = if (isSoundOn) R.drawable.baseline_volume_up_24 else R.drawable.baseline_volume_off_24),
                tint = Color.Magenta,
                contentDescription = "Sound on off ",
                modifier = Modifier
                    .clickable {
                        isSoundOn = !isSoundOn
                        soundOnOff(isSoundOn)
                    }
            )
        }
        if (ballsList.value.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .padding(top = 70.dp, start = 6.dp, end = 6.dp)
                    .fillMaxHeight(0.8f)
                    .border(3.dp, Color.Black, BackGroundShape)
                    .background(color = Color.Unspecified.copy(alpha = 0.4f))
                    .align(Alignment.TopStart)


            ) {
                Text(
                    text = "Levels",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth()
                        .border(3.dp, Color.Black, BackGroundShape)

                ) {
                    items(ballsList.value.size) { index ->
                        BallListItem(ballsList.value[index], level = index + 1, onBay = {
                            onShowAlert = OnBay(index = index, onShow = true, isWallpaper = false)
                        }, onSelect = {
                            viewModel.saveSelectedBall(index)
                            Toast.makeText(context, "Level Selected", Toast.LENGTH_SHORT)
                                .show()
                        })
                    }
                }
                Text(
                    text = "Wallpapers",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .border(3.dp, Color.Black, BackGroundShape)
                ) {
                    items(wallpaperList.value.size) { index ->
                        WallpaperItem(wallpaperList.value[index], onBay = {
                            onShowAlert = OnBay(index = index, onShow = true, isWallpaper = true)
                        }, onSelect = {
                            viewModel.saveSelectedWallpaper(index)
                        })
                    }
                }
            }
        }

        if (onShowAlert.onShow) {
            if (!onShowAlert.isWallpaper) {
                AcceptAlert(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.4f)
                        .align(Alignment.Center),
                    onHide = {
                        if (it) {
                            if (points >= ballsList.value[onShowAlert.index].price) {
                                points -= ballsList.value[onShowAlert.index].price
                                viewModel.byBall(onShowAlert.index)
                                viewModel.saveNewPoint(points)
                            } else {
                                onShowAlert = OnBay(index = -1, onShow = false)
                                Toast.makeText(context, "Not enough points", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } else {
                            onShowAlert = OnBay(index = -1, onShow = false)
                        }
                    },
                    text = "You are what to unlock this level?"
                )
            } else {
                AcceptAlert(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.4f)
                        .align(Alignment.Center),
                    onHide = {
                        if (!it) {
                            onShowAlert = OnBay(index = -1, onShow = false)
                        } else {
                            if (points >= wallpaperList.value[onShowAlert.index].price) {
                                points -= wallpaperList.value[onShowAlert.index].price
                                viewModel.byWallpaper(onShowAlert.index)
                                viewModel.saveNewPoint(points)
                                onShowAlert = OnBay(index = -1, onShow = false)
                            } else {
                                onShowAlert = OnBay(index = -1, onShow = false)
                                Toast.makeText(context, "Not enough points", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    },
                    text = "You are what to unlock this wallpaper?"
                )
            }
        }
        if (onShowInfo) {
            ScreenAlert(
                modifier = Modifier
                    .align(Alignment.Center),
                onHide = { onShowInfo = false },
                text = stringResource(id = R.string.shop_screen_info)
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
        back()
    }
}

@Composable
fun BallListItem(ball: BallsModel, level: Int, onBay: () -> Unit, onSelect: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Magenta.copy(alpha = 0.3f))
            .clickable(enabled = ball.isBay) {
                if (!ball.isBay) {
                    onBay()
                }

            }
    ) {
        Text(
            text = if (ball.price <= 0) "" else "Price: ${ball.price}",
            color = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopStart)
        )
        Text(
            text = "Lvl $level",
            fontSize = 13.sp,
            color = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
                .align(Alignment.TopEnd)
        )
        Image(
            painter = painterResource(id = ball.ball),
            contentDescription = "Ball",
            modifier = Modifier
                .padding(20.dp)
                .size(40.dp)
                .align(Alignment.Center)
                .clickable(enabled = ball.isBay) {
                    onSelect()
                },
            colorFilter = if (!ball.isBay) ColorFilter.tint(Color.Gray) else null
        )

        if (!ball.isBay) {
            Image(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = "Bay",
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp)
                    .align(Alignment.Center)
                    .clickable {
                        if (!ball.isBay) {
                            onBay()
                        }
                    },
                colorFilter = ColorFilter.tint(Color.Gray)
            )
        }
    }
}

@Composable
fun WallpaperItem(wallpaper: WalPapersModel, onBay: () -> Unit, onSelect: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Magenta.copy(alpha = 0.3f))
            .clickable {
                if (!wallpaper.isBay) {
                    onBay()
                }
            }
            .horizontalScroll(rememberScrollState())
    ) {
        Text(
            text = if (wallpaper.price <= 0) "" else "price: ${wallpaper.price}",
            color = Color.White,
            modifier = Modifier
                .padding(10.dp)
        )
        Image(
            painter = painterResource(id = wallpaper.walPapers),
            contentDescription = "WallPaper",
            modifier = Modifier
                .padding(20.dp)
                .size(40.dp)
                .align(Alignment.Center)
                .clickable(enabled = wallpaper.isBay) {
                    onSelect()
                },
            colorFilter = if (!wallpaper.isBay) ColorFilter.tint(Color.Gray.copy(alpha = 0.4f)) else null
        )

        if (!wallpaper.isBay) {
            Image(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = "Bay",
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp)
                    .align(Alignment.Center)
                    .clickable {
                        if (!wallpaper.isBay) {
                            onBay()
                        }
                    },
                colorFilter = ColorFilter.tint(Color.Gray)
            )
        }
    }
}