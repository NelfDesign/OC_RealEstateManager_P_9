package fr.nelfdesign.oc_realestatemanager_p_9.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Typeface
import android.os.Build
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
        lateinit var channelId : String
        lateinit var appContext : Context
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())

        db = Room.databaseBuilder(this, Database::class.java, DATABASE_NAME)
            .build()

        appContext = applicationContext

        channelId = "fr.nelfdesign.oc_realestatemanager_p_9.app"

        createNotificationChannel()

        //repository = PropertyRepository()
        //repository.syncPropertyNow()
        //repository.syncPhotoNow()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel notification add"
            val descriptionText = "Notification add property"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}