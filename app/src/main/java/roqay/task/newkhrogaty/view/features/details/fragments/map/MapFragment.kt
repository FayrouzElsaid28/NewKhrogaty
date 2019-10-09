package roqay.task.newkhrogaty.view.features.details.fragments.map


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_map.*

import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.base.extensions.getCategoryLatLng
import roqay.task.newkhrogaty.base.extensions.getSharedPreferences
import roqay.task.newkhrogaty.base.helpers.Location
import roqay.task.newkhrogaty.base.helpers.Location.mMap
import roqay.task.newkhrogaty.view.features.details.fragments.IDetails
import roqay.task.newkhrogaty.view.features.home.homeFragments.Category
import roqay.task.newkhrogaty.view.features.languageSelection.ILanguage

class MapFragment : Fragment(),
    OnMapReadyCallback,
    IDetails,
    ILanguage{

    private val currentLatLng = LatLng(Location.latitude, Location.longitude)
    private var categoryLatLng: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetails()
        initMap()
        updateView()
    }

    override fun getDetails() {
        details_place.text = Category.details_place
        val details_map_url = Category.details_map_location_url
        categoryLatLng = getCategoryLatLng(details_map_url)
    }

    private fun initMap() {
        if (map != null) {
            map.onCreate(null)
            map.onResume()
            map.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap

        mMap?.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng))
        mMap?.animateCamera(CameraUpdateFactory.zoomTo(10f))
        mMap?.uiSettings?.isZoomControlsEnabled = true
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
