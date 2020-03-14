package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
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
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils.checkData
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils.checkMaxData
import kotlinx.android.synthetic.main.filter_query_dialog.view.*
import kotlinx.android.synthetic.main.fragment_property_list.*
import timber.log.Timber


/**
 *
 */
class PropertyListFragment : BaseFragment(), PropertyListAdapter.PropertyListAdapterListener {

    private lateinit var viewModel: PropertyListViewModel
    private lateinit var propertyListAdapter: PropertyListAdapter
    private var properties: MutableList<Property> = mutableListOf()
    private var town : String = ""
    private var minPrice : Long = 0
    private var maxPrice : Long = 0
    private var minRoom : Int= 0
    private var maxRoom : Int = 0
    private var minSurface : Int = 0
    private var maxSurface : Int = 0
    private var numberPhotos : Int= 0
    private var sold : String = "On sale"
    private var entryDate: String = ""
    private var sellDate: String = ""
    private var hospital: Boolean = false
    private var school : Boolean = false
    private var market: Boolean = false

        companion object {
        internal var DEVISE : String = "dollars"
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_property_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
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

        fab_add_property.setOnClickListener {
            val intent = Intent(this.requireContext(), AddPropertyActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_money -> {
                if (DEVISE == "dollars") {
                    for (p in properties) {
                        p.priceEuro = Utils.convertDollarToEuro(p.price.toInt()).toDouble()
                    }
                    DEVISE = "euro"
                    propertyListAdapter.notifyDataSetChanged()
                } else {
                    DEVISE = "dollars"
                    propertyListAdapter.notifyDataSetChanged()
                }

            }
            R.id.filter -> alertDialogQuery()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alertDialogQuery() {

        val viewGroup = activity?.findViewById<ViewGroup>(android.R.id.content)
        //Inflate dialog with custom layout
        val mDialog =
            LayoutInflater.from(activity).inflate(R.layout.filter_query_dialog, viewGroup, false)
        //build the dialog with custom view
        val mBuilder = AlertDialog.Builder(activity).setView(mDialog)
        //show dialog
        val mAlertDialog = mBuilder.show()

        mDialog.button_filter.setOnClickListener {
            val listType = ArrayList<String>()
            if (mDialog.radio_Manor.isChecked) listType.add(mDialog.radio_Manor.text.toString())
            if (mDialog.radio_Penthouse.isChecked) listType.add(mDialog.radio_Penthouse.text.toString())
            if (mDialog.radio_Loft.isChecked) listType.add( mDialog.radio_Loft.text.toString())
            if (mDialog.radio_house.isChecked) listType.add(mDialog.radio_house.text.toString())

             town = mDialog.filter_town.text.toString()
             minPrice = checkData(mDialog.filter_min_price.text.toString()).toLong()
             maxPrice = checkMaxData(mDialog.filter_max_price.text.toString()).toLong()
             minRoom = checkData(mDialog.filter_min_room.text.toString())
             maxRoom = checkMaxData(mDialog.filter_max_room.text.toString())
             minSurface = checkData(mDialog.filter_min_area.text.toString())
             maxSurface = checkMaxData(mDialog.filter_max_area.text.toString())
             numberPhotos = checkMaxData(mDialog.filter_number_photos.text.toString())

            when {
                mDialog.radio_on_sale.isChecked -> sold = mDialog.radio_on_sale.text.toString()
                mDialog.radio_sold.isChecked -> sold = mDialog.radio_sold.text.toString()
            }
             entryDate = if (mDialog.filter_entry_date.text.toString() == "") "24/02/2020" else mDialog.filter_sell_date.text.toString()
            sellDate = if (mDialog.filter_sell_date.text.toString() == "") "" else mDialog.filter_sell_date.text.toString()
            hospital = mDialog.filter_chip_hospital.isChecked
             school  = mDialog.filter_chip_school.isChecked
             market = mDialog.filter_chip_market.isChecked

            Timber.d("Parameters : $listType, $town, $minPrice, $maxPrice, $minRoom, $maxRoom, $minSurface, $maxSurface, $numberPhotos, $sold, $entryDate, $sellDate, $hospital, $market, $school")

            refreshListProperty(listType)
            mAlertDialog.dismiss()
        }
    }

    private fun updateProperty(newProperty: List<Property>) {
        properties.clear()
        properties.addAll(newProperty)
        Timber.d("List of property = ${properties.size},  $properties")
        checkIfNoProperty()
        propertyListAdapter.notifyDataSetChanged()
    }

    private fun refreshListProperty(listType : List<String>) {
        if (listType.isNotEmpty()){
            if (town != ""){
                viewModel.filterPropertiesWithParameters(
                    listType, town, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                    numberPhotos, sold, entryDate, sellDate, hospital, school, market
                ).observe(viewLifecycleOwner, Observer { propertyFilter -> updateProperty(propertyFilter) })
            }else{
                viewModel.filterPropertiesWithNoTownParameter(
                    listType, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                    numberPhotos, sold, entryDate, sellDate, hospital, school, market
                ).observe(viewLifecycleOwner, Observer { propertyFilter -> updateProperty(propertyFilter) })
            }
        }else{
            if (town != ""){
                viewModel.filterPropertiesWithOutTypeParameters(
                    town, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                    numberPhotos, sold, entryDate, sellDate, hospital, school, market
                ).observe(viewLifecycleOwner, Observer { propertyFilter -> updateProperty(propertyFilter)})
            }else{
                viewModel.filterPropertiesWithNoTownAndNoTypeParameter(minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                    numberPhotos, sold, entryDate, sellDate, hospital, school, market
                ).observe(viewLifecycleOwner, Observer { propertyFilter -> updateProperty(propertyFilter)})
            }

        }

    }

    private fun checkIfNoProperty() {
        if (propertyListAdapter.itemCount == 0) {
            textViewNoProperty.visibility = View.VISIBLE
        } else textViewNoProperty.visibility = View.GONE
    }

    override fun onPropertySelected(property: Property) {
        val intent = Intent(context, DetailProperty::class.java)
        intent.putExtra(DetailProperty.PROPERTY_ID, property.id)
        startActivity(intent)
    }

}
