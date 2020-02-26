package fr.nelfdesign.oc_realestatemanager_p_9.repository

import fr.nelfdesign.oc_realestatemanager_p_9.models.Property

/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.repository
 */

private val PROPERTIES = listOf(
    Property(0,"Manoir", 1200000, 250, 6, "Great Manoir",
        "21 jump street, New York 10001 ", "", false, "24/02/2020", null, 1),
    Property(0, "Penthouse", 900000, 200, 5, "Penthouse",
        "455 parc avenue, New York 10010", "", false, "26/02/2020", null, 1),
    Property(0, "Manoir", 2000000, 350, 8, "Great Manoir",
        "635 Lafayette avenue, Booklyn 11213", "", false, "25/02/2020", null, 1)
)

class FakePropertyApi {

    fun getAllPropertyFake() : List<Property>{
        return PROPERTIES
    }

}