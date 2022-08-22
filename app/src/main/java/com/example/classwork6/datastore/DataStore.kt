package com.example.classwork6.datastore

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.classwork6.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "settings")

object DataStore {
    private val TOKEN_KEY = stringPreferencesKey("token")
    private val EMAIL_KEY = stringPreferencesKey("email")
    private val PASSWORD_Key = stringPreferencesKey("password")
    suspend fun saveToken(token: String){
        App.context?.dataStore?.edit {
           it[TOKEN_KEY] = token
        }
    }

    val getToken = App.context?.dataStore?.data?.map{
        it[TOKEN_KEY]?:""
    }

    suspend fun save(key: String, value: String){
        val KEY = stringPreferencesKey(key)
        App.context?.dataStore?.edit {
            it[KEY] = value
        }
    }

    fun get(key: String): Flow<String>? {
        val KEY = stringPreferencesKey(key)
        return App.context?.dataStore?.data?.map{
            it[KEY]?:""
        }
    }

}