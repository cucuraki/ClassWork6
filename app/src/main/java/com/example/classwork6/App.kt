package com.example.classwork6

import android.app.Application
import android.content.Context

class App:  Application() {
    companion object{
        var context: Context? = null
    }
}