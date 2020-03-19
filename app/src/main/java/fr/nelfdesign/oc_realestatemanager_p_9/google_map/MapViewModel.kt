package fr.nelfdesign.oc_realestatemanager_p_9.google_map

import androidx.lifecycle.ViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.app.App
import fr.nelfdesign.oc_realestatemanager_p_9.models.EstateResult
import fr.nelfdesign.oc_realestatemanager_p_9.models.Poi
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils
import kotlinx.android.synthetic.main.activity_addproperty.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Nelfdesign at 17/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.google_map
 */
class MapViewModel : ViewModel() {

    /**
     * generate list Poi with property list
     *
     * @param properties       list
     * @return list of poi
     */
    fun generatePoi(properties : List<Property>) : MutableList<Poi> {
        val poiList: MutableList<Poi> = mutableListOf()

        for (estate in properties) {
            val p = Poi(
                name = estate.type,
                street = estate.street,
                photo = estate.photo,
                lat = estate.estateLat!!,
                long = estate.estateLong!!,
                estateId = estate.id
            )
            poiList.add(p)
        }
        return poiList
    }
}