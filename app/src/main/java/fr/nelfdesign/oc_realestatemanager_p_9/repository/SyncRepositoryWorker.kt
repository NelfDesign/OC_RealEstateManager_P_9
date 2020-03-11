package fr.nelfdesign.oc_realestatemanager_p_9.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import fr.nelfdesign.oc_realestatemanager_p_9.app.App
import timber.log.Timber

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.repository
 */
class SyncRepositoryWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Timber.i("Synchronize database ...")
        val propertyApi = FakePropertyApi()
        val propertyDao = App.db.PropertyDao()
        propertyDao.insertProperties(propertyApi.getAllPropertyFake())

        return Result.success()
    }
}

class SyncRepositoryPhotoWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        Timber.i("Synchronize database ...")
        val propertyApi = FakePropertyApi()
        val photoDao = App.db.PhotoDao()
        for (p in propertyApi.getAllPhotos()){
            photoDao.insertPhoto(p)
        }

        return Result.success()
    }
}

