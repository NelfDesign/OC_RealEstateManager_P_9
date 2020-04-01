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

    fun filterPropertyWithParameters(listType : List<String>, town : String, minPrice : Double, maxPrice : Double, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                                     numberPhotos : Int, sold : String, entryDateLong: Long, soldDateLong : Long,
                                     school : Boolean, hospital: Boolean, market : Boolean) : LiveData<List<Property>> {
       return propertyDao.filterPropertyWithParameters(listType , town , minPrice, maxPrice , minRoom , maxRoom , minSurface , maxSurface ,
            numberPhotos , sold, entryDateLong, soldDateLong, school , hospital, market)
    }


    fun filterPropertyWithNoTownParameter(listType : List<String>, minPrice : Double, maxPrice : Double, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                                     numberPhotos : Int, sold : String, entryDateLong: Long, soldDateLong : Long,
                                     school : Boolean, hospital : Boolean, market : Boolean) : LiveData<List<Property>> {
        return propertyDao.filterPropertyWithNoTownParameter(listType , minPrice, maxPrice , minRoom , maxRoom , minSurface , maxSurface ,
            numberPhotos , sold, entryDateLong, soldDateLong, school, hospital , market)
    }

    fun filterTest(listType : List<String>, town :String, minPrice : Double, maxPrice : Double, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                   numberPhotos : Int , sold : String, entryDateLong: Long, soldDateLong : Long ): LiveData<List<Property>>{
        return propertyDao.filterProperty(listType, town, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface, numberPhotos, sold, entryDateLong, soldDateLong)
    }

    fun filterTestWithoutTown(listType : List<String>, minPrice : Double, maxPrice : Double, minRoom : Int, maxRoom : Int, minSurface : Int, maxSurface : Int,
                   numberPhotos : Int , sold : String, entryDateLong: Long, soldDateLong : Long ): LiveData<List<Property>>{
        return propertyDao.filterPropertyWithoutTown(listType, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface, numberPhotos, sold, entryDateLong, soldDateLong)
    }

}