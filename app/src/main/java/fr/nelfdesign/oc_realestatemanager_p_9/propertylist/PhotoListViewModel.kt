package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.app.App
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo

/**
 * Created by Nelfdesign at 28/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.propertylist
 */
class PhotoListViewModel : ViewModel() {

    val photos : LiveData<List<Photo>> = App.db.PhotoDao().getAllPhotos()

    fun getPhotoToDisplay(propertyId : Int) : LiveData<List<Photo>> = App.db.PhotoDao().getPhotosForPropertyId(propertyId)
}