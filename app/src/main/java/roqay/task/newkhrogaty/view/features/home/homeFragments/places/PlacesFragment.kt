package roqay.task.newkhrogaty.view.features.home.homeFragments.places


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_places.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.AdapterToViewCallBack
import roqay.task.newkhrogaty.base.extensions.makeLongToast
import roqay.task.newkhrogaty.base.extensions.openActivityFromParent
import roqay.task.newkhrogaty.view.features.details.DetailsActivity
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.home.homeFragments.ICategory

class PlacesFragment : Fragment(),
    AdapterToViewCallBack,
    ICategory {

    val adapter = PlacesDetailsAdapter(this,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        places_recyclerview.layoutManager = LinearLayoutManager(context)
        places_recyclerview.adapter = adapter
        getPlaces()
    }

    private fun getPlaces() {
        when(Category.placesList.size){
            0 -> makeLongToast("No Places found")
            else -> adapter.setData(Category.placesList)
        }
    }

    override fun getContext(): Context {
        return activity?.applicationContext!!
    }

    override fun openActivity() {
        openActivityFromParent(DetailsActivity::class.java)
    }
}
