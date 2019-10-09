package roqay.task.newkhrogaty.firebaseNotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import roqay.task.newkhrogaty.R
import roqay.task.newkhrogaty.view.features.home.HomeActivity

class MyFirebaseMessagingService: FirebaseMessagingService() {

    private val channelID = "CHANNEL_ID"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val notification = remoteMessage.notification
        val title = notification?.title
        val body = notification?.body

        createNotification(this, title!!, body!!)

        Log.d("notification-message",
            "$title $body"
        )

    }

    override fun onNewToken(token: String) {
        Log.d("token",token)
    }

    private fun createNotification(context: Context, title: String, message: String){
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val mBuilder = NotificationCompat.Builder(context,channelID)
            .setContentTitle(title)
            .setContentText(message)
            .setChannelId(channelID)
            .setSmallIcon(R.drawable.khrogaty_logo)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, mBuilder.build())
    }

}