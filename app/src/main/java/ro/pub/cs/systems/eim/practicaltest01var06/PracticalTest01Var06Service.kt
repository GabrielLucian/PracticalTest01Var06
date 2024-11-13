package ro.pub.cs.systems.eim.practicaltest01var06

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock.sleep
import android.util.Log

class PracticalTest01Var06Service : Service() {

    override fun onCreate() {
        super.onCreate()

        // send notification
        Log.d("PracticalTest01Var06Service", "onCreate() method was invoked")
        val CHANNEL_ID = "11";
        val CHANNEL_NAME = "Foreground Service Channel";
        val notification: Notification = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Pariuri")
                .setContentText("Victory!")
                .setAutoCancel(true)
                .build()
        } else {
            Notification.Builder(this)
                .setContentTitle("Pariuri")
                .setContentText("Victory!")
                .setAutoCancel(true)
                .build()
        }
        sleep(2000)
        startForeground(1, notification)
    }

    override fun onBind(intent: Intent): IBinder {
        return null as IBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("PracticalTest01Var06Service", "onStartCommand() method was invoked")
        return START_REDELIVER_INTENT
    }
}