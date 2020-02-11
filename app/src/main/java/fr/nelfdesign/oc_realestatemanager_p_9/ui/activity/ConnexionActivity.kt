package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.OnClick
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.firebase.UsersHelpers
import timber.log.Timber


class ConnexionActivity : BaseActivity() {

    //ButterKnife
    @BindView(R.id.linear_connection)
    lateinit var mConnexionLayout: LinearLayout
    @BindView(R.id.edit_text_connexion_name)
    lateinit var mEditName: EditText
    @BindView(R.id.edit_text_connexion_password)
    lateinit var mEditPassword: EditText
    @BindView(R.id.connexion_button)
    lateinit var mButton: Button
    @BindView(R.id.checkbox_connexion)
    lateinit var mCheckBox: CheckBox

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

        Timber.w("isChecked = $isChecked")
        loadPref()
        updateView()

        Timber.w("isChecked2 = $isChecked")
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
        login = mEditName.text.toString()
        pass = mEditPassword.text.toString()
        isChecked = mCheckBox.isChecked
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
        mQuery = UsersHelpers().getAllUsers()
        mQuery.get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document: QueryDocumentSnapshot in it.result!!) {
                    if (login == document.get("login").toString() && pass == document.get("password").toString()) {
                        connected()
                        Timber.d("name = $login et pass = $pass et check = $isChecked")
                    } else {
                        Snackbar.make(
                            this.mConnexionLayout,
                            "Login or password isn't correct.",
                            Snackbar.LENGTH_SHORT
                        ).show()
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
                .putBoolean("pref_check", mCheckBox.isChecked)
                .apply()
        Timber.d("Shared saved login= $login, pass = $pass et checked = $isChecked")
        Snackbar.make(this.mConnexionLayout, "Pref saved", Snackbar.LENGTH_SHORT).show()
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
        mEditName.setText(login)
        mEditPassword.setText(pass)
        mCheckBox.isChecked = isChecked
    }

    private fun startMainActivity(){
        intent = Intent(this, MainActivity::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }

}
