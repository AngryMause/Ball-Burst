package com.jindoblu.bubblemazeadventure.presentation.activity.main.screens.menu

import androidx.lifecycle.ViewModel
import com.jindoblu.bubblemazeadventure.data.local.shared.SharedServices
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val sharedServices: SharedServices
):ViewModel() {
    fun getScore() = sharedServices.getScore()
}