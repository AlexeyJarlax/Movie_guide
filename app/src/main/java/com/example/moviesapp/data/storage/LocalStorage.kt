package com.example.moviesapp.data.storage

import android.content.SharedPreferences

class LocalStorage(private val sharedPreferences: SharedPreferences) {
    private companion object {
        const val FAVORITES_KEY = "FAVORITES_KEY"
    }

    fun addToFavorites(movieId: String) {
        changeFavorites(movieId = movieId, remove = false)
    }

    fun removeFromFavorites(movieId: String) {
        changeFavorites(movieId = movieId, remove = true)
    }

    fun getSavedFavorites(): Set<String> {
        return sharedPreferences.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()
    }

    private fun changeFavorites(movieId: String, remove: Boolean) {
        val mutableSet = getSavedFavorites().toMutableSet()
        val modified = if (remove) mutableSet.remove(movieId) else mutableSet.add(movieId)
        if (modified) sharedPreferences.edit().putStringSet(FAVORITES_KEY, mutableSet).apply()
    }
}