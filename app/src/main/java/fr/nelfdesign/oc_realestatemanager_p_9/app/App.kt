package fr.nelfdesign.oc_realestatemanager_p_9.app

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Nelfdesign at 03/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.app
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}