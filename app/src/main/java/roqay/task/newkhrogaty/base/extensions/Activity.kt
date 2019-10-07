
package roqay.task.newkhrogaty.base.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.widget.Toast
import androidx.appcompat.widget.ViewUtils
import androidx.core.text.TextUtilsCompat
import java.util.*

/**
 * Extensions for reducing code inside activities
 */

private const val PREFERENCE_NAME = "roqay.task"

fun <T> Activity.openActivity(context: Context, cls: Class<T>) {
    startActivity(Intent(context, cls))
}

fun <T> Activity.openActivityClearStack(context: Context, cls: Class<T>) {
    startActivity(Intent(context, cls).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
}

fun Activity.makeLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun loadLocals(context: Context){
    val prefs: SharedPreferences = context.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
    val language: String = prefs.getString("Language", "")!!
    setLocale(context, language)
}

fun setLocale(context: Context, lang: String) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    //context.createConfigurationContext(config)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
    val editor: SharedPreferences.Editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
    editor.putString("Language", lang)
    editor.apply()
}

fun Activity.getSharedPreferences(): SharedPreferences =
    getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
