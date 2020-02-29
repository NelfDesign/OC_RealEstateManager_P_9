package fr.nelfdesign.oc_realestatemanager_p_9.repository

import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.repository
 */

private val PROPERTIES = listOf(
    Property(0,"Manoir", 1200000, 250, 6, 4,1,"New dream mansion by the water with a serene view of the bay and the Indian Creek golf course! Inside, a spectacular contemporary design including open and open living spaces surrounded by oversized glass walls, an interior garden, a formal dining room, a chef's kitchen + a 2nd full kitchen, a large upper living room, a sauna and high-end finishes. Exceptional exterior with an infinity pool, spa, summer kitchen and a ready roof - ideal for entertaining and dolphin watching. Deluxe Master offers an ultra-lux marble bathtub, a terrace, a dressing room and endless sunsets! Equipped with Lutron lighting and blinds, 2 car garage with elevator, lush landscape. Live in the exclusive islands of Bay Harbor, minutes from Bal Harbor!","https://s1.1zoom.me/big0/201/USA_Houses_Evening_493487.jpg",
        "21 jump street, New York 10001 ", "", false, "24/02/2020", null, 1),
    Property(0, "Penthouse", 900000, 200, 8,5,2, "Located north of Miami Beach, this penthouse rises on five levels and offers an incredible number of terraces on each of its sides. To spoil nothing, a swimming pool, bar and barbecue are available on the roof. This exceptional penthouse has six bedrooms and as many bathrooms and its interior space is as large as the exterior, for a total area of 1,500 m²!","https://upload.wikimedia.org/wikipedia/commons/4/42/Rooftop_pool_NYC.jpg",
        "455 parc avenue, New York 10010", "", false, "26/02/2020", null, 1),
    Property(0, "Manoir", 2000000, 350, 12,7,3, "Enjoy ocean and sunset views from this area of \u200B\u200B2014 Mashta Island on an oversized lot of 23,814 square feet with 138 feet of water frontage. This custom house, inspired by modern architect Richard Meier, presents pure geometric lines and integrated open spaces with an emphasis on natural light. Unique features include a 3-story curved glass curtain wall, 14 skylights on a 27-foot-high atrium, NanaWall folding glass doors, elevator, gym, dock, accommodation staff, garage for 4 cars, generator and smart home technology.","https://s1.1zoom.me/big0/584/USA_Houses_Mansion_Design_Shrubs_Lawn_555053_1280x801.jpg",
        "635 Lafayette avenue, Booklyn 11213", "", false, "25/02/2020", null, 1)
)

private val PHOTOS = listOf(
    Photo(0, R.drawable.manoir_de_dubourvieux.toString(), "Manoir", 1),
    Photo(0, R.drawable.manoir_chambre.toString(), "Manoir chambre", 1),
    Photo(0, R.drawable.manoir_cuisine.toString(), "Manoir cuisine", 1),
    Photo(0, R.drawable.manoir_salle_de_jeu.toString(), "Manoir salle de jeu", 1),
    Photo(0, R.drawable.penthouse.toString(), "Penthouse", 2),
    Photo(0, R.drawable.penthouse_cuisine.toString(), "Penthouse cuisine", 2)
)

class FakePropertyApi {

    fun getAllPropertyFake() : List<Property>{
        return PROPERTIES
    }

    fun getAllPhotos() : List<Photo>{
        return PHOTOS
    }
}