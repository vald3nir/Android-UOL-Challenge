package com.vald3nir.my_events.core.app

import android.app.Application
import android.content.Context
import com.vald3nir.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules

class MainApplication : Application() {

    companion object {

        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }

    private val modules by lazy {
        listOf(dataModule, appModule)
    }

    override fun onCreate() {
        super.onCreate()
        // start koin with the module list
        startKoin {
            androidContext(this@MainApplication)
            modules(modules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        unloadKoinModules(modules)
    }
}