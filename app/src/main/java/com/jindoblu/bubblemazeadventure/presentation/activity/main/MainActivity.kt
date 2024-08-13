package com.jindoblu.bubblemazeadventure.presentation.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.jindoblu.bubblemazeadventure.presentation.activity.main.navigation.MainActivityNavigation
import com.jindoblu.bubblemazeadventure.ui.theme.BubbleMazeAdventureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BubbleMazeAdventureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainActivityNavigation(Modifier.padding(innerPadding))
                }
            }
            BackHandler {

            }
        }
    }
}


