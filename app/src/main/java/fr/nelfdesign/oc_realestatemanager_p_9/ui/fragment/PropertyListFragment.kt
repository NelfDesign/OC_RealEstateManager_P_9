package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseFragment
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PropertyListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter.PropertyListAdapter
import kotlinx.android.synthetic.main.fragment_property_list.*
import timber.log.Timber

/**
 *
 */
class PropertyListFragment : BaseFragment(), PropertyListAdapter.PropertyListAdapterListener {

    private lateinit var viewModel: PropertyListViewModel
    private lateinit var propertyListAdapter : PropertyListAdapter
    private lateinit var properties : MutableList<Property>

    override fun getFragmentLayout(): Int {
       return R.layout.fragment_property_list
    }

    override fun configureDesign() {
        properties = mutableListOf()
        propertyListAdapter = PropertyListAdapter(properties, this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_property.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = propertyListAdapter
        }
        viewModel = ViewModelProvider(this).get(PropertyListViewModel::class.java)
        viewModel.properties.observe(viewLifecycleOwner, Observer { newProperty -> updateProperty(newProperty) })
    }

    private fun updateProperty(newProperty : List<Property>){
        Timber.d("List of property $newProperty")
        properties.clear()
        properties.addAll(newProperty)
        propertyListAdapter.notifyDataSetChanged()
    }

    override fun onPropertySelected(property: Property) {

    }


}
