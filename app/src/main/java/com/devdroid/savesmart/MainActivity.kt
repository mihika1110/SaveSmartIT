package com.devdroid.savesmart

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import com.devdroid.savesmart.ui.theme.SaveSmartTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Remember theme state
            var isDarkTheme by remember { mutableStateOf(false) }

            SaveSmartTheme(darkTheme = isDarkTheme) {
                // Navigate to SplashScreen or main nav graph
                SplashScreen(
                    onTimeout = {
                        startActivity(Intent(this, OnboardingActivity::class.java))
                        finish()
                    }
                )

                // For example, if using NavHost, you can pass theme state to SettingsScreen:
                // SettingsScreen(navController, isDarkTheme, onThemeToggle = { isDarkTheme = it })
            }
        }
    }
}