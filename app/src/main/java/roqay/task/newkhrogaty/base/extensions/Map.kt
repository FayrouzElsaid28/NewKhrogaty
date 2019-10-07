package roqay.task.newkhrogaty.base.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import roqay.task.newkhrogaty.base.helpers.Location

fun Activity.setLocationData(locationManager: LocationManager) {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
        )

    } else {
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        val location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)

        if (location != null) {
            Location.latitude = location.latitude
            Location.longitude = location.longitude
        } else if (location1 != null) {
            Location.latitude = location1.latitude
            Location.longitude = location1.longitude
        } else if (location2 != null) {
            Location.latitude = location2.latitude
            Location.longitude = location2.longitude
        } else {
            Toast.makeText(this, "Unable to trace your location", Toast.LENGTH_SHORT).show()
        }

    }
}

fun Activity.buildAlertMessageNoGps(locationManager: LocationManager) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage("Please Turn ON your GPS Connection")
        .setCancelable(false)
        .setPositiveButton("Yes") { _, _ ->
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            setLocationData(locationManager)
        }
        .setNegativeButton("No", null)
        .create()
        .show()
}

fun Activity.getCurrentLocation(locationManager: LocationManager){
    var manager = locationManager
    manager = (getSystemService(Context.LOCATION_SERVICE) as LocationManager?)!!
    if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        buildAlertMessageNoGps(manager)

    } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        setLocationData(manager)
    }
}