package fr.nelfdesign.oc_realestatemanager_p_9.utils

import android.content.ContentValues

/**
 * Created by Nelfdesign at 12/03/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.utils
 */
object ContentProviderTestData {

    fun generateProperty(): ContentValues {
        val values = ContentValues()
        values.put("type", "Manor")
        values.put("price", 3000000.0)
        values.put("area", 250)
        values.put("roomNumber", 9)
        values.put("bedroomNumber", 5)
        values.put("bathroomNumber", 2)
        values.put("description", "good")
        values.put("photo", "")
        values.put("address", "46 rue labas")
        values.put("hospital", true)
        values.put("school", true)
        values.put("market", true)
        values.put("status", "on Sale")
        values.put("entryDate", "24/02/2020")
        values.put("compromiseDate", "")
        values.put("sellDate", "")
        values.put("userId", 1)
        return values
    }


    fun generateProperty2(): ContentValues {
        val values = ContentValues()
        values.put("type", "Loft")
        values.put("price", 2500000.0)
        values.put("area", 350)
        values.put("roomNumber", 10)
        values.put("bedroomNumber", 7)
        values.put("bathroomNumber", 2)
        values.put("description", "good")
        values.put("photo", "")
        values.put("address", "4 avenue du paradis")
        values.put("hospital", true)
        values.put("school", false)
        values.put("market", true)
        values.put("status", "on Sale")
        values.put("entryDate", "24/02/2020")
        values.put("compromiseDate", "")
        values.put("sellDate", "")
        values.put("userId", 1)
        return values
    }

    fun generateProperty3(): ContentValues {
        val values = ContentValues()
        values.put("type", "Penthouse")
        values.put("price", 4000000.0)
        values.put("area", 350)
        values.put("roomNumber", 10)
        values.put("bedroomNumber", 7)
        values.put("bathroomNumber", 2)
        values.put("description", "good")
        values.put("photo", "")
        values.put("address", "4 avenue du paradis")
        values.put("hospital", true)
        values.put("school", false)
        values.put("market", true)
        values.put("status", "on Sale")
        values.put("entryDate", "24/02/2020")
        values.put("compromiseDate", "")
        values.put("sellDate", "")
        values.put("userId", 1)
        return values
    }

    fun generatePropertyUpdated(): ContentValues {
        val values = ContentValues()
        values.put("type", "Penthouse")
        values.put("price", 2500000.0)
        values.put("area", 350)
        values.put("roomNumber", 10)
        values.put("bedroomNumber", 7)
        values.put("bathroomNumber", 2)
        values.put("description", "good")
        values.put("photo", "")
        values.put("address", "4 avenue du paradis")
        values.put("hospital", true)
        values.put("school", false)
        values.put("market", true)
        values.put("status", "on Sale")
        values.put("entryDate", "24/02/2020")
        values.put("compromiseDate", "")
        values.put("sellDate", "")
        values.put("userId", 1)
        return values
    }
}