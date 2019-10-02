package roqay.task.newkhrogaty.base.helpers

import com.google.android.gms.maps.GoogleMap

/**
 * Values needed for current location and routing
 */
object Location {
    var latitude = 0.0
    var longitude = 0.0
    var mMap: GoogleMap? = null
    var lineColor: Int = -1
}