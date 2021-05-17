package com.test.marvel.di

import com.test.marvel.ui.dashboard.di.DashboardComponent
import dagger.Module

@Module(subcomponents = [DashboardComponent::class, DashboardComponent::class])
class AppSubcomponents