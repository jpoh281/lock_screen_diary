package com.jpoh281.lock_screen_diary

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

class MainActivity : FlutterActivity() {

    private val CHANNEL = "com.jpoh281.lock_screen_diary"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "checkPermission")
                result.success(checkPermission())
            if (call.method == "getPermission")
                getPermission()
            if (call.method == "startService")
                startService()
        }
    }

    fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            return false
        } else {
            return true
        }

    }

    fun startService() {
        val intent = Intent(applicationContext, NewLockScreenService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startForegroundService(intent)
        }else {
            startService(intent)
        }
    }

    fun getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val uri = Uri.fromParts("package", packageName, null)
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
                startActivityForResult(intent, 0)
            }
        }
    }
}
