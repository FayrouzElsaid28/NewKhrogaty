package roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_coffee_shops_only.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.base.extensions.makeLongToast
import roqay.task.newkhrogaty.base.extensions.openActivtyFromParent
import roqay.task.newkhrogaty.view.features.details.DetailsActivity
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory
import roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.RestaurantsDetailsAdapter

class CoffeeShopsOnlyFragment : Fragment(),
    AdapterToViewCallBack,
    ICategory {

    private val restaurantAdapter =
        RestaurantsDetailsAdapter(this,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coffee_shops_only, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coffee_shops_only_recyclerview.layoutManager = LinearLayoutManager(context)
        coffee_shops_only_recyclerview.adapter = restaurantAdapter
        getCoffeShops()

    }

    private fun getCoffeShops() {
        when(Category.restaurantsList.size){
            0 -> makeLongToast("No restaurants found")
            else -> restaurantAdapter.setData(Category.restaurantsList)
        }
    }

    override fun getContext(): Context {
        return activity?.applicationContext!!
    }

    override fun openActivity() {
        openActivtyFromParent(DetailsActivity::class.java)
    }

}
