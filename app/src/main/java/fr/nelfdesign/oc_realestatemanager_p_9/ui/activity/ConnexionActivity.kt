package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.OnClick
import com.github.ybq.android.spinkit.SpinKitView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.firebase.UsersHelpers
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils
import kotlinx.android.synthetic.main.activity_connexion.*
import timber.log.Timber


class ConnexionActivity : BaseActivity() {

    @BindView(R.id.loading_indicator)
    lateinit var loadingIndicator: SpinKitView

    //FIELDS
    private lateinit var mQuery: Query
    private var login: String? = ""
    private var pass: String? = ""
    private var isChecked: Boolean = false

    /******************************************************************************************
     * override Method
     *****************************************************************************************/
    override fun getActivityLayout(): Int {
        return R.layout.activity_connexion
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadPref()
        updateView()

        if (isChecked) {
            checkName()
            Timber.d("Shared onCreate() login= $login, pass = $pass et checked = $isChecked")
        }
    }

    /****************************************************************************************
     * onClick
     ***************************************************************************************/
    @OnClick(R.id.connexion_button)
    fun onClickConnexionButton(view: View) {
        login = edit_text_connexion_name.text.toString()
        pass = edit_text_connexion_password.text.toString()
        isChecked = checkbox_connexion.isChecked
        when (view.id) {
            R.id.connexion_button -> {
                checkName()
            }
        }
    }

    /**
     * check if the login and password is correct with the BDD
     */
    private fun checkName() {
        loadingIndicator.visibility = View.VISIBLE
        mQuery = UsersHelpers().getAllUsers()
        mQuery.get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document: QueryDocumentSnapshot in it.result!!) {
                    if (login == document.get("login").toString() && pass == document.get("password").toString()) {
                        connected()
                        Timber.d("name = $login et pass = $pass et check = $isChecked")
                    } else {
                        Utils.makeSnackBar(this.linear_connection, "Login or password isn't correct.")
                    }
                }
            }
        }
    }

    /**
     * save login, password and checkbox choice in sharedPreferences
     * and start MainActivity
     */
    @SuppressLint("CommitPrefEdits")
    private fun connected() {
        val shares = getSharedPreferences("SharedConnection",Context.MODE_PRIVATE).edit()
            shares.putString("pref_login", login)
                .putString("pref_pass", pass)
                .putBoolean("pref_check", checkbox_connexion.isChecked)
                .apply()
        Timber.d("Shared saved login= $login, pass = $pass et checked = $isChecked")
        Utils.makeSnackBar(this.linear_connection, "Pref saved")
        startMainActivity()
    }

    /**
     * load login, password and checkbox choice
     */
    private fun loadPref() {
        val shared = getSharedPreferences("SharedConnection",Context.MODE_PRIVATE)
        shared.apply {
            login = getString("pref_login", "")
            pass = getString("pref_pass", "")
            isChecked = getBoolean("pref_check", false)
        }
    }

    /**
     * update View with  sharedPreferences
     */
    private fun updateView() {
        edit_text_connexion_name.setText(login)
        edit_text_connexion_password.setText(pass)
        checkbox_connexion.isChecked = isChecked
    }

    private fun startMainActivity(){
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }

}
