package roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.view.BlankFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.fragments.AllRestaurantsFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.fragments.CoffeeShopsOnlyFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.fragments.RestaurantsOnlyFragment

class RestaurantsPagerAdapter(fm: FragmentManager, activityContext: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    val context: Context? = activityContext

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AllRestaurantsFragment()
            1 -> RestaurantsOnlyFragment()
            2-> CoffeeShopsOnlyFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context?.resources?.getString(R.string.all)
            1 -> context?.resources?.getString(R.string.restaurants)
            2 -> context?.resources?.getString(R.string.coffeeShops)
            else -> "null"
        }
    }
}