package com.reggya.wings.utils

import android.content.Context

internal class LoginPreference(context: Context) {

    companion object{
        private const val KEY_PREFS = "user_pref"
        const val KEY_LOGIN_USERNAME = "username"
        private const val ISLOGIN = "false"
    }

    private val preference = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)

    fun setLogin(username : String){
        val editor = preference.edit()
        editor.putString(KEY_LOGIN_USERNAME, username)
        editor.apply()
    }

    fun getUsername() = preference.getString(KEY_LOGIN_USERNAME, "")

    fun isLogin(state : Boolean){
        val editor = preference.edit()
        editor.putBoolean(ISLOGIN, state)
            .apply()
    }

    fun getIsLogin(): Boolean{
        return preference.getBoolean(ISLOGIN, false)
    }
}