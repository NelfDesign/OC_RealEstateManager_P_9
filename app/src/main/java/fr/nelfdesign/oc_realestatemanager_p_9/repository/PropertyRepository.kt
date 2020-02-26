package fr.nelfdesign.oc_realestatemanager_p_9.repository

import android.app.Application
import android.content.Context
import androidx.work.*

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.repository
 */
class PropertyRepository {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun syncPropertyNow(){
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().beginUniqueWork("syncProperty", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }
}