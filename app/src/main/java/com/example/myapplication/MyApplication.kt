package com.example.myapplication

import android.app.Application
import android.content.Context
import com.example.myapplication.di.networkModule
import com.example.myapplication.di.repositoryModule
import com.example.myapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {
  init {
    instance = this
  }

  companion object {
    private var instance: MyApplication? = null
    fun getAppContext(): Context = instance!!.applicationContext
  }

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@MyApplication)
      modules(listOf(networkModule, repositoryModule, viewModelModule))
    }

    if (BuildConfig.DEBUG)
      Timber.plant(Timber.DebugTree())
  }
}
