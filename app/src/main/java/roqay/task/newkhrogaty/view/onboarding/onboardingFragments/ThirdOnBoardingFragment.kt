package roqay.task.newkhrogaty.view.onboarding.onboardingFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_third_on_boarding.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.view.features.languageSelection.ILanguage

class ThirdOnBoardingFragment : Fragment(), ILanguage {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateView()
    }

    override fun updateView() {
        when (getSharedPreferences(activity?.applicationContext!!).getString("applicationLanguage", "")) {
            "ar" -> {
                content.rotationY = 180f
            }
            "en" -> {
                content.rotationY = 0f
            }
        }
    }

}
