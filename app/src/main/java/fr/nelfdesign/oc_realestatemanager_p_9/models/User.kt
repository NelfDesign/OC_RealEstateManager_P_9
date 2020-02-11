package fr.nelfdesign.oc_realestatemanager_p_9.models

/**
 * Created by Nelfdesign at 06/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.models
 */
data class User (val name : String,
                 val surname : String,
                 val login : String,
                 var avatar : String?,
                 var mail :String?,
                 var password : String,
                 var telephone : String)