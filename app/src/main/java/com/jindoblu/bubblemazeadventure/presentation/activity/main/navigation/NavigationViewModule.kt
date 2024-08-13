package com.jindoblu.bubblemazeadventure.presentation.activity.main.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import com.jindoblu.bubblemazeadventure.data.music.GlobalSoundManager
import com.jindoblu.bubblemazeadventure.repository.room.WallPapersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                if (wallpaper == -1) {
                    _wallpaper.emit(0)
                } else {
                    _wallpaper.emit(wallPapersRepository.getWallPapers(wallpaper))
                }
            }
        }
    }

    fun playGlobalSound() {
        if (globalSoundManager.isGameSoundPlaying()) return
        globalSoundManager.playGlobalSound()
    }

    fun pauseGlobalSound() {
        globalSoundManager.pauseGlobalSound()
    }

    fun saveSound(isPlaying: Boolean) {
        sharedServices.playSound(isPlaying)
    }

    fun resumeGlobalSound() {
        globalSoundManager.resumeGlobalSound()
    }

}