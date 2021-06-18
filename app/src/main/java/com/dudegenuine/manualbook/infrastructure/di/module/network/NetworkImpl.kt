package com.dudegenuine.manualbook.infrastructure.di.module.network

import com.dudegenuine.manualbook.BuildConfig
import com.dudegenuine.manualbook.infrastructure.di.module.network.INetwork.Companion.CONNECT_TIMEOUT
import com.dudegenuine.manualbook.infrastructure.di.module.network.INetwork.Companion.READ_TIMEOUT
import com.dudegenuine.manualbook.infrastructure.di.module.network.INetwork.Companion.WRITE_TIMEOUT
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Manual Book created by utifmd on 18/06/21.
 */
class NetworkImpl: INetwork {

    override fun builder(): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    override fun gson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    override fun client(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                // level = HttpLoggingInterceptor.Level.BODY
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            // if (BuildConfig.DEBUG){ }
        }.build()
    }

    /*
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL) //.addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()
    }
    * */
}