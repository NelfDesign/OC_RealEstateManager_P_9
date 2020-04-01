package fr.nelfdesign.oc_realestatemanager_p_9.database

import android.content.ContentValues
import androidx.annotation.NonNull
import androidx.room.OnConflictStrategy
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.repository
 */

 class FakePropertyApi {

    companion object {
        fun prepopulateDatabase(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val estate1 = ContentValues()
                    estate1.put("id", 1)
                    estate1.put("type", "Manor")
                    estate1.put("price", 1200000.0)
                    estate1.put("priceEuro", 0.0)
                    estate1.put("area", 250)
                    estate1.put("room_number", 6)
                    estate1.put("bedroomNumber", 4)
                    estate1.put("bathroomNumber", 1)
                    estate1.put(
                        "description",
                        "New dream mansion by the water with a serene view of the bay and the Indian Creek golf course! Inside, a spectacular contemporary design including open and open living spaces surrounded by oversized glass walls, an interior garden, a formal dining room, a chef's kitchen + a 2nd full kitchen, a large upper living room, a sauna and high-end finishes."
                    )
                    estate1.put("photo", R.drawable.manoir_de_dubourvieux.toString())
                    estate1.put("numberPhotos", 4)
                    estate1.put("street", "655 fifth avenue")
                    estate1.put("town", "New York")
                    estate1.put("estate_lat", 40.7598568)
                    estate1.put("estate_long", -73.9781696)
                    estate1.put("hospital", false)
                    estate1.put("school", false)
                    estate1.put("market", false)
                    estate1.put("status", "On sale")
                    estate1.put("entry_date", "24/02/2020")
                    estate1.put("entry_date_long", 1582498800000)
                    estate1.put("compromise_date", "")
                    estate1.put("sell_date", "")
                    estate1.put("sell_date_long", 0)
                    estate1.put("user_id", 1)
                    estate1.put("complete", true)
                    db.insert("estate", OnConflictStrategy.IGNORE, estate1)

                    val estate2 = ContentValues()
                    estate2.put("id", 2)
                    estate2.put("type", "Penthouse")
                    estate2.put("price", 900000.0)
                    estate2.put("priceEuro", 0.0)
                    estate2.put("area", 200)
                    estate2.put("room_number", 8)
                    estate2.put("bedroomNumber", 5)
                    estate2.put("bathroomNumber", 2)
                    estate2.put(
                        "description",
                        "Located north of Miami Beach, this penthouse rises on five levels and offers an incredible number of terraces on each of its sides. To spoil nothing, a swimming pool, bar and barbecue are available on the roof. This exceptional penthouse has six bedrooms and as many bathrooms and its interior space is as large as the exterior, for a total area of 1,500 m²!"
                    )
                    estate2.put("photo", R.drawable.penthouse.toString())
                    estate2.put("numberPhotos", 5)
                    estate2.put("street", "294 Pearl Street")
                    estate2.put("town", "New York")
                    estate2.put("estate_lat", 40.7083016)
                    estate2.put("estate_long", -74.0052953)
                    estate2.put("hospital", false)
                    estate2.put("school", false)
                    estate2.put("market", false)
                    estate2.put("status", "On sale")
                    estate2.put("entry_date", "26/02/2020")
                    estate2.put("entry_date_long", 1582671600000)
                    estate2.put("compromise_date", "")
                    estate2.put("sell_date", "")
                    estate2.put("sell_date_long", 0)
                    estate2.put("user_id", 2)
                    estate2.put("complete", true)
                    db.insert("estate", OnConflictStrategy.IGNORE, estate2)

                    val estate3 = ContentValues()
                    estate3.put("id", 3)
                    estate3.put("type", "Manor")
                    estate3.put("price", 2000000.0)
                    estate3.put("priceEuro", 0.0)
                    estate3.put("area", 350)
                    estate3.put("room_number", 12)
                    estate3.put("bedroomNumber", 7)
                    estate3.put("bathroomNumber", 3)
                    estate3.put(
                        "description",
                        "Enjoy ocean and sunset views from this area of \u200B\u200B2014 Mashta Island on an oversized lot of 23,814 square feet with 138 feet of water frontage. This custom house, inspired by modern architect Richard Meier, presents pure geometric lines and integrated open spaces with an emphasis on natural light."
                    )
                    estate3.put("photo", R.drawable.manoir_salle_de_jeu.toString())
                    estate3.put("numberPhotos", 1)
                    estate3.put("street", "309 Lafayette street")
                    estate3.put("town", "Brooklyn")
                    estate3.put("estate_lat", 40.7248017)
                    estate3.put("estate_long", -73.997509)
                    estate3.put("hospital", false)
                    estate3.put("school", false)
                    estate3.put("market", false)
                    estate3.put("status", "On sale")
                    estate3.put("entry_date", "25/02/2020")
                    estate3.put("entry_date_long", 1582585200000)
                    estate3.put("compromise_date", "")
                    estate3.put("sell_date", "")
                    estate3.put("sell_date_long", 0)
                    estate3.put("user_id", 1)
                    estate3.put("complete", true)
                    db.insert("estate", OnConflictStrategy.IGNORE, estate3)

                    val picture1 = ContentValues()
                    picture1.put("id_photo", 1)
                    picture1.put("url_photo", R.drawable.manoir_de_dubourvieux.toString())
                    picture1.put("name", "Manor")
                    picture1.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture1)

                    val picture2 = ContentValues()
                    picture2.put("id_photo", 2)
                    picture2.put("url_photo", R.drawable.manoir_chambre.toString())
                    picture2.put("name", "Manor bedroom")
                    picture2.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture2)

                    val picture3 = ContentValues()
                    picture3.put("id_photo", 3)
                    picture3.put("url_photo", R.drawable.manoir_cuisine.toString())
                    picture3.put("name", "Manor kitchen")
                    picture3.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture3)

                    val picture4 = ContentValues()
                    picture4.put("id_photo", 4)
                    picture4.put("url_photo", R.drawable.manoir_salle_de_jeu.toString())
                    picture4.put("name", "Manor playroom")
                    picture4.put("property_id", 1)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture4)

                    val picture5 = ContentValues()
                    picture5.put("id_photo", 5)
                    picture5.put("url_photo", R.drawable.penthouse.toString())
                    picture5.put("name", "Penthouse")
                    picture5.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture5)

                    val picture6 = ContentValues()
                    picture6.put("id_photo", 6)
                    picture6.put("url_photo", R.drawable.penthouse_cuisine.toString())
                    picture6.put("name", "Penthouse kitchen")
                    picture6.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture6)

                    val picture7 = ContentValues()
                    picture7.put("id_photo", 7)
                    picture7.put("url_photo", R.drawable.penthouse_chambre.toString())
                    picture7.put("name", "Penthouse bedroom")
                    picture7.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture7)

                    val picture8 = ContentValues()
                    picture8.put("id_photo", 8)
                    picture8.put("url_photo", R.drawable.penthouse_chambre_2.toString())
                    picture8.put("name", "Penthouse bedroom 2")
                    picture8.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture8)

                    val picture9 = ContentValues()
                    picture9.put("id_photo", 9)
                    picture9.put("url_photo", R.drawable.penthouse_chambre_3.toString())
                    picture9.put("name", "Penthouse bedroom 3")
                    picture9.put("property_id", 2)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture9)

                    val picture10 = ContentValues()
                    picture10.put("id_photo", 10)
                    picture10.put("url_photo", R.drawable.hotel_luxury.toString())
                    picture10.put("name", "Manor luxe")
                    picture10.put("property_id", 3)
                    db.insert("photo", OnConflictStrategy.IGNORE, picture10)
                }
            }
        }
    }
}