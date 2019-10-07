package roqay.task.newkhrogaty.view.features.home.homeFragments.home.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import roqay.task.khrogaty.models.activity.Activity
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory

class ToDoAdapter(private val callBack: AdapterToViewCallBack,
                  private val categoryCallCallback: ICategory
):
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    var todoList = ArrayList<Activity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(newData: ArrayList<Activity>){
        todoList = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image_url = todoList[position].better_featured_image.source_url
        Picasso.with(callBack.getContext()).load(image_url).into(holder.category_img)
        holder.category_name.text = todoList[position].title.rendered
        holder.category_place.text = todoList[position].acf.address

        holder.item_category.setOnClickListener {
            setDetailsData(todoList[position])
            categoryCallCallback.openActivity()
        }
    }

    private fun setDetailsData(activity: Activity) {
        Category.details_name = activity.title.rendered
        Category.details_email = activity.acf.email_address
        Category.details_img_url = activity.better_featured_image.source_url
        Category.details_information = activity.excerpt.rendered
        Category.details_map_location_url = activity.acf.map_location
        Category.details_phone = activity.acf.phone_number
        Category.details_place = activity.acf.address
        Category.post_id = activity.better_featured_image.post
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category_img = itemView.findViewById<ImageView>(R.id.category_img)
        var category_name = itemView.findViewById<TextView>(R.id.category_name)
        var category_place = itemView.findViewById<TextView>(R.id.category_place)
        var item_category = itemView.findViewById<ConstraintLayout>(R.id.item_category)
    }
}