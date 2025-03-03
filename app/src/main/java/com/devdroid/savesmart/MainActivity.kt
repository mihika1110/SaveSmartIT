package com.devdroid.savesmart

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.devdroid.savesmart.ui.theme.SaveSmartTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaveSmartTheme {
                SplashScreen(onTimeout = {
                    startActivity(Intent(this, OnboardingActivity::class.java))
                    finish()
                })
                // ProfileScreen is removed from here as it will be navigated to from HomeScreen
            }
        }
    }
}