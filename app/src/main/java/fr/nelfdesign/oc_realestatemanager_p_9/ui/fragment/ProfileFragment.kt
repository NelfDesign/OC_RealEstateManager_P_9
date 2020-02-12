package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.OnClick
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.QueryDocumentSnapshot
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseFragment
import fr.nelfdesign.oc_realestatemanager_p_9.firebase.UsersHelpers
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.MainActivity
import java.lang.RuntimeException


/**
 *
 */
class ProfileFragment : BaseFragment(){

    interface onClickConfirmButtonListener{
        fun onClickConfirmButton()
    }

    @BindView(R.id.button_profile)
    lateinit var buttonProfile: Button
    @BindView(R.id.linear_profile)
    lateinit var linearProfile : LinearLayout
    @BindView(R.id.password_edit)
    lateinit var passEditText: TextInputEditText
    @BindView(R.id.confirm_edit)
    lateinit var passConfirmEditText: TextInputEditText
    @BindView(R.id.tel_edit)
    lateinit var telEditText: TextInputEditText
    @BindView(R.id.avatar_edit)
    lateinit var avatarEditText: TextInputEditText
    @BindView(R.id.mail_edit)
    lateinit var mailEditText: TextInputEditText

    private lateinit var onClickListener : onClickConfirmButtonListener
    private lateinit var passText : String
    private lateinit var confirmText : String
    private lateinit var telText : String
    private lateinit var mailText : String
    private lateinit var avatarText : String
    private lateinit var login : String

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login = MainActivity.LOGIN_USER
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is onClickConfirmButtonListener) {
            onClickListener = context
        } else {
            throw RuntimeException("$context must implemente interface")
        }
    }

    @OnClick(R.id.button_profile)
    fun onClickButtonProfile(v : View){
        when(v.id){
            R.id.button_profile -> {
                getUpdateProfile()
                onClickListener.onClickConfirmButton()
            }
        }
    }

    private fun getData(){
        passText = passEditText.text.toString()
        confirmText = passConfirmEditText.text.toString()
        telText = telEditText.text.toString()
        mailText = mailEditText.text.toString()
        avatarText = avatarEditText.text.toString()
    }

    private fun getUpdateProfile(){
        val query = UsersHelpers().getAllUsers().whereEqualTo("login", login)
        getData()
        if (checkPass(passText, confirmText)){
            query.get().addOnCompleteListener{
                if (it.isSuccessful){
                    for (document : QueryDocumentSnapshot in it.result!!){
                        val id = document.id
                        if (telText == "") telText = document.get("telephone").toString()
                        if (avatarText == "") avatarText = document.get("avatar").toString()
                        if (mailText == "") mailText = document.get("mail").toString()
                        UsersHelpers().updateUser(passText, mailText, avatarText, id, telText)
                    }
                }
            }
        }else{
            Snackbar.make(this.linearProfile, "Password aren't equals", Snackbar.LENGTH_SHORT).show()
        }


    }

    private fun checkPass(pass : String, confirm : String) : Boolean{
        if (pass == confirm){
            return true
        }
        return false
    }
}
