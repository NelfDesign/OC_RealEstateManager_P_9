package fr.nelfdesign.oc_realestatemanager_p_9.database

import androidx.room.Database
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
    abstract fun PropertyDao() : PropertyDao
    abstract fun PhotoDao() : PhotoDao

}