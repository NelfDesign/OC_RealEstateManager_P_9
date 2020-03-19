package fr.nelfdesign.oc_realestatemanager_p_9.google_map

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.models.Poi
import kotlinx.android.synthetic.main.window_map_infos.view.*

/**
 * Created by Nelfdesign at 18/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.google_map
 */
class EstateInfoWindowAdapter(context: Context) : GoogleMap.InfoWindowAdapter {

    private val contents : View = LayoutInflater.from(context).inflate(R.layout.window_map_infos, null)

    override fun getInfoContents(marker : Marker): View {
        val poi = marker.tag as Poi

        with(contents){
            title_map_window.text = poi.name
            street_map_window.text = poi.street
            if (poi.photo.contains("document")) {
                image_window.setImageURI(Uri.parse(poi.photo))
            }else{
                image_window.setImageResource(poi.photo.toInt())
            }
        }
        return contents
    }

    override fun getInfoWindow(p0: Marker?): View? = null
}