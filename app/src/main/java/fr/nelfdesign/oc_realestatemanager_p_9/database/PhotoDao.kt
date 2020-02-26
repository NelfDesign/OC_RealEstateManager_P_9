package fr.nelfdesign.oc_realestatemanager_p_9.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database
 */
@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo")
    fun getAllPhotos() : LiveData<List<Photo>>

    @Insert
    fun insertPhotos(photos : List<Photo>)

    @Update
    fun updatePhotos(photos : List<Photo>)
}