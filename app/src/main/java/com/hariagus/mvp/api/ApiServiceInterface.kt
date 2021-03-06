package com.hariagus.mvp.api

import com.google.gson.GsonBuilder
import com.hariagus.mvp.models.PhotosResponse
import com.hariagus.mvp.util.Constants.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import java.util.concurrent.TimeUnit

interface ApiServiceInterface {

    @GET("/photos")
    fun getPhotos(@Query("albumId")id: Int) : Observable<List<PhotosResponse>>

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