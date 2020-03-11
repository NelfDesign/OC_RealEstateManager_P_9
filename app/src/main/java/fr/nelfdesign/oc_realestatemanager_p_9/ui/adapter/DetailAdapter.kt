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
class DetailAdapter(private val photos : List<Photo>, private val listener : DetailAdapter.onClickItemListener)
    : RecyclerView.Adapter<DetailAdapter.ViewHolder>(), View.OnClickListener {

    interface onClickItemListener{
        fun onClickItem(image : Photo)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.image_detail!!
        var textDetail = itemView.text_image_detail!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        val resources: Resources = holder.itemView.resources

        with(holder) {
            image.tag = photo
            image.setOnClickListener(this@DetailAdapter)
            textDetail.text = photo.name
            if (photo.urlPhoto.contains("images")) {
                Glide.with(holder.itemView)
                    .load(photo.urlPhoto)
                    .into(image)
            } else {
                val intPhoto = resources.getIdentifier(photo.urlPhoto, "drawable", "fr.nelfdesign.oc_realestatemanager_p_9")
                Glide.with(holder.itemView)
                    .load(intPhoto)
                    .into(image)
            }
        }
    }
    override fun getItemCount() = photos.size

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.image_detail -> listener.onClickItem(v.tag as Photo)
        }
    }
}