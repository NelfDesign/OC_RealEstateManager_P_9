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

    @Query("""SELECT * FROM property 
                    WHERE type IN (:listType) AND town LIKE :town AND price BETWEEN :minPrice AND :maxPrice 
                    AND room_number BETWEEN :minRoom AND :maxRoom AND area BETWEEN :minSurface AND :maxSurface 
                    AND numberPhotos BETWEEN 0 AND :numberPhotos AND status = :sold AND entry_date >= :creationDate 
                    AND sell_date >= :soldDate AND school = :school AND hospital = :hospital AND market = :market
    """)
    fun filterPropertyWithParameters(listType : List<String>, town : String, minPrice : Long, maxPrice : Long, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                                     numberPhotos : Int , sold : String, creationDate : String, soldDate : String,
                                     school : Boolean, hospital : Boolean, market : Boolean) : LiveData<List<Property>>

    @Query("""SELECT * FROM property 
                    WHERE type IN (:listType) AND price BETWEEN :minPrice AND :maxPrice 
                    AND room_number BETWEEN :minRoom AND :maxRoom AND area BETWEEN :minSurface AND :maxSurface 
                    AND numberPhotos BETWEEN 0 AND :numberPhotos AND status = :sold AND entry_date >= :creationDate 
                    AND sell_date >= :soldDate AND school = :school AND hospital = :hospital AND market = :market
    """)
    fun filterPropertyWithNoTownParameter(listType : List<String>, minPrice : Long, maxPrice : Long, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                                     numberPhotos : Int , sold : String, creationDate : String, soldDate : String,
                                     school : Boolean, hospital : Boolean, market : Boolean) : LiveData<List<Property>>

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