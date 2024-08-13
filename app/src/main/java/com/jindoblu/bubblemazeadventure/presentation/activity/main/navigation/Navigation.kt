package com.jindoblu.bubblemazeadventure.presentation.activity.main.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Menu
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.game.Game
import com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.menu.Menu
import com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.settings.Settings
import com.jindoblu.bubblemazeadventure.presentation.activity.main.shop.ShopScreen
import com.jindoblu.bubblemazeadventure.ui.OnLifecycleEvent
import kotlin.random.Random


@Composable
fun MainActivityNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    val viewModule = hiltViewModel<NavigationViewModule>()
    val wallpaper = viewModule.wallpaper.collectAsState()
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                viewModule.playGlobalSound()
            }

            Lifecycle.Event.ON_RESUME -> {
                viewModule.resumeGlobalSound()
            }

            Lifecycle.Event.ON_PAUSE -> {
                viewModule.pauseGlobalSound()
            }

            else -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = NavigationState.MENU.name,
        modifier = modifier.then(
            if (wallpaper.value == 0) Modifier.background(Color.Magenta.copy(alpha = 0.3f))
            else Modifier.paint(
                painterResource(id = wallpaper.value),
                contentScale = ContentScale.FillBounds
            )
        )
    ) {
        composable(NavigationState.MENU.name) {
            Menu(
                openGame = {
                    viewModule.pauseGlobalSound()
                    navController.navigate(NavigationState.GAME.name)
                },
                openSettings = {
                    navController.navigate(NavigationState.SETTINGS.name)
                }, shop = {
                    navController.navigate(NavigationState.SHOP.name)
                })
        }
        composable(NavigationState.SHOP.name) {
            ShopScreen(
                back = {
                    navController.popBackStack()
                }, soundOnOff = { isSound ->
                    if (isSound) {
                        viewModule.resumeGlobalSound()
                    } else {
                        viewModule.pauseGlobalSound()
                    }
                }
            )
        }
        composable(NavigationState.GAME.name) {
            Game(
                goBack = {
                    navController.navigate(NavigationState.MENU.name)
                    viewModule.resumeGlobalSound()
                })
        }
        composable(NavigationState.SETTINGS.name) {
            Settings({
                navController.navigate(NavigationState.MENU.name)
            }, soundOnOff = { isSound ->
                if (isSound) {
                    viewModule.resumeGlobalSound()
                } else {
                    viewModule.pauseGlobalSound()
                }
            })
        }
    }
}





