package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.Injection
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PhotoListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PropertyListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter.DetailAdapter
import kotlinx.android.synthetic.main.activity_detail_property.*
import timber.log.Timber
import java.lang.StringBuilder


class DetailProperty : BaseActivity(), DetailAdapter.onClickItemListener {

    private lateinit var adapterDetail: DetailAdapter
    private lateinit var photoViewModel : PhotoListViewModel
    private lateinit var propertyViewModel : PropertyListViewModel
    private lateinit var photos : MutableList<Photo>
    private var propertyId : Long = 0

    companion object{
        const val PROPERTY_ID = "propertyId"
    }

    override fun getActivityLayout(): Int {
        return R.layout.activity_detail_property
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        propertyId = intent.getLongExtra(PROPERTY_ID, 1)
        Timber.d("property id is $propertyId")
        photos = mutableListOf()

        configureViewModel(propertyId)
        adapterDetail = DetailAdapter(photos, this)

        configureRecyclerView()
    }

    @OnClick(R.id.button_update)
    fun updateButton(){
        intent = Intent(this, AddPropertyActivity::class.java)
        intent.putExtra(PROPERTY_ID, propertyId)
        startActivity(intent)
        finish()
    }

    private fun configureViewModel(propertyId : Long){
        val factory = Injection.provideViewModelFactory()
        photoViewModel = ViewModelProvider(this, factory).get(PhotoListViewModel::class.java)
        photoViewModel.getPhotoToDisplay(propertyId).observe(this, Observer { list -> updatePhotos(list) })

        propertyViewModel = ViewModelProvider(this, factory).get(PropertyListViewModel::class.java)
        propertyViewModel.getPropertyById(propertyId).observe(this, Observer { property -> updateProperty(property) })
    }

    private fun updatePhotos(list : List<Photo>) {
        photos.clear()
        photos.addAll(list)
        adapterDetail.notifyDataSetChanged()
        Timber.d("list photo VM = ${photos.size}, $photos")
    }

    private fun configureRecyclerView() {
        val linear = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_detail.apply {
            layoutManager = linear
            itemAnimator = DefaultItemAnimator()
            adapter = adapterDetail
        }
    }

    private fun updateProperty(property: Property) {
        text_type.text = property.type
        text_price.text = resources.getString(R.string.devise_dollars, property.price.toString())
        text_description.text = property.description
        text_description.movementMethod = ScrollingMovementMethod()
        property_area.text = property.area.toString()
        property_address.text = buildTextAddress(property.street, property.town)
        property_room.text = property.roomNumber.toString()
        property_bathroom.text = property.bathroomNumber.toString()
        property_bedroom.text = property.bedroomNumber.toString()
        entry_date.text = property.entryDate
        if (property.hospital) poi_hospital.visibility = View.VISIBLE
        if (property.school) poi_school.visibility = View.VISIBLE
        if (property.market) poi_market.visibility = View.VISIBLE
        showCompromiseAndSoldText(property.compromiseDate, compromise_date, layout_compromise)
        showCompromiseAndSoldText(property.sellDate, sold_date, layout_sold)
    }

    private fun buildTextAddress(street : String, town : String) : String {
        val text = StringBuilder()
        text.append(street)
        text.append(", ")
        text.append(town)
        return text.toString()
    }

    private fun showCompromiseAndSoldText(text : String?, textView : TextView, layout : LinearLayout){
        if (text != null && text != ""){
            layout.visibility = View.VISIBLE
            textView.text = text
        }
    }

    /**
     * click on the image may do nothing in detail page
     */
    override fun onClickItem(image : Photo) {
    }

}
