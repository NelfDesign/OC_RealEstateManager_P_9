package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.app.App
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.propertylist
 */
class PropertyListViewModel : ViewModel() {

    val properties : LiveData<List<Property>> = App.db.PropertyDao().getAllProperties()
}