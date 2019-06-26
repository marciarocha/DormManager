package com.marciarocha.dormmanager.di.module

import com.marciarocha.dormmanager.data.networking.api.exchangerates.ExchangeRatesApi
import com.marciarocha.dormmanager.data.networking.api.networkstats.NetworkStatsApi
import com.marciarocha.dormmanager.data.networking.interceptor.NetworkStatsInterceptor
import com.marciarocha.dormmanager.di.scope.PerApplication
import com.marciarocha.dormmanager.domain.interactor.networkstats.NetworkStatsInteractor
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
    fun provideNetworkStatsApi(): NetworkStatsApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://gist.githubusercontent.com/ruimendesM/cb9313c4d4b3434975a3d7a6700d1787/raw/02d17a4c542ac99fe559df360cbfe9ba24dbe6be/")
        .build()
        .create(NetworkStatsApi::class.java)

    @Provides
    @PerApplication
    fun provideExchangeRatesApi(client: OkHttpClient): ExchangeRatesApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .baseUrl("https://api.exchangeratesapi.io/")
        .build()
        .create(ExchangeRatesApi::class.java)

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
        networkStatsInterceptor: NetworkStatsInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(logging)
            .addInterceptor(networkStatsInterceptor)
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS).build()
    }

    @Provides
    @PerApplication
    fun providNetworkStatsInterceptor(networkStatsInteractor: NetworkStatsInteractor): NetworkStatsInterceptor =
        NetworkStatsInterceptor(networkStatsInteractor)


}