package com.marciarocha.dormmanager.di.module

import com.marciarocha.dormmanager.ui.splash.SplashScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector(modules = [SplashScreenModule::class])
    abstract fun bindSplashActivity(): SplashScreen
}