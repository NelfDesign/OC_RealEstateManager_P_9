package fr.nelfdesign.oc_realestatemanager_p_9.database.repository

import androidx.lifecycle.LiveData
import fr.nelfdesign.oc_realestatemanager_p_9.database.PropertyDao
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property

/**
 * Created by Nelfdesign at 02/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database.repository
 */
class PropertyDaoRepository(private val propertyDao : PropertyDao) {

    var long : Long = 0
    val properties = propertyDao.getAllProperties()

    fun getPropertyById(propertyId : Long) : LiveData<Property> = propertyDao.getProperty(propertyId)

    fun createProperty(property : Property) : Long {
        long = propertyDao.createProperty(property)
        return long
    }

    fun updateProperty(property: Property) {
        propertyDao.updateProperty(property)
    }

    fun filterPropertyWithParameters(listType : List<String>, town : String, minPrice : Long, maxPrice : Long, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                                     numberPhotos : Int, sold : String, creationDate : String, soldDate : String,
                                     school : Boolean, hospital : Boolean, market : Boolean) : LiveData<List<Property>>
            = propertyDao.filterPropertyWithParameters(listType , town , minPrice, maxPrice , minRoom , maxRoom , minSurface , maxSurface ,
        numberPhotos , sold, creationDate, soldDate,
        school, hospital , market)

    fun filterPropertyWithNoTownParameter(listType : List<String>, minPrice : Long, maxPrice : Long, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                                     numberPhotos : Int, sold : String, creationDate : String, soldDate : String,
                                     school : Boolean, hospital : Boolean, market : Boolean) : LiveData<List<Property>>
            = propertyDao.filterPropertyWithNoTownParameter(listType , minPrice, maxPrice , minRoom , maxRoom , minSurface , maxSurface ,
        numberPhotos , sold, creationDate, soldDate,
        school, hospital , market)
}