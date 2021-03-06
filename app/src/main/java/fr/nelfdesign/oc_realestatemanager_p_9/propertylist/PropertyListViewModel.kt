package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SupportSQLiteQuery
import fr.nelfdesign.oc_realestatemanager_p_9.app.App
import fr.nelfdesign.oc_realestatemanager_p_9.app.App.Companion.db
import fr.nelfdesign.oc_realestatemanager_p_9.database.repository.PropertyDaoRepository
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import java.util.concurrent.Executor

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.propertylist
 */
class PropertyListViewModel(private val executor: Executor) : ViewModel() {

    private val repositoryProperty: PropertyDaoRepository

    init {
        val propertyDao = App.db.propertyDao()
        repositoryProperty = PropertyDaoRepository(propertyDao)
    }

    var properties: LiveData<List<Property>> = repositoryProperty.properties

    fun getPropertyById(propertyId: Long): LiveData<Property> =
        repositoryProperty.getPropertyById(propertyId)

    fun createProperty(property: Property, photos: List<Photo>) {
        executor.execute {
            val long = repositoryProperty.createProperty(property)
            for (p in photos) {
                p.propertyId = long
            }
        }
    }

    fun updateProperty(property: Property, photos: List<Photo>) {
        executor.execute {
            for (p in photos) {
                p.propertyId = property.id
            }
            repositoryProperty.updateProperty(property)
        }
    }

    /**************************************************************************************************
     * Filter method
     *************************************************************************************************/

    fun getPropertyWithFilter(query : SupportSQLiteQuery) : LiveData<List<Property>> = repositoryProperty.getPropertyWithFilter(query)
}