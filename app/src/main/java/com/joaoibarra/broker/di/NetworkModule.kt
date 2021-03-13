package com.joaoibarra.broker.di

import com.joaoibarra.broker.data.remote.BrokerApi
import com.joaoibarra.broker.data.remote.BrokerOkHttpClient
import com.joaoibarra.broker.data.remote.BrokerRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val NetworkModule = module {
    single { BrokerApi.newInstance(get()) }
    single { BrokerRetrofit.newInstance(get()) }
    single { BrokerOkHttpClient.newInstance(androidContext())}
}