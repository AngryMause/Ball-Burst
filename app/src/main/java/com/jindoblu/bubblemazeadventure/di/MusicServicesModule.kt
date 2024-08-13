package com.jindoblu.bubblemazeadventure.di

import com.jindoblu.bubblemazeadventure.data.music.GameMusicManager
import com.jindoblu.bubblemazeadventure.data.music.GameMusicManagerImpl
import com.jindoblu.bubblemazeadventure.data.music.GlobalSoundManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface MusicServicesModule {

    @Binds
    fun bindGameMusicManager(gameMusicManager: GameMusicManagerImpl): GameMusicManager

    @Binds
    fun bindGlobalSoundManager(gameMusicManager: GameMusicManagerImpl): GlobalSoundManager
}