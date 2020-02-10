package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.OnClick
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.firebase.UsersHelpers
import timber.log.Timber
import kotlin.properties.Delegates


class ConnexionActivity : BaseActivity() {

    //ButterKnife
    @BindView(R.id.linear_connection)
    lateinit var mConnexionLayout : LinearLayout
    @BindView(R.id.edit_text_connexion_name)
    lateinit var mEditName : EditText
    @BindView(R.id.edit_text_connexion_password)
    lateinit var mEditPassword: EditText
    @BindView(R.id.connexion_button)
    lateinit var mButton: Button

    //FIELDS
    private var login : String? = ""
    private var pass : String? = ""
    private lateinit var mQuery : Query
    private var check : Boolean = false

    override fun getActivityLayout(): Int {
        return R.layout.activity_connexion
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

   @OnClick(R.id.connexion_button)
    fun onClickConnexionButton(view : View){
       login = mEditName.text.toString()
       pass = mEditPassword.text.toString()
        when(view.id){
            R.id.connexion_button -> {
                checkName()
            }
        }
    }

    private fun checkName() {
        val usersRef: CollectionReference = UsersHelpers().getUsersCollection()
        usersRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Timber.w("Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                for (document: DocumentSnapshot in snapshot.documents) {
                    if (login == document.get("login").toString() && pass == document.get("password").toString()) {
                        connected()
                        Timber.d("name = $login et pass = $pass et check = $check")
                    }else{
                        Snackbar.make(this.mConnexionLayout, "Login or password isn't correct.", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun connected() {
        check = true
        if (check) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}
