package fr.nelfdesign.oc_realestatemanager_p_9.google_map

import fr.nelfdesign.oc_realestatemanager_p_9.BuildConfig
import fr.nelfdesign.oc_realestatemanager_p_9.models.EstateResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nelfdesign at 19/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.google_map
 */
interface AddressCoordinateApiService {

    @GET("json?")
    fun getCoordinateFromAddress(@Query("address") address : String,
                                    @Query("key") apiKey : String = BuildConfig.google_maps_key) : Call<EstateResult>
}