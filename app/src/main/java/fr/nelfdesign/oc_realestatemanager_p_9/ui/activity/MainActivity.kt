package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.bumptech.glide.Glide
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import fr.nelfdesign.oc_realestatemanager_p_9.R.id
import fr.nelfdesign.oc_realestatemanager_p_9.R.layout
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.firebase.UsersHelpers
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation.DetailPropertyFragment
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation.DetailPropertyFragment.Companion.PROPERTY_ID_DETAIL
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation.MapFragment
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation.ProfileFragment
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation.PropertyListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : BaseActivity(), ProfileFragment.OnClickConfirmButtonListener,
    PropertyListFragment.OnClickEstateListener, MapFragment.OnClickMarkerListener {

    //Fields
    private lateinit var mQuery: Query
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object LoginUser {
        var LOGIN_USER: String = ""
    }

    /*****************************************************************************************
     * override method
     ****************************************************************************************/
    override fun getActivityLayout(): Int {
        return layout.activity_main
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        navController = findNavController(id.navHostFragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                id.nav_property, id.nav_settings, id.logout,
                id.nav_map, id.nav_profile, id.nav_send_property, id.nav_simulation
            ), drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        drawer_navigation.setupWithNavController(navController)

        LOGIN_USER = intent.getStringExtra("login")!!

        updateNavigationHeader()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    /**
     * use to control the back press
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * Update header with user information
     */
    private fun updateNavigationHeader() {

        val headerNav = drawer_navigation.getHeaderView(0)

        //XML id for update data
        val imageViewNav = headerNav.findViewById<ImageView>(id.imageView_navHeader)
        val textViewNavName = headerNav.findViewById<TextView>(id.text_name)
        val textViewNavMail = headerNav.findViewById<TextView>(id.text_mail)
        val textViewNavtel = headerNav.findViewById<TextView>(id.text_phone_nav)

        mQuery = UsersHelpers().getAllUsers().whereEqualTo("login", LOGIN_USER)
        mQuery.get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document: QueryDocumentSnapshot in it.result!!) {
                    Glide.with(this)
                        .load(document.get("avatar").toString())
                        .circleCrop()
                        .into(imageViewNav)
                    textViewNavName.text = document.get("surname").toString()
                    textViewNavMail.text = document.get("mail").toString()
                    textViewNavtel.text = document.get("telephone").toString()
                }
            }
        }
    }

    /**
     *
     */
    /* private fun logOutApplication() {
         val pref = getSharedPreferences("SharedConnection", Context.MODE_PRIVATE).edit()
         pref.putBoolean("pref_check", false)
             .putString("pref_pass", "")
             .apply()
     }*/

    /**
     * listener for click on confirm button in profile fragment
     */
    override fun onClickConfirmButton() {
        navController.navigate(id.nav_property)
    }

    override fun onClickItemEstate(propertyId: Long) {
        PROPERTY_ID_DETAIL = propertyId
        if (frameLayout_container_detail != null) {
            val fragmentDetail = DetailPropertyFragment()
            supportFragmentManager.beginTransaction()
                .replace(id.frameLayout_container_detail, fragmentDetail)
                .commit()
        } else {
            navController.navigate(id.action_nav_property_to_detailPropertyFragment)
        }

    }

    override fun onClickMarkerEstate(propertyId: Long) {
        PROPERTY_ID_DETAIL = propertyId

        navController.navigate(id.detailPropertyFragment)

    }

}
