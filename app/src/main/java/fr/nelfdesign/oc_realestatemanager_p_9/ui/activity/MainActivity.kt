package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.BindView
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.firebase.UsersHelpers

class MainActivity : BaseActivity() {

    //Butterknife
    @BindView(R.id.drawer_layout)
    lateinit var mDrawerLayout: DrawerLayout
    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.drawer_navigation)
    lateinit var mNavigationView: NavigationView
    @BindView(R.id.fragment_main)
    lateinit var mFragment: FrameLayout

    //Fields
    private lateinit var mQuery: Query
    private lateinit var login: String

    /*****************************************************************************************
     * override method
     ****************************************************************************************/
    override fun getActivityLayout(): Int {
        return R.layout.activity_main
    }

    override fun getToolbar(): Toolbar? {
        return mToolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.configureToolBar("Real Estate Manager")

        login = intent.getStringExtra("login")!!

        drawerLayoutConfiguration()
        configureNavigationHeader()
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /************************************************************************************
     *                        configure navigation drawer
     ***********************************************************************************/
    private fun configureNavigationHeader() {
        mNavigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            this.updateMainFragment(
                menuItem
            )!!
        }
        this.updateNavigationHeader()
    }

    /**
     * Update header with user information
     */
    private fun updateNavigationHeader() {

        val headerNav = mNavigationView.getHeaderView(0)

        //XML id for update data
        val imageViewNav = headerNav.findViewById<ImageView>(R.id.imageView_navHeader)
        val textViewNavName = headerNav.findViewById<TextView>(R.id.text_name)
        val textViewNavMail = headerNav.findViewById<TextView>(R.id.text_mail)

        mQuery = UsersHelpers().getAllUsers().whereEqualTo("login", login)
        mQuery.get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document: QueryDocumentSnapshot in it.result!!) {
                    Glide.with(this)
                        .load(document.get("avatar").toString())
                        .circleCrop()
                        .into(imageViewNav)
                    textViewNavName.text = document.get("surname").toString()
                    textViewNavMail.text = document.get("mail").toString()
                }
            }
        }
    }

    /**
     * configuration of the drawer layout
     */
    private fun drawerLayoutConfiguration() {
        val toggle = ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            mToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    /**
     * update main fragment when item is clicked
     *
     * @param menuItem item to click on
     * @return new fragment
     */
    private fun updateMainFragment(menuItem: MenuItem): Boolean? {
        when (menuItem.itemId) {
            R.id.nav_property -> {
                mToolbar.title = "home"
            }
            R.id.nav_simulation -> {
                mToolbar.title = "Credit Simulation"
            }
            R.id.nav_send_property -> {
                mToolbar.title = "Send property"
            }
            R.id.nav_add_property -> {
                mToolbar.title = "Add property"
            }
            R.id.nav_my_sales -> {
                mToolbar.title = "My sales"
            }
            R.id.nav_settings -> {
                mToolbar.title = "Settings"
            }
            R.id.nav_profile -> {
                logOutApplication()
                mToolbar.title = "Modify my profile"
            }
            R.id.logout -> {
                finishAffinity()
            }
        }
        // Closes the DrawerNavigationView when the user click on an item
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        }
        return true
    }

    /**
     *
     */
    private fun logOutApplication() {
        val pref = getSharedPreferences("SharedConnection", Context.MODE_PRIVATE).edit()
        pref.putBoolean("pref_check", false)
            .putString("pref_pass", "")
            .apply()

        startActivity(Intent(this, ConnexionActivity::class.java))
        finishAffinity()
    }
}
