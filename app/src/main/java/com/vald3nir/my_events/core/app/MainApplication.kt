package com.vald3nir.my_events.core.app

import android.app.Application
import android.content.Context
import com.vald3nir.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        // start koin with the module list
        startKoin {
            androidContext(this@MainApplication)
            modules(
                dataModule,
                appModule,
            )
        }
    }

    companion object {

        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}