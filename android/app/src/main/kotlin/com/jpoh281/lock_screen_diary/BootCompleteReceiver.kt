package com.jpoh281.lock_screen_diary

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

/*
    다시 켜졌을 때 version이 높으면 Forground Service
    낮으면 그냥 서비스를 실행
 */
class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when {
            intent?.action == Intent.ACTION_BOOT_COMPLETED -> {
                val intent = Intent(context, NewLockScreenService::class.java)
                context?.let {
//                    val pref = PreferenceManager.getDefaultSharedPreferences(context)
//                    val useLockScreen = pref.getBoolean("useLockScreen", false)
//                    if (useLockScreen){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        it.startForegroundService(intent)
                    } else {
                        it.startService(intent)
                    }
//                    }

                }


            }
        }


    }
}