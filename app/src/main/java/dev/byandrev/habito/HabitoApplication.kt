package dev.byandrev.habito

import android.app.Application
import dev.byandrev.habito.data.AppContainer
import dev.byandrev.habito.data.AppDataContainer

class HabitoApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}