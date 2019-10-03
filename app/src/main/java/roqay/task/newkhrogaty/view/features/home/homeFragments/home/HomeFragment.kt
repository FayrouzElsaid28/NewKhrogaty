package roqay.task.newkhrogaty.view.features.home.homeFragments.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.openActivity
import roqay.task.newkhrogaty.base.extensions.openActivtyFromParent
import roqay.task.newkhrogaty.view.features.settings.SettingsActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settings_img.setOnClickListener {
            openActivtyFromParent(SettingsActivity::class.java)
        }
    }

}
