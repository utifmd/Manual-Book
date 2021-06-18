package com.dudegenuine.manualbook.infrastructure.di.module.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Manual Book created by utifmd on 18/06/21.
 */
interface INetwork {
    fun builder(): Retrofit.Builder
    fun gson(): Gson
    fun client(): OkHttpClient

    companion object {
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 1L
        const val READ_TIMEOUT = 20L
    }
}