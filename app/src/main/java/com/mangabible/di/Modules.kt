package com.mangabible.di

import android.content.Context
import com.mangabible.data.api.ApiHelper
import com.mangabible.data.api.RetrofitBuilder
import com.mangabible.data.dao.UserDao
import com.mangabible.data.database.AppDatabase
import com.mangabible.data.repository.LoginRepositoryImpl
import com.mangabible.data.repository.MangaRepositoryImpl
import com.mangabible.data.repository.UserRepositoryImpl
import com.mangabible.ui.viewmodel.main.MainViewModel
import com.mangabible.ui.viewmodel.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ApiHelper> { RetrofitBuilder.apiHelper }
    single { MangaRepositoryImpl(get()) }
    single { LoginRepositoryImpl() }
    single { UserRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel() }
}

fun provideAppDatabase(context: Context): AppDatabase {
    return AppDatabase.getDatabase(context)
}

fun provideUserDao(appDatabase: AppDatabase): UserDao {
    return appDatabase.userDao()
}