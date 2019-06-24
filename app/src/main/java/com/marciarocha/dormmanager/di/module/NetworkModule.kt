package com.marciarocha.dormmanager.di.module

import android.content.Context
import com.marciarocha.dormmanager.data.networking.api.ExchangeRatesApi
import com.marciarocha.dormmanager.data.networking.interceptor.ConnectivityInterceptor
import com.marciarocha.dormmanager.data.networking.interceptor.NetworkStatsInterceptor
import com.marciarocha.dormmanager.data.networking.interceptor.NetworkUtil
import com.marciarocha.dormmanager.di.qualifier.ApplicationContext
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
    fun provideApi(retrofit: Retrofit): ExchangeRatesApi = retrofit.create(
        ExchangeRatesApi::class.java
    )

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
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        connectivityInterceptor: ConnectivityInterceptor,
        networkStatsInterceptor: NetworkStatsInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(logging)
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(networkStatsInterceptor)
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS).build()
    }

    @Provides
    @PerApplication
    fun provideConnectivityInterceptor(networkUtil: NetworkUtil): ConnectivityInterceptor =
        ConnectivityInterceptor(networkUtil)

    @Provides
    @PerApplication
    fun provideNetWorkUtil(@ApplicationContext applicationContext: Context): NetworkUtil =
        NetworkUtil(applicationContext)

    @Provides
    @PerApplication
    fun providNetworkStatsInterceptor(): NetworkStatsInterceptor = NetworkStatsInterceptor()


}