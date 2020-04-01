package fr.nelfdesign.oc_realestatemanager_p_9.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property


/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database
 */

const val DATABASE_NAME = "real_estate"

@Database(entities = [Property::class, Photo::class],
            version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    //DAO
    abstract fun propertyDao() : PropertyDao
    abstract fun photoDao() : PhotoDao

    companion object{
        @Volatile
        private var INSTANCE : fr.nelfdesign.oc_realestatemanager_p_9.database.Database? = null

        fun getDatabase(context : Context) : fr.nelfdesign.oc_realestatemanager_p_9.database.Database {
            //if the instance is not null return it
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    fr.nelfdesign.oc_realestatemanager_p_9.database.Database::class.java,
                    DATABASE_NAME
                )
                    .addCallback(FakePropertyApi.prepopulateDatabase())
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

}