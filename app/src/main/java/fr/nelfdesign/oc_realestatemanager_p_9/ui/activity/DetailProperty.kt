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


class DetailProperty : BaseActivity() {

    private lateinit var adapterDetail: DetailAdapter
    private lateinit var photoViewModel : PhotoListViewModel
    private lateinit var propertyViewModel : PropertyListViewModel
    private lateinit var photos : MutableList<Photo>
    private var propertyId : Int = 0

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

        propertyId = intent.getIntExtra(PROPERTY_ID, 1)
        Timber.d("property id is $propertyId")
        photos = mutableListOf()

        configureViewModel(propertyId)
        adapterDetail = DetailAdapter(photos)

        configureRecyclerView()
    }

    @OnClick(R.id.button_update)
    fun updateButton(){
        intent = Intent(this, AddPropertyActivity::class.java)
        intent.putExtra(PROPERTY_ID, propertyId)
        startActivity(intent)
    }

    private fun configureViewModel(propertyId : Int){
        val factory = Injection.provideViewModelFactory()
        photoViewModel = ViewModelProvider(this, factory).get(PhotoListViewModel::class.java)
        photoViewModel.getPhotoToDisplay(propertyId).observe(this, Observer { liste -> updatePhotos(liste) })

        propertyViewModel = ViewModelProvider(this, factory).get(PropertyListViewModel::class.java)
        propertyViewModel.getPropertyById(propertyId).observe(this, Observer { property -> updateProperty(property) })
    }

    private fun updatePhotos(liste: List<Photo>) {
        photos.clear()
        photos.addAll(liste)
        adapterDetail.notifyDataSetChanged()
        Timber.d("liste photo VM = ${photos.size}, $photos")
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
        text_price.text = resources.getString(R.string.devise, property.price)
        text_description.text = property.description
        text_description.movementMethod = ScrollingMovementMethod()
        property_area.text = property.area.toString()
        property_address.text = property.address
        property_room.text = property.roomNumber.toString()
        property_bathroom.text = property.bathroomNumber.toString()
        property_bedroom.text = property.bedroomNumber.toString()
        entry_date.text = property.entryDate
        showCompromiseAndSoldText(property.compromiseDate, compromise_date, layout_compromise)
        showCompromiseAndSoldText(property.sellDate, sold_date, layout_sold)
    }

    private fun showCompromiseAndSoldText(text : String?, textView : TextView, layout : LinearLayout){
        if (text != null && text != ""){
            layout.visibility = View.VISIBLE
            textView.text = text
        }
    }

}
