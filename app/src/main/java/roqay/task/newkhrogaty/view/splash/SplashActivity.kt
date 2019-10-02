package roqay.task.newkhrogaty.view.splash

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import roqay.task.khrogaty.base.helpers.LaunchingActivity
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.base.extensions.loadLocals
import roqay.task.newkhrogaty.base.extensions.openActivity
import roqay.task.newkhrogaty.base.extensions.setLocale
import roqay.task.newkhrogaty.view.features.home.HomeActivity
import roqay.task.newkhrogaty.view.splash.languageSelection.LanguageSelectionFragment
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //TODO:: set language
        selectActivity()
    }

    //Check first launch
    private fun selectActivity(){
        Handler().postDelayed({
            if(isFirstLaunch()) {
                LanguageSelectionFragment().show(supportFragmentManager,"")
            }
            else {
                //get language
                loadLocals(baseContext)

                openActivity(this, HomeActivity::class.java)
                finishAffinity()
            }
        }, 1000)
    }


    private fun isFirstLaunch() : Boolean {
        return LaunchingActivity.getInstance(applicationContext).isFirstLaunch()
    }
}
