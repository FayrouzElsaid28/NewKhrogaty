package roqay.task.newkhrogaty.view.features.details

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.view.BlankFragment
import roqay.task.newkhrogaty.view.features.details.fragments.about.AboutFragment
import roqay.task.newkhrogaty.view.features.details.fragments.map.MapFragment

class DetailsPagerAdapter(fm: FragmentManager, activityContext: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    val context: Context? = activityContext

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AboutFragment()
            1 -> MapFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> context?.resources?.getString(R.string.about)
            1 -> context?.resources?.getString(R.string.map)
            else -> "null"
        }
    }
}