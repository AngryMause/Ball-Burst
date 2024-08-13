package com.jindoblu.bubblemazeadventure.data.music

import android.content.Context
import android.media.MediaPlayer
import com.jindoblu.bubblemazeadventure.R
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameMusicManagerImpl @Inject constructor(
    private val context: Context,
    private val sharedServices: SharedServices
) : GlobalSoundManager, GameMusicManager {

    private val globalSound = MediaPlayer.create(context, R.raw.global_music)
    private val gameSound = MediaPlayer.create(context, R.raw.balls_game_music)
    private var globalSoundPosition = 0
    private var gameSoundPosition = 0

    override fun playGlobalSound() {
        if (!sharedServices.getSound()) return
        if (!globalSound.isPlaying) {
            globalSound.isLooping = true
            globalSound.start()
        }
    }

    override fun stopGlobalSound() {
        if (globalSound.isPlaying) {
            globalSound.stop()
        }
    }

    override fun pauseGlobalSound() {
        if (!sharedServices.getSound()) return
        globalSound.currentPosition.also { globalSoundPosition = it }
        globalSound.pause()
    }

    override fun resumeGlobalSound() {
        if (!sharedServices.getSound()) return
        globalSound.seekTo(globalSoundPosition)
        globalSound.start()
    }

    override fun playGameSound() {
        if (!sharedServices.getSound()) return
        if (!gameSound.isPlaying) {
            gameSound.isLooping = true
            gameSound.start()
        }
    }

    override fun stopGameSound() {
        if (gameSound.isPlaying) {
            gameSound.stop()
        }
    }

    override fun pauseGameSound() {
        if (!sharedServices.getSound()) return
        gameSound.currentPosition.also { gameSoundPosition = it }
        gameSound.pause()
    }

    override fun resumeGameSound() {
        if (!sharedServices.getSound()) return
        gameSound.seekTo(gameSoundPosition)
        gameSound.start()
    }
}