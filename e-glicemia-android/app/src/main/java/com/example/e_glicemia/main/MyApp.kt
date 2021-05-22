package com.example.e_glicemia.main

import android.app.Application
import com.example.e_glicemia.main.di.DataModules
import com.example.e_glicemia.main.di.ViewModelModules
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                DataModules.modules + ViewModelModules.modules
            )
        }
    }
}