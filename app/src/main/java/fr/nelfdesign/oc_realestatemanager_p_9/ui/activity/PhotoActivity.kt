package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.CameraFragment
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.GalleryFragment

class PhotoActivity : BaseActivity() {

    lateinit var fragment : Fragment

    override fun getActivityLayout(): Int {
       return R.layout.activity_photo
    }

    override fun getToolbar(): Toolbar? {
       return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val TAG = intent.getStringExtra("tag")

        when(TAG){
            "camera" -> {
                fragment = CameraFragment()
                configureFragment(fragment)
            }
            "gallery" ->{
                fragment = GalleryFragment()
                configureFragment(fragment)
            }
        }

    }

    private fun configureFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_photo, fragment)
            .commit()
    }

}
