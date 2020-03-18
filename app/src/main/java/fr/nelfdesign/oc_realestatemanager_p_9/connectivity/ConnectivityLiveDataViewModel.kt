package fr.nelfdesign.oc_realestatemanager_p_9.connectivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils

/**
 * Created by Nelfdesign at 15/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.connectivity
 */
class ConnectivityLiveDataViewModel(val context: Context) : ViewModel() {

    val isConnected: LiveData<Boolean> = Utils.isNetworkAvailable(context)

}
