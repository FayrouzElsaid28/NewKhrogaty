package roqay.task.newkhrogaty.languageSelection


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_language_selection.*
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.base.extensions.openActivtyFromParent
import roqay.task.newkhrogaty.view.onboarding.OnBoardingActivity
import android.R.id.edit
import android.content.SharedPreferences
import android.preference.PreferenceManager



class LanguageSelectionFragment : DialogFragment(), ILanguage {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_language_selection, container, false)
        if (dialog != null && dialog?.window != null) {
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //To prevent dismissing fragment from outside
        dialog?.setCanceledOnTouchOutside(false)

        arabic_btn.setOnClickListener {
            changeLanguage("ar")
        }
        english_btn.setOnClickListener {
            changeLanguage("en")
        }
    }

    override fun changeLanguage(language: String) {
        getSharedPreferences().edit().putString("applicationLanguage",language).apply()
        openActivtyFromParent(OnBoardingActivity::class.java)
        activity?.finishAffinity()

        //TODO:: Send broadcast with language
    }

}
