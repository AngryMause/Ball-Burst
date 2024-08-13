package com.jindoblu.bubblemazeadventure.data.local.shared

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SharedServices @Inject constructor(
    private val context: Context
) {
    private val sharedPref = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    private val editer = sharedPref.edit()
    private val dataStore = context.dataStore
    fun saveString(value: String) {
        editer.putString(KEY, value)
        editer.apply()
    }

    fun getString(): String? {
        return sharedPref.getString(KEY, null)
    }


    fun getScore(): Int {
        return sharedPref.getInt(SCORE, 2000)
    }

    fun saveScore(value: Int) {
        editer.putInt(SCORE, value)
        editer.apply()
    }

    fun saveSelectedBall(value: Int) {
        editer.putInt(SELECTED_BALL, value)
        editer.apply()
    }

    fun getSelectedBall(): Int {
        return sharedPref.getInt(SELECTED_BALL, 0)
    }

    fun playSound(isPlaying: Boolean) {
        editer.putBoolean(SOUND, isPlaying).apply()
    }

    val getSelectedWallpaper: Flow<Int> = dataStore.data.map { preferences ->
        preferences[wallpaperKey] ?: -1
    }

    suspend fun saveSelectedWallpaper(value: Int) {
        Log.e("Test", "saveSelectedWallpaper: $value")
        dataStore.edit { preferences ->
            preferences[wallpaperKey] = value
        }
    }

    fun getSound(): Boolean {
        return sharedPref.getBoolean(SOUND, true)
    }

    companion object {
        private const val KEY = "key"
        private const val SCORE = "score"
        private const val SELECTED_BALL = "selected_ball"
        private const val SOUND = "is_on_sound"
        private val wallpaperKey = intPreferencesKey("wallpaper")
    }


}