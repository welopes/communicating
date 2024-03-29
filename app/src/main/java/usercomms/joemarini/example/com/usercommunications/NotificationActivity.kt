package usercomms.joemarini.example.com.usercommunications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        btnNotification.setOnClickListener(this)

        // For API 26 and later, we have to create a channel otherwise the notification
        // won't be displayed. This can be called multiple times without harm - if there's
        // already a channel with the given ID then the call is ignored
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFY_CHANNEL, "Notifications", importance)
            channel.description = "This is a notification channel"

            // Register the channel with the system
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun createNotification() {
        // create the NotificationCompat Builder
        val builder = NotificationCompat.Builder(this, NOTIFY_CHANNEL)

        // Create the intent that will start the ResultActivity when the user
        // taps the notification or chooses an action button
        val intent = Intent(this, NotificationResultActivity::class.java)
        // Store the notification ID so we can cancel it later in the ResultActivity
        intent.putExtra("notifyID", NOTIFY_ID)
        val pendingIntent = PendingIntent.getActivity(this, NOTIFY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        // Set the three required items all notifications must have
        builder.setSmallIcon(R.drawable.ic_stat_sample_notification)
        builder.setContentTitle("Sample Notification")
        builder.setContentText("This is a sample notification")

        // Set the notification to cancel when the user taps on it
        builder.setAutoCancel(true)

        // Set the large icon to be our app's launcher icon
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))

        // Set the small subtext message
        builder.setSubText("Tap to view")

        // Set the content intent to launch our result activity
        builder.setContentIntent(pendingIntent)

        // Add an expanded layout to the notification
        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle("This is a big notification")
        bigTextStyle.bigText(resources.getString(R.string.LongMsg))
        builder.setStyle(bigTextStyle)

        // Add action buttons to the Notification if they are supported
        // Use the same PendingIntent as we use for the main notification action
        builder.addAction(R.mipmap.ic_launcher, "Action 1", pendingIntent)
        builder.addAction(R.mipmap.ic_launcher, "Action 2", pendingIntent)

        // Set the lock screen visibility of the notification
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        // Build the finished notification and then display it to the user
        val notification = builder.build()
        val mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mgr.notify(NOTIFY_ID, notification)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnNotification) {
            createNotification()
        }
    }

    companion object {
        private const val NOTIFY_ID = 1001
        private const val NOTIFY_CHANNEL = "MY_CHANNEL"
    }
}
