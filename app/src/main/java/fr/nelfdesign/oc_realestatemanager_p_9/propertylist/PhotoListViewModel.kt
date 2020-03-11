package fr.nelfdesign.oc_realestatemanager_p_9.propertylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.app.App.Companion.db
import fr.nelfdesign.oc_realestatemanager_p_9.database.repository.PhotoDaoRepository
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import java.util.concurrent.Executor

/**
 * Created by Nelfdesign at 28/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.propertylist
 */
class PhotoListViewModel(private val executor: Executor) : ViewModel() {

    private val photoRepository : PhotoDaoRepository = PhotoDaoRepository(db.PhotoDao())

    val photos : LiveData<List<Photo>> = photoRepository.photos

    fun getPhotoToDisplay(propertyId : Int) : LiveData<List<Photo>> = photoRepository.getPhotoToDisplay(propertyId)

    fun insertPhotos(photos : List<Photo>){
        executor.execute{
            /*for (p in photos){
                photoRepository.insertPhoto(p)
            }*/
            photoRepository.insertPhotos(photos)
        }
    }

    fun updatePhoto(photos : List<Photo>){
        executor.execute{
            for (p in photos){
                photoRepository.updatePhoto(p)
            }
        }
    }

    fun deletePhotos(photos : List<Photo>){
        executor.execute{
            for (p in photos){
                photoRepository.deletePhoto(p)
            }
        }
    }
}