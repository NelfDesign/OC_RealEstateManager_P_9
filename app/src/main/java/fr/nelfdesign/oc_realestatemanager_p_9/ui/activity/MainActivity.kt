package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.BindView
import com.google.android.material.navigation.NavigationView
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity

class MainActivity : BaseActivity() {

    @BindView(R.id.drawer_layout)
    lateinit var mDrawerLayout: DrawerLayout
    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar
    @BindView(R.id.drawer_navigation)
    lateinit var mNavigationView: NavigationView

    override fun getActivityLayout(): Int {
        return R.layout.activity_main
    }

    override fun getToolbar(): Toolbar? {
        return mToolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.configureToolBar("Real Estate Manager")

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

    /**
     * configure navigation drawer header
     */
    private fun configureNavigationHeader() {
        mNavigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            this.updateMainFragment(
                menuItem
            )!!
        }
       // this.updateNavigationHeader()
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
            R.id.nav_property-> {

                mToolbar.title = "home"
            }
            R.id.nav_settings -> {
                mToolbar.title = "settings"
            }
           // R.id.logout ->
        }
        // Closes the DrawerNavigationView when the user click on an item
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        }
        return true
    }
}
