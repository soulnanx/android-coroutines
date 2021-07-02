package com.example.androidcoroutines

import android.app.Application
import com.example.androidcoroutines.di.viewModelModule
import com.example.data.di.databaseModule
import com.example.data.di.mapperModule
import com.example.data.di.repositoryModule
import com.example.data.di.serviceModule
import com.example.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {

        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    useCaseModule,
                    serviceModule,
                    mapperModule,
                    databaseModule
                )
            )
        }
    }
}