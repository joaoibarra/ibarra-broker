package com.joaoibarra.broker.di

import com.joaoibarra.broker.ui.property.PropertyListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { PropertyListViewModel(get()) }
}