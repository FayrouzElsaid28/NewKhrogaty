package roqay.task.newkhrogaty.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import roqay.task.khrogaty.base.helpers.LaunchingActivity
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.openActivity
import roqay.task.newkhrogaty.view.features.home.HomeActivity
import roqay.task.newkhrogaty.view.features.languageSelection.LanguageSelectionFragment

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
                openActivity(this, HomeActivity::class.java)
                finishAffinity()
            }
        }, 1000)
    }


    private fun isFirstLaunch() : Boolean {
        return LaunchingActivity.getInstance(applicationContext).isFirstLaunch()
    }
}
