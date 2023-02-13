package com.example.serviceapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {

    // indicates how to behave if the service is killed
    var  mStartMode: Int = 0

    // Interface for the client that bind
    lateinit var mIBinder: IBinder

    //indicate whether onRebind should be used
    var mAllowRebind: Boolean = false

    // called when the service is being created
    override fun onCreate() {
        super.onCreate()
    }

    // The service is starting on due to call to startService()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "Service started", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    // A client is binding to the service with bindService
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        return mIBinder
    }

    // called when all the client have unbound with unbindService()
    override fun onUnbind(intent: Intent?): Boolean {
        return mAllowRebind
    }

    // called when client is binding to the service with bindService()
    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    // called when the service is no longer used and is being destroy
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Service Destroy", Toast.LENGTH_SHORT).show()
    }
}