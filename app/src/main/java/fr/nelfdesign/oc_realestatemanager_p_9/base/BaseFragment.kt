package fr.nelfdesign.oc_realestatemanager_p_9.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife


/**
 * Created by Nelfdesign at 03/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.base
 */
abstract class BaseFragment : Fragment() {

    // Methods
    abstract fun getFragmentLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(getFragmentLayout(), container, false)
        ButterKnife.bind(this, view)
        return view
    }
}