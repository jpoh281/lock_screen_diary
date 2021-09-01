package com.jpoh281.lock_screen_diary

import android.app.Activity
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor

class EngineBindings(activity: Activity, entrypoint: String) {
    val engine: FlutterEngine

    init {
        val app = activity.applicationContext as App
        // This has to be lazy to avoid creation before the FlutterEngineGroup.
        val dartEntrypoint =
                DartExecutor.DartEntrypoint(
                        FlutterInjector.instance().flutterLoader().findAppBundlePath(), entrypoint
                )
        engine = app.engines.createAndRunEngine(activity, dartEntrypoint)
    }
}