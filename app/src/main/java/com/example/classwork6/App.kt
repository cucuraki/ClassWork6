package com.example.classwork6

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App:  Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }
}