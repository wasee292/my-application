package com.example.myapplication.di

import com.example.myapplication.network.NetworkHandler
import com.example.myapplication.repository.GithubRepository
import com.example.myapplication.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
  single { NetworkHandler() }
}

val repositoryModule = module {
  single { GithubRepository(get()) }
}

val viewModelModule = module {
  viewModel { MainViewModel(get()) }
}
