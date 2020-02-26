package fr.nelfdesign.oc_realestatemanager_p_9.app

import android.app.Application
import androidx.room.Room
import fr.nelfdesign.oc_realestatemanager_p_9.database.DATABASE_NAME
import fr.nelfdesign.oc_realestatemanager_p_9.database.Database
import fr.nelfdesign.oc_realestatemanager_p_9.repository.PropertyRepository
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Nelfdesign at 03/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.app
 */
class App : Application() {

    companion object{
        lateinit var db : Database
        lateinit var repository : PropertyRepository
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())

        db = Room.databaseBuilder(this, Database::class.java, DATABASE_NAME)
            .build()

       /* repository = PropertyRepository()
        repository.syncPropertyNow()*/
    }
}