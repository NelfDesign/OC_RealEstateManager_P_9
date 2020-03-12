package fr.nelfdesign.oc_realestatemanager_p_9.database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property

/**
 * Created by Nelfdesign at 25/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database
 */
@Dao
interface PropertyDao {

    @Query("SELECT * FROM property")
    fun getAllProperties() : LiveData<List<Property>>

    @Query("SELECT * FROM property WHERE id = :propertyId")
    fun getProperty(propertyId : Long) : LiveData<Property>

    @Insert
    fun insertProperties(properties : List<Property>)

    @Insert
    fun createProperty(properties : Property) : Long

    @Update
    fun updateProperty(property : Property) : Int

    @Delete
    fun deleteProperty(property : Property)

    // Content provider
    @Query("SELECT * FROM property")
    fun getAllPropertiesWithCursor() : Cursor

    @Query("SELECT * FROM property WHERE id = :propertyId")
    fun getPropertyByIdWithCursor(propertyId : Long) : Cursor

    @Query("DELETE FROM property WHERE id LIKE :propertyId")
    fun deletePropertyById(propertyId: Long) : Int
}