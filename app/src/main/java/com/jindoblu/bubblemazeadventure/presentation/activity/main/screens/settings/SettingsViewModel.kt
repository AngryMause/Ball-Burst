package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.settings

import androidx.lifecycle.ViewModel
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import com.jindoblu.bubblemazeadventure.repository.room.BallsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val ballsRepository: BallsRepository,
    private val sharedServices: SharedServices
) : ViewModel() {
    fun getAll() = ballsRepository.getAll()
    fun saveSelectedBall(value: Int) {
        sharedServices.saveSelectedBall(value)
    }

    fun getSelectedBall(): Int {
        return sharedServices.getSelectedBall()
    }


}