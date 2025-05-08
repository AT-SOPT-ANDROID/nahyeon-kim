package org.sopt.at.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object UserPrefs {
    private const val PREF_NAME = "user_prefs"
    private const val KEY_USER_ID = "user_id"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserId(userId: Long) {
        prefs.edit() { putLong(KEY_USER_ID, userId) }
    }

    fun getUserId(): Long? {
        val id = prefs.getLong(KEY_USER_ID, -1L)
        return if (id != -1L) id else null
    }

    fun clearUserId() {
        prefs.edit() { remove(KEY_USER_ID) }
    }
}