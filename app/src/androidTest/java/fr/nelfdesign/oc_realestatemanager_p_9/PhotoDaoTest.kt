package fr.nelfdesign.oc_realestatemanager_p_9

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.nelfdesign.oc_realestatemanager_p_9.database.Database
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.utils.LiveDataTestUtils
import junit.framework.TestCase.assertTrue
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by Nelfdesign at 08/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9
 */
@RunWith(AndroidJUnit4::class)
class PhotoDaoTest {

    //For Data
    private lateinit var db : Database
    private val photo1 = Photo(1, R.drawable.manoir_de_dubourvieux.toString(), "Manoir", 1)
    private val photo2 = Photo(2, R.drawable.manoir_chambre.toString(), "Manoir chambre", 1)
    private val photo3 = Photo(3, R.drawable.manoir_cuisine.toString(), "Manoir cuisine", 2)

    private val photos = listOf(photo1, photo2, photo3)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        this.db = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .allowMainThreadQueries()
            .build()
        //Add a property to Foreign key
        this.db.PropertyDao().createProperty(Property(1,"Manoir", 1200000.0, 0.0,250, 6, 4,1,"New dream mansion by the water with a serene view of the bay and the Indian Creek golf course! Inside, a spectacular contemporary design including open and open living spaces surrounded by oversized glass walls, an interior garden, a formal dining room, a chef's kitchen + a 2nd full kitchen, a large upper living room, a sauna and high-end finishes. Exceptional exterior with an infinity pool, spa, summer kitchen and a ready roof - ideal for entertaining and dolphin watching. Deluxe Master offers an ultra-lux marble bathtub, a terrace, a dressing room and endless sunsets! Equipped with Lutron lighting and blinds, 2 car garage with elevator, lush landscape. Live in the exclusive islands of Bay Harbor, minutes from Bal Harbor!",
            R.drawable.manoir_de_dubourvieux.toString(),2, "21 jump street", "New York",0.0,0.0,false, true, false, "On sale", "24/02/2020", "","", 1, true))
        this.db.PropertyDao().createProperty(Property(2,"Manoir", 1200000.0, 0.0, 250, 6, 4,1,"New dream mansion by the water with a serene view of the bay and the Indian Creek golf course! Inside, a spectacular contemporary design including open and open living spaces surrounded by oversized glass walls, an interior garden, a formal dining room, a chef's kitchen + a 2nd full kitchen, a large upper living room, a sauna and high-end finishes. Exceptional exterior with an infinity pool, spa, summer kitchen and a ready roof - ideal for entertaining and dolphin watching. Deluxe Master offers an ultra-lux marble bathtub, a terrace, a dressing room and endless sunsets! Equipped with Lutron lighting and blinds, 2 car garage with elevator, lush landscape. Live in the exclusive islands of Bay Harbor, minutes from Bal Harbor!",
            R.drawable.manoir_de_dubourvieux.toString(), 1,"21 jump street ", "New York", 0.0,0.0,false, true, false, "On sale", "24/02/2020", "", "",2, true))
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertPhoto_WithSuccess() {
        // BEFORE : Adding photos
        this.db.PhotoDao().insertPhotos(photos)

        // TEST
        val photos : List<Photo> =  LiveDataTestUtils.getValue(this.db.PhotoDao().getAllPhotos())

        assertTrue(photos.size == 3)
    }

    @Test
    @Throws(InterruptedException::class)
    fun getPhotoForPropertyId_WithSuccess() {
        // BEFORE : Adding photos
        this.db.PhotoDao().insertPhotos(photos)
        // TEST
        val photosList =  LiveDataTestUtils.getValue(this.db.PhotoDao().getPhotosForPropertyId(1))
        val photosList2 =  LiveDataTestUtils.getValue(this.db.PhotoDao().getPhotosForPropertyId(2))

        assertTrue(photosList.size == 2)
        assertTrue(photosList2.size == 1)
    }

    @Test
    @Throws(InterruptedException::class)
    fun updatePhoto(){
        // BEFORE : Adding photos list 1
        this.db.PhotoDao().insertPhotos(photos)
        //Get photoList for property 1
        val photoList = LiveDataTestUtils.getValue(this.db.PhotoDao().getPhotosForPropertyId(1))
        val photo = photoList[0].copy(name = "Loft")
        //Update with new photo name
        this.db.PhotoDao().updatePhotos(photo)
        //Get photo updated for propertyId 1
        val photoUpdated = LiveDataTestUtils.getValue(this.db.PhotoDao().getAllPhotos())

        Assert.assertNotSame(photoList[0].name, photoUpdated[0].name)
        Assert.assertEquals("Loft", photoUpdated[0].name)
    }

    @Test
    @Throws(InterruptedException::class)
    fun deletePhoto_WithSuccess(){
        // BEFORE : Adding photos list
        this.db.PhotoDao().insertPhotos(photos)
        // delete photo with id 1
        this.db.PhotoDao().deletePhoto(photos[1])
        // get all photos
        val photosAfterDelete = LiveDataTestUtils.getValue(this.db.PhotoDao().getAllPhotos())

        assertTrue(photosAfterDelete.size == 2)
    }
}