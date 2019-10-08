@file:Suppress("DEPRECATION", "NAME_SHADOWING")

package roqay.task.newkhrogaty.base.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import java.util.*
import android.os.Build
import androidx.preference.PreferenceManager


/**
 * Extensions for reducing code inside activities
 */

fun <T> Activity.openActivity(context: Context, cls: Class<T>) {
    startActivity(Intent(context, cls))
}

fun Activity.makeLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@SuppressLint("ObsoleteSdkInt")
fun changeLang(context: Context, lang_code: String): ContextWrapper {
    var context = context
    val sysLocale: Locale

    val rs = context.resources
    val config = rs.configuration

    sysLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        config.locales.get(0)
    } else {
        config.locale
    }
    if (lang_code != "" /*&& sysLocale.language != lang_code*/) {
        val locale = Locale(lang_code)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
            config.setLayoutDirection(locale)
        } else {
            config.setLocale(locale)
            config.setLayoutDirection(locale)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context = context.createConfigurationContext(config)
            config.setLayoutDirection(locale)
        } else {
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
    }

    return ContextWrapper(context)
}

fun getSharedPreferences(context: Context): SharedPreferences =
    PreferenceManager.getDefaultSharedPreferences(context)
