package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseFragment
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.Injection
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PhotoListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PropertyListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.DetailProperty
import fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter.PropertyListAdapter
import kotlinx.android.synthetic.main.fragment_property_list.*
import timber.log.Timber

/**
 *
 */
class PropertyListFragment : BaseFragment(), PropertyListAdapter.PropertyListAdapterListener {

    private lateinit var viewModel: PropertyListViewModel
    private lateinit var photoViewModel: PhotoListViewModel
    private lateinit var propertyListAdapter : PropertyListAdapter
    private lateinit var properties : MutableList<Property>
    private lateinit var photos : MutableList<Photo>
    private val propertyListToDisplay = ArrayList<Property>()

    override fun getFragmentLayout(): Int {
       return R.layout.fragment_property_list
    }

    override fun configureDesign() {
        val factory = Injection.provideViewModelFactory()
        photoViewModel = ViewModelProvider(this, factory).get(PhotoListViewModel::class.java)
        viewModel = ViewModelProvider(this, factory).get(PropertyListViewModel::class.java)

        photoViewModel.photos.observe(viewLifecycleOwner, Observer { photoList -> updatePhotos(photoList) })
        viewModel.properties.observe(viewLifecycleOwner, Observer { newProperty -> updateProperty(newProperty) })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = mutableListOf()
        photos = mutableListOf()
        propertyListAdapter = PropertyListAdapter(propertyListToDisplay, this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_property.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = propertyListAdapter
        }

    }

    private fun updatePhotos(photoList : List<Photo>) {
        photos.clear()
        photos.addAll(photoList)
        Timber.d("photoList 1 = $photos")
    }

    private fun updateProperty(newProperty : List<Property>){
        properties.clear()
        properties.addAll(newProperty)
        Timber.d("List of property 2 =  $properties")
        getPhotoForProperty(photos)
        Timber.d("List of propertyToDisplay 3  = ${propertyListToDisplay.size},  $propertyListToDisplay")
        propertyListAdapter.notifyDataSetChanged()
    }

    /**
     * add a photo to property list
     */
    private fun getPhotoForProperty(photosList : List<Photo>){
        propertyListToDisplay.clear()
        for (p in properties){
            for (photo in photosList){
                if (p.id == photo.propertyId && p.photo == null){
                    p.photo = photo.urlPhoto
                    propertyListToDisplay.add(p)
                }
            }
        }

    }

    override fun  onPropertySelected(property : Property) {
        val intent = Intent(context, DetailProperty::class.java)
        intent.putExtra(DetailProperty.PROPERTY_ID, property.id)
        startActivity(intent)
    }

}
