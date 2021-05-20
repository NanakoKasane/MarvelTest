package com.test.marvel.ui.dashboard.di

import androidx.lifecycle.ViewModel
import com.test.marvel.di.ViewModelKey
import com.test.marvel.ui.dashboard.DashboardViewModel
import com.test.marvel.ui.dashboard.characters.list.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DashboardModule {
    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel
}