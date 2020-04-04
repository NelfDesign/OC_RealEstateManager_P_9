package fr.nelfdesign.oc_realestatemanager_p_9.database.repository

import androidx.lifecycle.LiveData
import fr.nelfdesign.oc_realestatemanager_p_9.database.PhotoDao
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo

/**
 * Created by Nelfdesign at 02/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.database.repository
 */
class PhotoDaoRepository(private val photoDao: PhotoDao) {

    val photos : LiveData<List<Photo>> = photoDao.getAllPhotos()

    fun getPhotoToDisplay(propertyId : Long) : LiveData<List<Photo>> = photoDao.getPhotosForPropertyId(propertyId)

    fun insertPhotos(photos : List<Photo>) = photoDao.insertPhotos(photos)

    fun updatePhoto(photo : Photo) = photoDao.updatePhotos(photo)

    fun deletePhoto(photo : Photo) = photoDao.deletePhoto(photo)
}