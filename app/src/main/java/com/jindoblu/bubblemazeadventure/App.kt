package com.jindoblu.bubblemazeadventure

import android.app.Application
import com.jindoblu.bubblemazeadventure.data.repository.room.BallsRepository
import com.jindoblu.bubblemazeadventure.data.repository.room.WallPapersRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
@OptIn(DelicateCoroutinesApi::class)
class App : Application() {
    @Inject
    lateinit var ballsRepository: BallsRepository

    @Inject
    lateinit var wallPapersRepository: WallPapersRepository

    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch(Dispatchers.IO) {
            ballsRepository.insertBall()
            wallPapersRepository.insertWallPapers()
        }
    }

}