package com.bb.blockbuster

import android.app.Application
import com.facebook.stetho.Stetho

class BlockbusterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}