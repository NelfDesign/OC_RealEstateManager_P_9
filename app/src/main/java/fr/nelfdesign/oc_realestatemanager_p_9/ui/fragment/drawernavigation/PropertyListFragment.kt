package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseFragment
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.Injection
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PropertyListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.AddPropertyActivity
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.DetailProperty
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        properties = mutableListOf()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = Injection.provideViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(PropertyListViewModel::class.java)
        viewModel.properties.observe(viewLifecycleOwner, Observer { newProperty -> updateProperty(newProperty) })

        propertyListAdapter = PropertyListAdapter(properties, this)

        recycler_property.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = propertyListAdapter
        }

        fab_add_property.setOnClickListener{
            val intent = Intent(this.requireContext(), AddPropertyActivity::class.java)
            intent.putExtra("tag", "add")
            startActivity(intent)
        }

    }

    private fun updateProperty(newProperty : List<Property>){
        properties.clear()
        properties.addAll(newProperty)
        Timber.d("List of property = ${properties.size},  $properties")
        propertyListAdapter.notifyDataSetChanged()
    }

    override fun  onPropertySelected(property : Property) {
        val intent = Intent(context, DetailProperty::class.java)
        intent.putExtra(DetailProperty.PROPERTY_ID, property.id)
        startActivity(intent)
    }

}
