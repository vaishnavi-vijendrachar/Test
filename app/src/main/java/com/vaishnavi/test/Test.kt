package com.vaishnavi.test

import android.app.Application
import com.vaishnavi.repository.ApiClient
import com.vaishnavi.repository.RemoteService
import com.vaishnavi.repository.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class Test : Application() {
    open var injectionModule = module {
        single { ApiClient().buildService }
        single { Repository() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Test)
            modules(injectionModule)
        }
    }
}
