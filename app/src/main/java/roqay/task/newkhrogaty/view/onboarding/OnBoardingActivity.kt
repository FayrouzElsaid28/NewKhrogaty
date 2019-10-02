package roqay.task.newkhrogaty.view.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_on_boarding.*
import roqay.task.khrogaty.base.helpers.LaunchingActivity
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.INavigation
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.base.extensions.loadLocals
import roqay.task.newkhrogaty.base.extensions.openActivity
import roqay.task.newkhrogaty.base.extensions.setLocale
import roqay.task.newkhrogaty.view.features.home.HomeActivity

class OnBoardingActivity : AppCompatActivity(), INavigation {

    var currentFragment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        /*
        Application is opened -- disable first launch
         */
        LaunchingActivity.getInstance(applicationContext).setIsFirstLaunchToFalse()
        initView()
    }

    private fun initView() {
        onboarding_viewpager.adapter = OnBoardingPagerAdapter(supportFragmentManager)
        handleViewPager()
        handleNavigation()
    }

    override fun handleNavigation() {
        next_tv.setOnClickListener {
            currentFragment++
            onboarding_viewpager.setCurrentItem(currentFragment, true)
        }
        prev_tv.setOnClickListener {
            currentFragment--
            onboarding_viewpager.setCurrentItem(currentFragment, true)
        }
        start_tv.setOnClickListener {
            openActivity(this, HomeActivity::class.java)
            finishAffinity()
        }
    }

    override fun handleViewPager() {
        onboarding_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
        if (currentFragment == 0){
            prev_tv.visibility = View.GONE
            start_tv.visibility = View.GONE
            next_tv.visibility = View.VISIBLE
        }
        else if (currentFragment == 1){
            prev_tv.visibility = View.VISIBLE
            start_tv.visibility = View.GONE
            next_tv.visibility = View.VISIBLE
        }else{
            prev_tv.visibility = View.VISIBLE
            start_tv.visibility = View.VISIBLE
            next_tv.visibility = View.GONE
        }
    }
}
