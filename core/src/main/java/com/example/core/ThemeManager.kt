package com.example.core

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {

    private const val FILE_NAME = "ThemePrefs"
    private const val KEY_IS_NIGHT_MODE = "isNightMode"

    private var currentNightMode = -1

    fun applyTheme(activity: AppCompatActivity) {
        val prefs = activity.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val isNightMode = prefs.getBoolean(KEY_IS_NIGHT_MODE, false)

        val mode = if (isNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
        currentNightMode = mode
    }

    fun toggleTheme(activity: AppCompatActivity) {
        val prefs = activity.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val isNightMode = prefs.getBoolean(KEY_IS_NIGHT_MODE, false)

        val newNightMode = !isNightMode
        val mode = if (newNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
        currentNightMode = mode

        prefs.edit().putBoolean(KEY_IS_NIGHT_MODE, newNightMode).apply()

        activity.recreate()
    }

    fun syncTheme(activity: AppCompatActivity) {
        val prefs = activity.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val isNightMode = prefs.getBoolean(KEY_IS_NIGHT_MODE, false)
        val savedMode = if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO

        if (currentNightMode != -1 && currentNightMode != savedMode) {
            activity.recreate()
        }
    }
}