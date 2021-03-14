package com.joaoibarra.broker

import android.app.Application
import com.joaoibarra.broker.di.NetworkModule
import com.joaoibarra.broker.di.RoomInstrumentalTestModule
import com.joaoibarra.broker.di.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApp)
            modules(listOf(NetworkModule, RoomInstrumentalTestModule, ViewModule))
        }
    }
}