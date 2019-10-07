package roqay.task.newkhrogaty.view.features.home.homeFragments.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory

class SearchAdapter(private val callBack: AdapterToViewCallBack,
                    private val categoryCallCallback: ICategory
):
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var searchList = ArrayList<Search>()

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
        return searchList.size
    }

    fun setData(newData: ArrayList<Search>){
        searchList = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image_url = searchList[position].search_img_url
        Picasso.with(callBack.getContext()).load(image_url).into(holder.category_img)
        holder.category_name.text = searchList[position].search_name
        holder.category_place.text = searchList[position].search_place
        holder.category_details.text = searchList[position].search_information
        holder.details_tv.setOnClickListener {
            setDetailsData(searchList[position])
            categoryCallCallback.openActivity()
        }
    }

    private fun setDetailsData(search: Search) {
        Category.details_name = search.search_name
        Category.details_email = search.search_email
        Category.details_img_url = search.search_img_url
        Category.details_information = search.search_information
        Category.details_map_location_url = search.search_map_location_url
        Category.details_phone = search.search_phone
        Category.details_place = search.search_place
        Category.post_id = search.search_post_id
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category_img = itemView.findViewById<ImageView>(R.id.category_img)
        var category_name = itemView.findViewById<TextView>(R.id.category_name)
        var category_place = itemView.findViewById<TextView>(R.id.category_place)
        var category_details = itemView.findViewById<TextView>(R.id.category_details)
        var details_tv = itemView.findViewById<TextView>(R.id.details_tv)
    }
}