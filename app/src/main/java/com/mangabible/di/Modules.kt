package com.example.myapplication.di
import com.mangabible.data.api.ApiHelper
import com.mangabible.data.api.RetrofitBuilder
import com.mangabible.data.repository.MainRepository
import com.mangabible.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ApiHelper> { RetrofitBuilder.apiHelper }
    single { MainRepository(get()) }
    viewModel { MainViewModel(get()) }
}