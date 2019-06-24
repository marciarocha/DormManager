package com.marciarocha.dormmanager.di.module

import com.marciarocha.dormmanager.ui.main.MainActivity
import com.marciarocha.dormmanager.ui.payment.PaymentActivity
import com.marciarocha.dormmanager.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [PaymentActivityModule::class])
    abstract fun bindPaymentActivity(): PaymentActivity
}