package fr.nelfdesign.oc_realestatemanager_p_9.database.repository

import androidx.lifecycle.LiveData
import fr.nelfdesign.oc_realestatemanager_p_9.app.App
import fr.nelfdesign.oc_realestatemanager_p_9.database.PhotoDao
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import java.util.concurrent.Executor

/**
 * Created by Nelfdesign at 02/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database.repository
 */
class PhotoDaoRepository(val photoDao: PhotoDao) {

    val photos : LiveData<List<Photo>> = photoDao.getAllPhotos()

    fun getPhotoToDisplay(propertyId : Int) : LiveData<List<Photo>> = photoDao.getPhotosForPropertyId(propertyId)

    fun insertPhoto(photos : List<Photo>) = photoDao.insertPhotos(photos)
}