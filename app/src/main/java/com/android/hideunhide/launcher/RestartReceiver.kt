package com.android.hideunhide.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class RestartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(MainActivity.TAG, "onReceive: RestartReceiver")
    }
}
