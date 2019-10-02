package roqay.task.newkhrogaty.base.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import roqay.task.newkhrogaty.base.helpers.Location

/**
 * Extensions for reducing code inside fragments
 */
fun <T> Fragment.openActivtyFromParent(cls: Class<T>) {
    activity?.openActivity(activity!!, cls)
}

fun Fragment.makeLongToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

fun Fragment.getCategoryLatLng(url: String): LatLng {
    if (url.contains('@')) {
        val removePrefix = url.split('@')
        val latlinSplit = removePrefix[1].split(',')
        val latitude = latlinSplit[0].toDouble()
        val longitude = latlinSplit[1].toDouble()
        val categoryLatLng = LatLng(latitude, longitude)

        return categoryLatLng
    } else {
        return LatLng(Location.latitude, Location.longitude)
    }
}
