package roqay.task.newkhrogaty.view.features.home

import android.content.Context
import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.view.BlankFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.home.HomeFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.places.PlacesFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.RestaurantsFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.search.SearchFragment
import roqay.task.newkhrogaty.view.features.home.homeFragments.todo.ToDoFragment

class HomePagerAdapter(fm: FragmentManager, activityContext: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val context: Context? = activityContext

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> PlacesFragment()
            3 -> RestaurantsFragment()
            4 -> ToDoFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //get names from string resource
        return when(position){
            0 -> context?.resources?.getString(R.string.home)
            1 -> context?.resources?.getString(R.string.search)
            2 -> context?.resources?.getString(R.string.find_places)
            3 -> context?.resources?.getString(R.string.restaurants)
            4 -> context?.resources?.getString(R.string.things_to_do)
            else -> "null"
        }
    }
}