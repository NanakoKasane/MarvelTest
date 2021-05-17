package com.test.marvel.di

import android.content.Context
import com.test.marvel.ui.dashboard.di.DashboardComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [ViewModelBuilderModule::class, AppSubcomponents::class, StorageModule::class, NetworkModule::class, EnvironmentModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun dashboardComponent(): DashboardComponent.Factory

}