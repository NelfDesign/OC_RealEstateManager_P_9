package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.nelfdesign.oc_realestatemanager_p_9.app.App
import fr.nelfdesign.oc_realestatemanager_p_9.connectivity.ConnectivityLiveDataViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.google_map.MapViewModel
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
            modelClass.isAssignableFrom(MapViewModel::class.java) -> return MapViewModel() as T
            modelClass.isAssignableFrom(ConnectivityLiveDataViewModel::class.java) -> return ConnectivityLiveDataViewModel(App.appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}