package com.example.valentindejoue.smartiris.preferences

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "Setting"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var user: String
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = this.user
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putString(user, value)
        }

    var email: String
        get() = this.email
        set(value) = preferences.edit {
            it.putString(email, value)
        }
}