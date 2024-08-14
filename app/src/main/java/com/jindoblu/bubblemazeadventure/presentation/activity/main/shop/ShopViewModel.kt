package com.jindoblu.bubblemazeadventure.presentation.activity.main.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import com.jindoblu.bubblemazeadventure.data.repository.room.BallsRepository
import com.jindoblu.bubblemazeadventure.data.repository.room.WallPapersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val ballsRepository: BallsRepository,
    private val sharedServices: SharedServices,
    private val wallPapersRepository: WallPapersRepository
) : ViewModel() {
    fun getAll() = ballsRepository.getAll()
    fun getWallPapers() = wallPapersRepository.getAll()
    fun getPoint() = sharedServices.getScore()
    fun saveNewPoint(point: Int) {
        sharedServices.saveScore(point)
    }

    fun byWallpaper(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            wallPapersRepository.updateIsBay(position + 1, true)
        }
    }

    fun byBall(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ballsRepository.updateIsBay(position + 1, true)
        }
    }

    fun saveSelectedWallpaper(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            sharedServices.saveSelectedWallpaper(value)
        }
    }

    fun saveSelectedBall(value: Int) {
        sharedServices.saveSelectedBall(value)
    }

    fun getSelectedBall(): Int {
        return sharedServices.getSelectedBall()
    }

    fun isSoundOn(): Boolean {
        return sharedServices.getSound()
    }

}