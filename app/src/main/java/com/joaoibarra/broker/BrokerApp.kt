package com.joaoibarra.broker

import android.app.Application
import com.joaoibarra.broker.di.DatabaseModule
import com.joaoibarra.broker.di.NetworkModule
import com.joaoibarra.broker.di.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BrokerApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BrokerApp)
            modules(listOf(NetworkModule, DatabaseModule, ViewModule))
        }

    }
}