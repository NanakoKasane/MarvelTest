package com.test.marvel.ui.dashboard.di

import com.test.marvel.di.ActivityScope
import com.test.marvel.ui.dashboard.DashboardActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [DashboardModule::class])
interface DashboardComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DashboardComponent
    }

    fun inject(activity: DashboardActivity)
//    fun inject(fragment: XFragment)

}
