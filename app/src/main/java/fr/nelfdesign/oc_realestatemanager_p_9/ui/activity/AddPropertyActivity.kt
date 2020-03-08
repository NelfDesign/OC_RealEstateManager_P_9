package fr.nelfdesign.oc_realestatemanager_p_9.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseActivity
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.Injection
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PhotoListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.propertylist.PropertyListViewModel
import fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter.DetailAdapter
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils.*
import kotlinx.android.synthetic.main.activity_addproperty.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

class AddPropertyActivity : BaseActivity() {

    //FIELDS
    private lateinit var type: String
    private lateinit var status: String
    private lateinit var address: String
    private lateinit var description: String
    private lateinit var adapterDetail: DetailAdapter
    private lateinit var photos: MutableList<Photo>
    private lateinit var propertyViewModel : PropertyListViewModel
    private lateinit var photoViewModel : PhotoListViewModel
    private lateinit var uri: Uri
    private lateinit var image: Photo

    private var area: Int = 0
    private var rooms: Int = 0
    private var price: Int = 0
    private var bedrooms: Int = 0
    private var bathrooms: Int = 0
    private var imageDescription: String = ""

    companion object {
        private const val RESULT_CAMERA_CODE = 20
        private const val RESULT_GALLERY_CODE = 30
    }

    override fun getActivityLayout(): Int {
        return R.layout.activity_addproperty
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.configureToolBar("Add property")

        photos = mutableListOf()

        adapterDetail = DetailAdapter(photos)

        configureRecyclerView()
        configureSpinnerType()
        configureSpinnerStatus()

        val factory = Injection.provideViewModelFactory()
        propertyViewModel = ViewModelProvider(this, factory).get(PropertyListViewModel::class.java)
        photoViewModel = ViewModelProvider(this, factory).get(PhotoListViewModel::class.java)
    }

    @OnClick(R.id.fab, R.id.camera, R.id.gallery)
     fun onClickBottomNavigation(view: View) {
        when(view.id){
            R.id.fab -> checkPropertyInformation()
            R.id.camera -> checkPermissionsCamera()
            R.id.gallery -> checkPermissionsGallery()
        }
    }

    private fun configureRecyclerView() {
        val linear = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_image.apply {
            layoutManager = linear
            itemAnimator = DefaultItemAnimator()
            adapter = adapterDetail
        }
    }

    private fun configureSpinnerStatus() {
       configureSpinnerType(this, R.array.status, spinner_status)
        spinner_status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text = parent?.getItemAtPosition(position).toString()
                status = text
            }
        }
    }

    private fun configureSpinnerType() {
        configureSpinnerType(this, R.array.type, spinner_type)
        spinner_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text = parent?.getItemAtPosition(position).toString()
                type = text
            }
        }
    }

    private fun checkPropertyInformation() {
        if (!checkEditTextInput(card_address.text.toString()) || !checkEditTextInput(card_description.text.toString())
            || !checkEditTextInput(text_area.text.toString())|| !checkEditTextInput(card_rooms.text.toString())
            || !checkEditTextInput(card_bedroom.text.toString())|| !checkEditTextInput(card_bathroom.text.toString())
            || !checkEditTextInput(card_price.text.toString())) {

            makeSnackbar(constraint_add, getString(R.string.error_message_add))

        }else{
            address = card_address.text.toString()
            description = card_description.text.toString()
            area = Integer.parseInt(text_area.text.toString())
            rooms = Integer.parseInt(card_rooms.text.toString())
            bedrooms = Integer.parseInt(card_bedroom.text.toString())
            bathrooms = Integer.parseInt(card_bathroom.text.toString())
            price = Integer.parseInt(card_price.text.toString())

            updateBddWithNewProperty()
        }

    }

    private fun updateBddWithNewProperty() {
        val property = Property(0, type, price, area, rooms, bedrooms, bathrooms, description,
            photos[0].urlPhoto, address,null, status, getTodayDate(), null, 1)

        propertyViewModel.createProperty(property, photos)
        //TODO recupérer l'id de la propriete
        photoViewModel.insertPhotos(photos)
        finish()
    }

    private fun openGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, RESULT_GALLERY_CODE)
    }


    private fun openCamera() {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, "New Picture")
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "From the camera")
        uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!

        //Intent for camera
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, RESULT_CAMERA_CODE)
    }

    // ***************************
    // PERMISSIONS
    // ***************************

    private fun checkPermissionsGallery() {
        Dexter
            .withActivity(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    openGallery()
                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                    Timber.d("Permission Rationale")
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Timber.d("Permission Denied")
                }
            })
            .check()
    }

    private fun checkPermissionsCamera() {
        Dexter
            .withActivity(this)
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    openCamera()
                }

                override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                    Timber.d("Permission Rationale")
                }
            })
            .check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            RESULT_CAMERA_CODE -> showDescriptionDialog(uri.toString())
            RESULT_GALLERY_CODE -> showDescriptionDialog(data?.data.toString())
            else ->  super.onActivityResult(requestCode, resultCode, data)
        }
    }

    /**
     * show a dialog to enter photo  description
     */
    private fun showDescriptionDialog(path : String) {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.photo_name))

        val dialogView = LayoutInflater.from(this).inflate(R.layout.description_layout, viewGroup, false)
        val descriptionEditText = dialogView.findViewById<EditText>(R.id.description_field)

        builder.setView(dialogView)
        builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
            imageDescription = descriptionEditText.text.toString()

            image = Photo(0, path, imageDescription, 0)
            photos.add(image)

            adapterDetail.notifyDataSetChanged()
        }

        builder.show()
    }

}
