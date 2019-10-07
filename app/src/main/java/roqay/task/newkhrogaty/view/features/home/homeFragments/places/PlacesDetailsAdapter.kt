package roqay.task.newkhrogaty.view.features.home.homeFragments.places

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import roqay.task.khrogaty.models.place.Place
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory

class PlacesDetailsAdapter(private val callBack: AdapterToViewCallBack,
                           private val categoryCallCallback: ICategory
):
    RecyclerView.Adapter<PlacesDetailsAdapter.ViewHolder>() {

    var placesList = ArrayList<Place>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category_details,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    fun setData(newData: ArrayList<Place>){
        placesList = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image_url = placesList[position].better_featured_image.source_url
        Picasso.with(callBack.getContext()).load(image_url).into(holder.category_img)
        holder.category_name.text = placesList[position].title.rendered
        holder.category_place.text = placesList[position].acf.address
        holder.category_details.text = placesList[position].excerpt.rendered

        holder.details_tv.setOnClickListener {
            setDetailsData(placesList[position])
            categoryCallCallback.openActivity()
        }
    }

    private fun setDetailsData(place: Place) {
        Category.details_name = place.title.rendered
        Category.details_email = place.acf.email_address
        Category.details_img_url = place.better_featured_image.source_url
        Category.details_information = place.excerpt.rendered
        Category.details_map_location_url = place.acf.map_location
        Category.details_phone = place.acf.phone_number
        Category.details_place = place.acf.address
        Category.post_id = place.better_featured_image.post
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category_img = itemView.findViewById<ImageView>(R.id.category_img)
        var category_name = itemView.findViewById<TextView>(R.id.category_name)
        var category_place = itemView.findViewById<TextView>(R.id.category_place)
        var category_details = itemView.findViewById<TextView>(R.id.category_details)
        var details_tv = itemView.findViewById<TextView>(R.id.details_tv)
    }
}