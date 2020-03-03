package fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.nelfdesign.oc_realestatemanager_p_9.R
import fr.nelfdesign.oc_realestatemanager_p_9.models.Photo
import kotlinx.android.synthetic.main.item_photo_detail.view.*


/**
 * Created by Nelfdesign at 29/02/2020
 * fr.nelfdesign.oc_realestatemanager_p_9.ui.adapter
 */
class DetailAdapter(private val photos : List<Photo>) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.image_detail!!
        var textDetail = itemView.text_image_detail!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo =photos[position]
        val resources: Resources = holder.itemView.resources
        val intPhoto = resources.getIdentifier(photo.urlPhoto, "drawable","fr.nelfdesign.oc_realestatemanager_p_9")
        with(holder){
          Glide.with(holder.itemView)
               .load(intPhoto)
               .into(image)
           textDetail.text = photo.name
        }
    }

    override fun getItemCount() = photos.size
}