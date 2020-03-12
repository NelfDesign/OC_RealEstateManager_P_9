package fr.nelfdesign.oc_realestatemanager_p_9

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.nelfdesign.oc_realestatemanager_p_9.database.Database
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.utils.LiveDataTestUtils
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Nelfdesign at 08/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9
 */
@RunWith(AndroidJUnit4::class)
class PropertyDaoTest {

    //For Data
    private lateinit var db : Database

    private val property1 = Property(1,"Manoir", 1200000.0, 250, 6, 4,1,"New dream mansion by the water with a serene view of the bay and the Indian Creek golf course! Inside, a spectacular contemporary design including open and open living spaces surrounded by oversized glass walls, an interior garden, a formal dining room, a chef's kitchen + a 2nd full kitchen, a large upper living room, a sauna and high-end finishes. Exceptional exterior with an infinity pool, spa, summer kitchen and a ready roof - ideal for entertaining and dolphin watching. Deluxe Master offers an ultra-lux marble bathtub, a terrace, a dressing room and endless sunsets! Equipped with Lutron lighting and blinds, 2 car garage with elevator, lush landscape. Live in the exclusive islands of Bay Harbor, minutes from Bal Harbor!",
        R.drawable.manoir_de_dubourvieux.toString(), "21 jump street, New York 10001 ", false,true,false, "On sale", "24/02/2020", null, null,1)
    private val property2 = Property(2, "Penthouse", 900000.0, 200, 8,5,2, "Located north of Miami Beach, this penthouse rises on five levels and offers an incredible number of terraces on each of its sides. To spoil nothing, a swimming pool, bar and barbecue are available on the roof. This exceptional penthouse has six bedrooms and as many bathrooms and its interior space is as large as the exterior, for a total area of 1,500 m²!",
        R.drawable.penthouse.toString(), "455 parc avenue, New York 10010", true,true,false,"On sale", "26/02/2020", null, null, 2)
    private val properties = listOf(property1, property2)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        this.db = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertProperty_WithSuccess() {
        // BEFORE : Adding properties
        this.db.PropertyDao().createProperty(property1)
        this.db.PropertyDao().createProperty(property2)

        // TEST
        val properties : List<Property> =  LiveDataTestUtils.getValue(
            this.db.PropertyDao().getAllProperties()
        )
        assertTrue(properties.size == 2)
    }

    @Test
    @Throws(InterruptedException::class)
    fun getProperty_WithSuccess() {
        // BEFORE : Adding properties
        this.db.PropertyDao().insertProperties(properties)
        // TEST
        val property : Property =  LiveDataTestUtils.getValue(
            this.db.PropertyDao().getProperty(1))
        val property2 : Property =  LiveDataTestUtils.getValue(
            this.db.PropertyDao().getProperty(2))
        assertEquals(this.property1.address, property.address)
        assertEquals(this.property2.type, property2.type)
    }

    @Test
    @Throws(InterruptedException::class)
    fun updateProperty_WithSuccess(){
        // BEFORE : Adding properties list
        this.db.PropertyDao().insertProperties(properties)
        //Get property 1
        val property1 = LiveDataTestUtils.getValue(this.db.PropertyDao().getProperty(1))
        //Update type of property
        val propertyUpdate = property1.copy(type = "Loft")
        // update property in BDD
        this.db.PropertyDao().updateProperty(propertyUpdate)
        //Get property updated
        val propertyUpdated = LiveDataTestUtils.getValue(this.db.PropertyDao().getProperty(1))

        assertNotSame(property1.type, propertyUpdated.type)
        assertEquals("Loft", propertyUpdated.type)
    }


    @Test
    @Throws(InterruptedException::class)
    fun deleteProperty_WithSuccess(){
        // BEFORE : Adding properties list
        this.db.PropertyDao().insertProperties(properties)

        // delete property with id 1
        this.db.PropertyDao().deleteProperty(property1)
        // get all properties
        val propertiesAfterDelete = LiveDataTestUtils.getValue(this.db.PropertyDao().getAllProperties())

       assertTrue(propertiesAfterDelete.size == 1)
    }
}