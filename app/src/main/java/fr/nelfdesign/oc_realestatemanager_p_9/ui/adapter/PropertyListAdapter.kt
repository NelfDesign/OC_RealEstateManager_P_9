package fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import kotlinx.android.synthetic.main.item_property.view.*


/**
 * Created by Nelfdesign at 26/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter
 */
class PropertyListAdapter(private val properties : List<Property>, private val listener : PropertyListAdapterListener)
    : RecyclerView.Adapter<PropertyListAdapter.ViewHolder>(), View.OnClickListener {

     interface PropertyListAdapterListener {
         fun onPropertySelected(property: Property)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.card_view_property!!
        val imageProperty = itemView.image_property!!
        val propertyType = itemView.text_type_property!!
        val propertyAddress = itemView.text_address!!
        val price = itemView.price_text!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val property = properties[position]

       with(holder){
           cardView.setOnClickListener(this@PropertyListAdapter)
           cardView.tag = property
           propertyType.text = property.type
           propertyAddress.text = property.address
           price.text = "$" + property.price.toString()

           Glide.with(this.cardView)
               .load(property.photo)
               .placeholder(R.drawable.ic_launcher_foreground)
               .into(imageProperty)
       }
    }

    override fun getItemCount()  = properties.size

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.card_view_property -> listener.onPropertySelected(v.tag as Property)
        }
    }
}