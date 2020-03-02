package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.Executor


/**
 * Created by Nelfdesign at 02/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.propertylist
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val executor : Executor) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(PropertyListViewModel::class.java) -> return PropertyListViewModel( executor) as T
            modelClass.isAssignableFrom(PhotoListViewModel::class.java) -> return PhotoListViewModel( executor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}