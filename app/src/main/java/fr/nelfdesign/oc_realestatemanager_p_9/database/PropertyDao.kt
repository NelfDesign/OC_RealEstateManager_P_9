package fr.nelfdesign.oc_realestatemanager_p_9.database

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
    fun getProperty(propertyId : Int) : LiveData<Property>

    @Insert
    fun insertProperties(properties : List<Property>)

    @Update
    fun updateProperty(property : Property)

    @Delete
    fun deleteProperty(property : Property)

}