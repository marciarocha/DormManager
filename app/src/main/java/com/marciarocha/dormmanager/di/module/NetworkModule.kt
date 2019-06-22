package com.marciarocha.dormmanager.di.module

import com.marciarocha.dormmanager.data.networking.ExchangeRatesApi
import com.marciarocha.dormmanager.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideApi(retrofit: Retrofit): ExchangeRatesApi = retrofit.create(ExchangeRatesApi::class.java)

    @Provides
    @PerApplication
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .baseUrl("https://api.exchangeratesapi.io/")
        .build()

    @Provides
    @PerApplication
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .readTimeout(25, TimeUnit.SECONDS)
        .connectTimeout(25, TimeUnit.SECONDS)
        .writeTimeout(25, TimeUnit.SECONDS).build()
}