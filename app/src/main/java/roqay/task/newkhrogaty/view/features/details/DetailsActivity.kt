package roqay.task.newkhrogaty.view.features.details

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.custom_tab.view.*
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.INavigation
import roqay.task.newkhrogaty.base.extensions.changeLang
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.view.features.details.fragments.IDetails
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category

class DetailsActivity : AppCompatActivity(),
    IDetails,
    INavigation {

    private var currentFragment = 0
    private var firstTab: ConstraintLayout? = null
    private var secondTab: ConstraintLayout? = null
    private var selectedColor = 0
    private var unselectedColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initView()
    }

    private fun initView() {
        details_viewpager.adapter = DetailsPagerAdapter(supportFragmentManager,this)
        details_back.setOnClickListener { onBackPressed() }

        details_tablayout.setupWithViewPager(details_viewpager)
        handleViewPager()
        handleTabLayout()
        getDetails()
       // getCurrentLocation()
    }

    @SuppressLint("InflateParams")
    private fun handleTabLayout() {
        //Set tabs back round to custom tab so that image can be at the right if text
        firstTab = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as ConstraintLayout
        secondTab = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as ConstraintLayout

        firstTab?.tab_txt?.text = this.resources?.getString(R.string.about)
        secondTab?.tab_txt?.text = this.resources?.getString(R.string.map)

        details_tablayout.getTabAt(0)?.customView = firstTab
        details_tablayout.getTabAt(1)?.customView = secondTab

        selectedColor = ContextCompat.getColor(applicationContext,R.color.black)
        unselectedColor = ContextCompat.getColor(applicationContext,R.color.nav_gray)
    }

    override fun handleViewPager() {
        details_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
                firstTab?.tab_img?.setImageResource(R.drawable.gabout)
                firstTab?.tab_txt?.setTextColor(selectedColor)

                secondTab?.tab_img?.setImageResource(R.drawable.grey_map_marker)
                secondTab?.tab_txt?.setTextColor(unselectedColor)
            }
            1 -> {
                firstTab?.tab_img?.setImageResource(R.drawable.about)
                firstTab?.tab_txt?.setTextColor(unselectedColor)

                secondTab?.tab_img?.setImageResource(R.drawable.map_marker)
                secondTab?.tab_txt?.setTextColor(selectedColor)
            }
        }
    }

    override fun getDetails() {
        details_name.text = Category.details_name
    }

    override fun attachBaseContext(newBase: Context) {
        val lang = getSharedPreferences(newBase).getString("applicationLanguage", "")
        val context = changeLang(newBase, lang!!)
        super.attachBaseContext( context )
    }
}
