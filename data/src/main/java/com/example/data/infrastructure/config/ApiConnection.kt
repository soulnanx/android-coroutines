package com.example.data.infrastructure.config

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConnection {

    fun <S> create(
        host: String,
        serviceClass: Class<S>
    ): S {
        return Retrofit.Builder()
            .baseUrl(host)
            .client(buildClient())
            .addConverterFactory(getConverterFactory())
            .build()
            .create(serviceClass)
    }

    private fun getConverterFactory() =
        GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create())

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
//            .addInterceptor(CurlLoggerInterceptor())
//            .apply {
//                if (BuildConfig.DEBUG) this.addInterceptor(HeaderInterceptor())
//            }
            .build()
    }
}