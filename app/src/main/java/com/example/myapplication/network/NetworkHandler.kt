package com.example.myapplication.network

import com.example.myapplication.BuildConfig
import com.example.myapplication.api.GithubService
import com.example.myapplication.utils.Constant.GITHUB_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkHandler {
  companion object {
    val gson: Gson by lazy {
      GsonBuilder().setLenient()
        .create()
    }
  }

  private val okHttpClient by lazy {
    OkHttpClient.Builder()
      .apply {
        if (BuildConfig.DEBUG) {
          val httpLoggingInterceptor = HttpLoggingInterceptor()
          addInterceptor(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
          })
        }
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
      }
      .build()
  }

  private fun provideService(baseUrl: String): Retrofit {
    return Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
      .client(okHttpClient)
      .build()
  }

  val githubApi by lazy {
    provideService(GITHUB_BASE_URL).create(GithubService::class.java)
  }
}
