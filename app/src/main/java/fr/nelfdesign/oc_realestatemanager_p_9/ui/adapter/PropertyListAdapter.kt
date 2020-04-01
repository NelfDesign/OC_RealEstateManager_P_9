package fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.models.Property
import fr.nelfdesign.oc_realestatemanager_p_9.ui.fragment.drawernavigation.PropertyListFragment
import fr.nelfdesign.oc_realestatemanager_p_9.utils.Utils
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
        val imageSold = itemView.image_sold!!
        val propertyType = itemView.text_type_property!!
        val propertyAddress = itemView.text_address!!
        val price = itemView.price_text!!
        val notComplete = itemView.not_complete!!
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
           propertyAddress.text = property.town
           val resources: Resources = holder.itemView.resources
           if (!property.complete) {
               notComplete.visibility = View.VISIBLE
               cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context , R.color.backgroundActivity))
           }

            if (property.sellDate != ""){
                imageSold.visibility = View.VISIBLE
            }

           if (PropertyListFragment.DEVISE == "dollars"){
               price.text = resources.getString(R.string.devise_dollars, Utils.formatNumber(property.price))
           }else{
               price.text = resources.getString(R.string.devise_euros, Utils.formatNumber(property.priceEuro))
           }

           if (property.photo.contains("document") || property.photo.contains("images")){
                   Glide.with(holder.itemView)
                       .load(property.photo)
                       .into(imageProperty)
           } else{
            val intPhoto = resources.getIdentifier(property.photo, "drawable","")
                   Glide.with(holder.itemView)
                       .load(intPhoto)
                       .into(imageProperty)
           }

       }
    }

    override fun getItemCount()  = properties.size

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.card_view_property -> listener.onPropertySelected(v.tag as Property)
        }
    }
}