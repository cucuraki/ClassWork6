package com.example.classwork6.sharedpreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.datastore.preferences.preferencesDataStore
import com.example.classwork6.App


val Context.dataStore by preferencesDataStore(name = "settings")

object SharedPreference {
    val sharedPreferences by lazy{
        App.context?.getSharedPreferences("session", MODE_PRIVATE)
    }
    fun saveToken(token: String){
        sharedPreferences?.edit()?.putString("token", token)?.apply()
    }

    fun getToken() = sharedPreferences?.getString("token", null)

    fun saveEmail(email: String){
        sharedPreferences?.edit()?.putString("email", email)?.apply()
    }

    fun getEmail() = sharedPreferences?.getString("email", null)


}