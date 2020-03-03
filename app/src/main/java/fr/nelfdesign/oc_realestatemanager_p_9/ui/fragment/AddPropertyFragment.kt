package fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.base.BaseFragment
import fr.nelfdesign.oc_realestatemanager_p_9.ui.activity.PhotoActivity
import kotlinx.android.synthetic.main.fragment_add_property.*


/**
 *
 */
class AddPropertyFragment : BaseFragment() {

    //FIELDS
    lateinit var type: String
    lateinit var status : String
    lateinit var address : String
    lateinit var description : String

    private var area : Int = 0
    private var rooms : Int = 0
    private var price : Int = 0
    private var bedrooms : Int = 0
    private var bathrooms : Int = 0

    companion object {
        private const val ID_CAMERA = 1
        private const val ID_ADD = 2
        private const val ID_GALLERY = 3
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_add_property
    }

    override fun configureDesign() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureBottomNavigation()
        configureSpinnerType()
        configureSpinnerStatus()
    }

    private fun configureSpinnerStatus() {
        val adapter = ArrayAdapter.createFromResource(this.requireContext(), R.array.status, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_status.adapter = adapter
        spinner_status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text = parent?.getItemAtPosition(position).toString()
                status = text
            }
        }
    }

    private fun configureSpinnerType() {
        val adapter = ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.type,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_type.adapter = adapter
        spinner_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val text = parent?.getItemAtPosition(position).toString()
                type = text
            }
        }
    }

    private fun configureBottomNavigation() {
        bottom_Navigation.add(MeowBottomNavigation.Model(ID_CAMERA, R.drawable.ic_camera))
        bottom_Navigation.add(MeowBottomNavigation.Model(ID_ADD, R.drawable.ic_add_circle))
        bottom_Navigation.add(MeowBottomNavigation.Model(ID_GALLERY, R.drawable.ic_image))

        bottom_Navigation.setOnClickMenuListener {
            when (it.id) {
                ID_CAMERA -> openCamera()
                ID_ADD ->   addProperty()
                ID_GALLERY -> openGallery()
            }
        }
    }

    private fun openGallery() {
       startActivityPhoto("gallery")
    }

    private fun addProperty() {
        //address = card_address.text.toString()
        //description = card_description.text.toString()
        area = Integer.parseInt(text_area.text.toString())
       // rooms = Integer.parseInt(card_rooms.text.toString())
        bedrooms = Integer.parseInt(card_bedroom.text.toString())
       // bathrooms = Integer.parseInt(card_bathroom.text.toString())
       // price = Integer.parseInt(card_price.text.toString())

       Toast.makeText(this.requireContext(),"status = $status et type = $type, area = $area, bedrooms = $bedrooms", Toast.LENGTH_LONG).show()
    }

    private fun openCamera() {
        startActivityPhoto("camera")
    }

    private fun startActivityPhoto(text : String){
        val intent = Intent(this.requireContext(), PhotoActivity::class.java)
        intent.putExtra("tag", text)
        startActivity(intent)
    }

}
