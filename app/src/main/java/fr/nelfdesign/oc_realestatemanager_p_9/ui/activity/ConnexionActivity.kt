package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity

class ConnexionActivity : BaseActivity() {
    override fun getActivityLayout(): Int {
        return R.layout.activity_connexion
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
