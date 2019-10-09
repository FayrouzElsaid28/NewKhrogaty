package roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_restaurants_only.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.base.extensions.makeLongToast
import roqay.task.newkhrogaty.base.extensions.openActivityFromParent
import roqay.task.newkhrogaty.view.features.details.DetailsActivity
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory
import roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.RestaurantsDetailsAdapter
import roqay.task.newkhrogaty.view.features.languageSelection.ILanguage

class RestaurantsOnlyFragment : Fragment(),
    AdapterToViewCallBack,
    ICategory,
    ILanguage{

    private val restaurantAdapter =
        RestaurantsDetailsAdapter(this,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurants_only, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restaurants_only_recyclerview.layoutManager = LinearLayoutManager(context)
        restaurants_only_recyclerview.adapter = restaurantAdapter
        getRestaurants()
        updateView()
    }

    private fun getRestaurants() {
        when(Category.restaurantsList.size){
            0 -> makeLongToast("No restaurants found")
            else -> restaurantAdapter.setData(Category.restaurantsList)
        }
    }

    override fun getContext(): Context {
        return activity?.applicationContext!!
    }

    override fun openActivity() {
        openActivityFromParent(DetailsActivity::class.java)
    }

    override fun updateView() {
        when (getSharedPreferences(activity?.applicationContext!!).getString("applicationLanguage", "")) {
            "ar" -> {
                content.rotationY = 180f
            }
            "en" -> {
                content.rotationY = 0f
            }
        }
    }

}
