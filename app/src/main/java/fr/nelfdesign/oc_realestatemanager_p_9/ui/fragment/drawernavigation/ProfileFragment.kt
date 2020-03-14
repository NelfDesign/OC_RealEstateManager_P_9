package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation

import android.content.Context
import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.google.firebase.firestore.QueryDocumentSnapshot
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseFragment
import fr.nelfdesign.oc_realestatemanager_p_9.firebase.UsersHelpers
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.MainActivity
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils
import kotlinx.android.synthetic.main.fragment_profile.*
import timber.log.Timber

class ProfileFragment : BaseFragment() {

    interface OnClickConfirmButtonListener {
        fun onClickConfirmButton()
    }

    private var onClickListener: OnClickConfirmButtonListener? = null
    private lateinit var passText: String
    private lateinit var confirmText: String
    private lateinit var telText: String
    private lateinit var mailText: String
    private lateinit var avatarText: String
    private lateinit var login: String

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login = MainActivity.LOGIN_USER
        Timber.d("login = $login")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text_user?.text = login
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickConfirmButtonListener) {
            onClickListener = context
        } else {
            throw RuntimeException("$context must implemente interface")
        }
    }

    override fun onDetach() {
        onClickListener = null
        super.onDetach()
    }

    @OnClick(R.id.button_profile)
    fun onClickButtonProfile(v: View) {
        when (v.id) {
            R.id.button_profile -> {
                getUpdateProfile()
                if (checkPass(passText, confirmText)) {
                    onClickListener?.onClickConfirmButton()
                } else {
                    Utils.makeSnackbar(this.linear_profile, "Password aren't equals")
                }
            }
        }
    }

    private fun getData() {
        if (passText == "" || confirmText == ""){
            Utils.makeSnackbar(this.linear_profile, "Password are necessary")
            return
        }
        passText = password_edit.text.toString()
        confirmText = confirm_edit.text.toString()
        telText = tel_edit.text.toString()
        mailText = mail_edit.text.toString()
        avatarText = avatar_edit.text.toString()
    }

    private fun getUpdateProfile() {
        val query = UsersHelpers().getAllUsers().whereEqualTo("login", login)
        getData()
        query.get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document: QueryDocumentSnapshot in it.result!!) {
                    val id = document.id
                    if (telText == "") telText = document.get("telephone").toString()
                    if (avatarText == "") avatarText = document.get("avatar").toString()
                    if (mailText == "") mailText = document.get("mail").toString()
                    UsersHelpers().updateUser(passText, mailText, avatarText, id, telText)
                }
            }
        }
    }

    private fun checkPass(pass: String, confirm: String): Boolean {
        if (pass == confirm) {
            return true
        }
        return false
    }
}
