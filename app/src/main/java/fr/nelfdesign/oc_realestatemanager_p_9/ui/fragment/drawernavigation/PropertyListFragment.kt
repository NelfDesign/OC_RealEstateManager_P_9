package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseFragment
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.Injection
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PropertyListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.AddPropertyActivity
import fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter.PropertyListAdapter
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils.*
import kotlinx.android.synthetic.main.filter_query_dialog.view.*
import kotlinx.android.synthetic.main.fragment_property_list.*
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


/**
 * property list
 */
class PropertyListFragment : BaseFragment(), PropertyListAdapter.PropertyListAdapterListener {

    interface OnClickEstateListener{
        fun onClickItemEstate(propertyId : Long)
    }

    private lateinit var viewModel: PropertyListViewModel
    private lateinit var propertyListAdapter: PropertyListAdapter
    private var properties: MutableList<Property> = mutableListOf()
    private var town : String = ""
    private var minPrice : Double = 0.0
    private var maxPrice : Double = 0.0
    private var minRoom : Int= 0
    private var maxRoom : Int = 0
    private var minSurface : Int = 0
    private var maxSurface : Int = 0
    private var numberPhotos : Int= 0
    private var entryDateLong : Long = 0
    private var soldDateLong : Long = 0
    private var sold : String = ""
    private var entryDate: String = ""
    private var sellDate: String = ""
    private var hospital: Boolean = false
    private var school : Boolean = false
    private var market: Boolean = false
    private var listener : OnClickEstateListener? = null
    private var mDateSetListener: OnDateSetListener? = null
    private var mDateSetListener2: OnDateSetListener? = null


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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClickEstateListener){
            listener = context
        }else{
            throw RuntimeException("$context must implemente PropertyListFragment interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
                        p.priceEuro = convertDollarToEuro(p.price.toInt()).toDouble()
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
        val mDialog = LayoutInflater.from(activity).inflate(R.layout.filter_query_dialog, viewGroup, false)
        //build the dialog with custom view
        val mBuilder = AlertDialog.Builder(activity).setView(mDialog)
        //show dialog
        val mAlertDialog = mBuilder.show()

       mDialog.entry_filter.setOnClickListener { configureDialogCalendar(mDateSetListener!!) }
       mDialog.sold_filter.setOnClickListener { configureDialogCalendar(mDateSetListener2!!) }

        mDateSetListener = OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                var day = dayOfMonth.toString()
                var monthS = (month + 1).toString()
                if (dayOfMonth < 10) {
                    day = "0$dayOfMonth"
                }
                if (month < 9) {
                    monthS = "0" + (month + 1)
                }
                entryDate = "$day/$monthS/$year"
                mDialog.entry_date_filter.text = entryDate
            }

        mDateSetListener2 = OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            var day = dayOfMonth.toString()
            var monthS = (month + 1).toString()
            if (dayOfMonth < 10) {
                day = "0$dayOfMonth"
            }
            if (month < 9) {
                monthS = "0" + (month + 1)
            }
            sellDate = "$day/$monthS/$year"
           mDialog.sold_date_filter.text = sellDate
        }

        mDialog.button_filter.setOnClickListener {
            val  list : List<String> = arrayListOf("Manor", "Penthouse", "Loft", "House")
            val listType = ArrayList<String>()

            if (mDialog.radio_Manor.isChecked) listType.add(mDialog.radio_Manor.text.toString())
            if (mDialog.radio_Penthouse.isChecked) listType.add(mDialog.radio_Penthouse.text.toString())
            if (mDialog.radio_Loft.isChecked) listType.add( mDialog.radio_Loft.text.toString())
            if (mDialog.radio_house.isChecked) listType.add(mDialog.radio_house.text.toString())
            if (!mDialog.radio_Manor.isChecked && !mDialog.radio_Penthouse.isChecked && !mDialog.radio_Loft.isChecked && !mDialog.radio_house.isChecked) listType.addAll(list)

             town = mDialog.filter_town.text.toString()
             minPrice = checkData(mDialog.filter_min_price.text.toString()).toDouble()
             maxPrice = checkMaxData(mDialog.filter_max_price.text.toString())
             minRoom = checkData(mDialog.filter_min_room.text.toString())
             maxRoom = checkMaxData(mDialog.filter_max_room.text.toString()).toInt()
             minSurface = checkData(mDialog.filter_min_area.text.toString())
             maxSurface = checkMaxData(mDialog.filter_max_area.text.toString()).toInt()
             numberPhotos = checkMaxData(mDialog.filter_number_photos.text.toString()).toInt()

            when {
                mDialog.radio_on_sale.isChecked -> sold = mDialog.radio_on_sale.text.toString()
                mDialog.radio_sold.isChecked -> sold = mDialog.radio_sold.text.toString()
            }

            entryDateLong = convertDateToLong(entryDate)
            soldDateLong = convertDateToLong(sellDate)

            hospital = mDialog.radio_hospital.isChecked
            school  = mDialog.radio_school.isChecked
            market = mDialog.radio_Market.isChecked

            Timber.d("Parameters : $listType, $town, $minPrice, $maxPrice, $minRoom, $maxRoom, $minSurface, $maxSurface, $numberPhotos, $sold, $entryDate, $entryDateLong, $sellDate, $soldDateLong, $hospital, $market, $school")

            refreshListProperty(listType)
            mAlertDialog.dismiss()
        }

        mDialog.button_Nofilter.setOnClickListener {
            viewModel.properties.observe(viewLifecycleOwner, Observer { estate -> updateProperty(estate) })
            mAlertDialog.dismiss()
        }
    }

    private fun updateProperty(newProperty: List<Property>) {
        Timber.d("List property in update = ${newProperty.size}")
        properties.clear()
        properties.addAll(newProperty)
        Timber.d("List of property = ${properties.size},  $properties")
        checkIfNoProperty()
        propertyListAdapter.notifyDataSetChanged()
    }

    private fun refreshListProperty(listType : List<String>) {
        Timber.d("List de biens = $listType")
           if (town != ""){
               if (!hospital && !school && !market){
                   viewModel.filterProperty( listType, town, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                       numberPhotos, sold, entryDateLong, soldDateLong).observe(viewLifecycleOwner, Observer { property -> updateProperty(property)})
               }else{
                   viewModel.filterPropertiesWithParameters(
                       listType, town, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                       numberPhotos, sold, entryDateLong, soldDateLong, school, hospital, market
                   ).observe(viewLifecycleOwner, Observer { propertyFilter -> updateProperty(propertyFilter) })
               }
            }else {
               if (!hospital && !school && !market) {
                   viewModel.filterPropertyWithoutTown(
                       listType, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                       numberPhotos, sold, entryDateLong, soldDateLong ).observe(viewLifecycleOwner, Observer { property -> updateProperty(property) })
               } else {
                   viewModel.filterPropertiesWithNoTownParameter(
                       listType, minPrice, maxPrice, minRoom, maxRoom, minSurface, maxSurface,
                       numberPhotos, sold, entryDateLong, soldDateLong, school, hospital, market
                   ).observe(viewLifecycleOwner, Observer { propertyFilter -> updateProperty(propertyFilter) })
               }
           }
    }

    private fun checkIfNoProperty() {
        if (propertyListAdapter.itemCount == 0) {
            textViewNoProperty.visibility = View.VISIBLE
        } else textViewNoProperty.visibility = View.GONE
    }

    private fun configureDialogCalendar(dateListener : OnDateSetListener) {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        val dialogDate = DatePickerDialog(
            requireContext(), dateListener,
            year, month, day
        )
        dialogDate.show()
    }

    override fun onPropertySelected(property: Property) {
        listener?.onClickItemEstate(property.id)
    }

}
