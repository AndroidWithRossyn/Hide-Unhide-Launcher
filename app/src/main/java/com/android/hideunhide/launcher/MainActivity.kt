package com.android.hideunhide.launcher

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.hideunhide.launcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivityTAG"
    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d(TAG, "onCreate: ")

        val componentName = this.packageName
        binding.hideUnhideSwitch.setOnCheckedChangeListener { _, b ->
            if (b) {
                try {
                    val intent = packageManager.getLaunchIntentForPackage(componentName)
                    if (intent != null) {
                        packageManager.setApplicationEnabledSetting(
                            packageName,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP
                        )
//                    finish() // Close the activity
                    }
                } catch (e: Exception) {
//                    java.lang.SecurityException
                    Log.d(TAG, "Exception: enable ${e.message} ")
                }

            } else {
                try {
                    packageManager.setApplicationEnabledSetting(
                        componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP
                    )
                } catch (e: Exception) {
//                    java.lang.SecurityException
                    Log.d(TAG, "Exception: disable ${e.message} ")
                }

            }
        }


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    isEnabled = false
                    moveTaskToBack(true)
                }
            }
        })


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "onLowMemory: ")
    }

    override fun onDestroy() {
//        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}