package roqay.task.newkhrogaty.view.features.home.homeFragments.restaurants.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import roqay.task.newkhrogaty.R

/**
 * A simple [Fragment] subclass.
 */
class RestaurantsOnlyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurants_only, container, false)
    }


}
