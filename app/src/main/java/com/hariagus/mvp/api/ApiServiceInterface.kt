package com.hariagus.mvp.api

import com.google.gson.GsonBuilder
import com.hariagus.mvp.util.Constants.BASE_URL
import com.hariagus.mvp.util.DateType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

interface ApiServiceInterface {


    companion object Factory {
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .registerTypeAdapter(Date::class.java, DateType())
                            .setLenient().create()
                    )
                )
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .readTimeout(120, TimeUnit.SECONDS)
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .build()
                )
                .build()
        }

        fun create(): ApiServiceInterface {
            return retrofit.create(ApiServiceInterface::class.java)
        }
    }

}