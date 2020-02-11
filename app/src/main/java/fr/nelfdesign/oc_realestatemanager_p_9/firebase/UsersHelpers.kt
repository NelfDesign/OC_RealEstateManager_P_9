package fr.nelfdesign.oc_realestatemanager_p_9.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import fr.nelfdesign.oc_realestatemanager_p_9.models.User


/**
 * Created by Nelfdesign at 06/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.firebase
 */
class UsersHelpers {

    //FIELD
    private val COLLECTION_NAME = "users"
    val db = FirebaseFirestore.getInstance()

    // --- COLLECTION REFERENCE ---
    private fun getUsersCollection(): CollectionReference {
        return db.collection(COLLECTION_NAME)
    }

    // --- CREATE ---
    fun createUser(name: String, surname: String, avatar : String, mail :String, login: String, password: String, telephone: String) : Task<Void> {
        val userToCreate = User(name, surname, avatar, mail, login, password, telephone)
        return UsersHelpers().getUsersCollection().document().set(userToCreate)
    }

    // -- GET ALL Workers --
    fun getAllUsers(): Query {
        return UsersHelpers().getUsersCollection()
    }

    // --- UPDATE ---
    fun updateUser(password : String, uid : String, telephone : String) {

        val data : MutableMap<String, Any> = HashMap()
        data["password"] = password
        data["telephone"] = telephone
        UsersHelpers().getUsersCollection().document(uid).update(data)
    }

}