package com.dudegenuine.manualbook.feature.di.module.network

import dagger.Module
import dagger.Provides

/**
 * Manual Book created by utifmd on 18/06/21.
 */
@Module
class NetworkModule{
    /*@Provides
    fun provideNetwork(): INetwork {
        return NetworkImpl()
    }*/

    /*@Provides
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL) //.addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()
    }*/
}