package com.jpoh281.lock_screen_diary

import io.flutter.app.FlutterApplication;
import io.flutter.embedding.engine.FlutterEngineGroup

class App : FlutterApplication(){

    lateinit var engines: FlutterEngineGroup

    override fun onCreate() {
        super.onCreate()
        engines = FlutterEngineGroup(this)
    }
}