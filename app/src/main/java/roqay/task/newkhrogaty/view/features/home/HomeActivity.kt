package roqay.task.newkhrogaty.view.features.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.INavigation
import roqay.task.newkhrogaty.base.extensions.openActivity
import roqay.task.newkhrogaty.view.features.settings.SettingsActivity

class HomeActivity : AppCompatActivity(), INavigation {

    private var currentFragment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        //TODO:: Settings icon
    }

    private fun initView() {
        home_viewpager.adapter =
            HomePagerAdapter(supportFragmentManager)
        home_tablayout.setupWithViewPager(home_viewpager)

        settings_img.setOnClickListener {
            openActivity(this,SettingsActivity::class.java)
        }

        handleViewPager()
        handleTabIcons()
    }

    private fun handleTabIcons() {
        //Set tab with icons
        home_tablayout.getTabAt(0)?.setIcon(R.drawable.home)
        home_tablayout.getTabAt(1)?.setIcon(R.drawable.filter)
        home_tablayout.getTabAt(2)?.setIcon(R.drawable.find_places)
        home_tablayout.getTabAt(3)?.setIcon(R.drawable.restaurants)
        home_tablayout.getTabAt(4)?.setIcon(R.drawable.todo)
    }

    override fun handleViewPager() {
        home_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentFragment = position
                handleNavView(currentFragment)
            }

            override fun onPageSelected(position: Int) {

            }
        })
    }

    override fun handleNavView(id: Int) {
        when(id){
            0 -> {
                // Home fragment is selected
                home_tablayout.getTabAt(0)?.setIcon(R.drawable.ghome)

                home_tablayout.getTabAt(1)?.setIcon(R.drawable.filter)
                home_tablayout.getTabAt(2)?.setIcon(R.drawable.find_places)
                home_tablayout.getTabAt(3)?.setIcon(R.drawable.restaurants)
                home_tablayout.getTabAt(4)?.setIcon(R.drawable.todo)
            }
            1 -> {
                //Search fragment is selected
                home_tablayout.getTabAt(1)?.setIcon(R.drawable.gfilter)

                home_tablayout.getTabAt(0)?.setIcon(R.drawable.home)
                home_tablayout.getTabAt(2)?.setIcon(R.drawable.find_places)
                home_tablayout.getTabAt(3)?.setIcon(R.drawable.restaurants)
                home_tablayout.getTabAt(4)?.setIcon(R.drawable.todo)
            }
            2 -> {
                //Find places fragment is selected
                home_tablayout.getTabAt(2)?.setIcon(R.drawable.gfind_places)

                home_tablayout.getTabAt(0)?.setIcon(R.drawable.home)
                home_tablayout.getTabAt(1)?.setIcon(R.drawable.filter)
                home_tablayout.getTabAt(3)?.setIcon(R.drawable.restaurants)
                home_tablayout.getTabAt(4)?.setIcon(R.drawable.todo)
            }
            3 -> {
                //Restaurants fragment is selected
                home_tablayout.getTabAt(3)?.setIcon(R.drawable.grestaurants)

                home_tablayout.getTabAt(0)?.setIcon(R.drawable.home)
                home_tablayout.getTabAt(1)?.setIcon(R.drawable.filter)
                home_tablayout.getTabAt(2)?.setIcon(R.drawable.find_places)
                home_tablayout.getTabAt(4)?.setIcon(R.drawable.todo)
            }
            4 -> {
                //To Do fragment is selected
                home_tablayout.getTabAt(4)?.setIcon(R.drawable.gtodo)

                home_tablayout.getTabAt(0)?.setIcon(R.drawable.home)
                home_tablayout.getTabAt(1)?.setIcon(R.drawable.filter)
                home_tablayout.getTabAt(2)?.setIcon(R.drawable.find_places)
                home_tablayout.getTabAt(3)?.setIcon(R.drawable.restaurants)
            }
        }
    }
}
