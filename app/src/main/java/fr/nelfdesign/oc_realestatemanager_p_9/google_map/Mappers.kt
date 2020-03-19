package fr.nelfdesign.oc_realestatemanager_p_9.google_map

import com.google.android.gms.maps.model.LatLng
import fr.nelfdesign.oc_realestatemanager_p_9.models.EstateResult

/**
 * Created by Nelfdesign at 19/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.google_map
 */

fun mapGeocodingDataToLatLong(estateResult: EstateResult) : LatLng{
    val estateFirst = estateResult.results.first()

    return LatLng(
        estateFirst.geometry.location.lat,
        estateFirst.geometry.location.lng
    )
}