package fr.nelfdesign.oc_realestatemanager_p_9.database.repository

import androidx.lifecycle.LiveData
import fr.nelfdesign.oc_realestatemanager_p_9.database.PropertyDao
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property

/**
 * Created by Nelfdesign at 02/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database.repository
 */
class PropertyDaoRepository(val propertyDao : PropertyDao) {

    val properties = propertyDao.getAllProperties()

    fun getPropertyById(propertyId : Int) : LiveData<Property> = propertyDao.getProperty(propertyId)
}