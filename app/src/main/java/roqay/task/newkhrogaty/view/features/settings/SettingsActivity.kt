package roqay.task.newkhrogaty.view.features.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_settings.*
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.changeLang
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.base.extensions.openActivity
import roqay.task.newkhrogaty.view.features.languageSelection.ILanguage
import roqay.task.newkhrogaty.view.splash.SplashActivity


class SettingsActivity : AppCompatActivity(), ILanguage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initView()
    }

    private fun initView(){
        updateView()
        back.setOnClickListener { onBackPressed() }

        english_btn.setOnClickListener {
            changeLanguage("en")
        }
        arabic_btn.setOnClickListener {
            changeLanguage("ar")
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val lang = getSharedPreferences(newBase).getString("applicationLanguage", "")
        val context = changeLang(newBase, lang!!)
        super.attachBaseContext( context )
    }

    override fun changeLanguage(language: String){
        getSharedPreferences(applicationContext).edit().putString("applicationLanguage",language).apply()
        openActivity(this,SplashActivity::class.java)
        finishAffinity()
    }

    override fun updateView() {
        when(getSharedPreferences(applicationContext).getString("applicationLanguage","")){
            "en" -> {
                back.scaleX = 1f
                arabic_btn.gravity = Gravity.END or Gravity.CENTER
                english_btn.gravity = Gravity.START or Gravity.CENTER
            }
            "ar" -> {
                back.scaleX = -1f
                arabic_btn.gravity = Gravity.START or Gravity.CENTER
                english_btn.gravity = Gravity.END or Gravity.CENTER
            }
        }
    }

}
