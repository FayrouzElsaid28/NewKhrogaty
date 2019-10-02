package roqay.task.newkhrogaty.base.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Extensions for reducing code inside activities
 */
fun <T> Activity.openActivity(context: Context, cls: Class<T>) {
    startActivity(Intent(context, cls))
}

fun <T> Activity.openActivityClearStack(context: Context, cls: Class<T>) {
    startActivity(Intent(context, cls).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
}

fun Activity.makeLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

