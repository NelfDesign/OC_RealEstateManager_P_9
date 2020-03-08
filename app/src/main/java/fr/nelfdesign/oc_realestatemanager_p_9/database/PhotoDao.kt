package fr.nelfdesign.oc_realestatemanager_p_9.database

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database
 */
@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo")
    fun getAllPhotos() : LiveData<List<Photo>>

    @Query("""SELECT * FROM photo
                    JOIN property ON property.id = property_id
                    WHERE property.id = :propertyId
    """)
    fun getPhotosForPropertyId(propertyId : Int) : LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos : List<Photo>)

    @Update
    fun updatePhotos(photos : List<Photo>)
}