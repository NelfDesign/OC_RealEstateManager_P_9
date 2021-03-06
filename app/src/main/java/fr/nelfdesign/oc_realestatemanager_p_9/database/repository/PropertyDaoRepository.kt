package fr.nelfdesign.oc_realestatemanager_p_9.database.repository

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
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

    fun getPropertyWithFilter(query: SupportSQLiteQuery) : LiveData<List<Property>> = propertyDao.getPropertyWithFilter(query)
}