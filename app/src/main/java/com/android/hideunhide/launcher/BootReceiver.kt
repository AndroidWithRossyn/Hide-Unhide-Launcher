package com.android.hideunhide.launcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Log.d(MainActivity.TAG, "onReceive: Boot completed")

            /*        val launchIntent = Intent(context, MainActivity::class.java)
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(launchIntent)*/
        }
    }
}