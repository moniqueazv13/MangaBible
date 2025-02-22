package com.mangabible.di
import com.mangabible.data.api.ApiHelper
import com.mangabible.data.api.RetrofitBuilder
import com.mangabible.data.repository.LoginRepositoryImpl
import com.mangabible.data.repository.MangaRepositoryImpl
import com.mangabible.ui.viewmodel.login.LoginViewModel
import com.mangabible.ui.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ApiHelper> { RetrofitBuilder.apiHelper }
    single { MangaRepositoryImpl(get()) }
    single { LoginRepositoryImpl() }
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}