package com.jpoh281.lock_screen_diary

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log

class NewLockScreenService : Service() {

    var receiver: ScreenOffReceiver? = null
    private val ANDROID_CHANNEL_ID = "com.lock.quizlocker"
    private val NOTIFICATION_ID = 9999

    override fun onCreate() {
        super.onCreate()
        Log.d("======LockScreenService", "onCreate")
        if (receiver == null) {
            receiver = ScreenOffReceiver()
            val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
            registerReceiver(receiver, filter)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)


        if (intent != null) {
            if (intent.action == null) {
                if (receiver == null) {
                    receiver = ScreenOffReceiver()
                    val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
                    registerReceiver(receiver, filter)
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val chan = NotificationChannel(ANDROID_CHANNEL_ID, "MyService", NotificationManager.IMPORTANCE_DEFAULT)
////            chan.lightColor = Color.BLUE
////            chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
//
//            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            manager.createNotificationChannel(chan)
//
//            val builder = Notification.Builder(this, ANDROID_CHANNEL_ID)
//                    .setContentTitle("앱 명")
//                    .setContentText("SmartTracker Running")
//            val notification = builder.build()

//            startForeground(NOTIFICATION_ID, notification)

            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(ANDROID_CHANNEL_ID, "잠금화면 테스트", NotificationManager.IMPORTANCE_DEFAULT)

            nm.createNotificationChannel(channel)

            val pending = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)

            val notification = Notification.Builder(this, ANDROID_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("잠금화면일기")
                    .setContentText("잠금화면 서비스 동작중")
                    .setContentIntent(pending)
                    .build()

            startForeground(1, notification)
        }

        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("======LockScreenService", "onDestroy")
        if (receiver != null) {
            unregisterReceiver(receiver)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}