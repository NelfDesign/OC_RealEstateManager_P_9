package fr.nelfdesign.oc_realestatemanager_p_9.base

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.ButterKnife


/**
 * Created by Nelfdesign at 03/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.base
 */
abstract class BaseActivity : AppCompatActivity() {

    // Methods
    abstract fun getActivityLayout(): Int

    @Nullable
    protected abstract fun getToolbar(): Toolbar?

    // --------------------
    // Activity
    // --------------------
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(getActivityLayout())
        ButterKnife.bind(this) //Configure Butterknife
        this.configureToolBar()
    }

    // --------------------
    // UI
    // --------------------

    protected open fun configureToolBar(text: String? = "") { // If ToolBar exists
        if (getToolbar() != null) {
            getToolbar()!!.title = text
            setSupportActionBar(getToolbar())
        }
    }
}