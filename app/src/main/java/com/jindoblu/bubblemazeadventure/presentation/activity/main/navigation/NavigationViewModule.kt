package com.jindoblu.bubblemazeadventure.presentation.activity.main.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import com.jindoblu.bubblemazeadventure.data.music.GameMusicManager
import com.jindoblu.bubblemazeadventure.data.music.GlobalSoundManager
import com.jindoblu.bubblemazeadventure.repository.room.WallPapersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModule @Inject constructor(
    private val globalSoundManager: GlobalSoundManager,
    private val sharedServices: SharedServices,
    private val wallPapersRepository: WallPapersRepository
) : ViewModel() {
    private val _wallpaper = MutableStateFlow(0)
    val wallpaper = _wallpaper.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            sharedServices.getSelectedWallpaper.collect { wallpaper ->
                Log.e("Test", "Wallpaper: $wallpaper")
                if (wallpaper == -1) {
                    _wallpaper.emit(0)
                } else {
                    _wallpaper.emit(wallPapersRepository.getWallPapers(wallpaper))
                }
            }
        }
    }

    fun playGlobalSound() {
        globalSoundManager.playGlobalSound()
    }


    fun stopGlobalSound() {
        globalSoundManager.stopGlobalSound()
    }

    fun pauseGlobalSound() {
        globalSoundManager.pauseGlobalSound()
    }

    fun resumeGlobalSound() {
        globalSoundManager.resumeGlobalSound()
    }

}