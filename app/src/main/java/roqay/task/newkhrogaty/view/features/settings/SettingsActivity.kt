package roqay.task.newkhrogaty.view.features.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.base.extensions.openActivity
import roqay.task.newkhrogaty.languageSelection.ILanguage
import roqay.task.newkhrogaty.view.splash.SplashActivity

class SettingsActivity : AppCompatActivity(), ILanguage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initView()
    }

    private fun initView(){
        back.setOnClickListener { onBackPressed() }

        english_btn.setOnClickListener {
            changeLanguage("en")
        }
        arabic_btn.setOnClickListener {
            changeLanguage("ar")
        }
    }

    override fun changeLanguage(language: String){
        getSharedPreferences().edit().putString("applicationLanguage",language).apply()
        openActivity(this,SplashActivity::class.java)
        finishAffinity()
    }
}
