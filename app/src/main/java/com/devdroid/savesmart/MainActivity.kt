//package com.devdroid.savesmart
//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.annotation.RequiresApi
//import com.devdroid.savesmart.ui.theme.SaveSmartTheme
//
//class MainActivity : ComponentActivity() {
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            SaveSmartTheme {
//                SplashScreen(onTimeout = {
//                    startActivity(Intent(this, OnboardingActivity::class.java))
//                    finish()
//                })
//                // ProfileScreen is removed from here as it will be navigated to from HomeScreen
//            }
//        }
//    }
//}
//
////import android.os.Bundle
////import android.util.Log
////import androidx.activity.ComponentActivity
////import com.google.firebase.firestore.FirebaseFirestore
////
////class MainActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////
////        val db = FirebaseFirestore.getInstance()
////
////        // Testing Firestore Connection: Writing Data
////        val testData = hashMapOf(
////            "name" to "Expense Tracker",
////            "status" to "Connected"
////        )
////
////        db.collection("test").add(testData)
////            .addOnSuccessListener { documentReference ->
////                Log.d("FirestoreTest", "Document added with ID: ${documentReference.id}")
////            }
////            .addOnFailureListener { e ->
////                Log.e("FirestoreTest", "Error adding document", e)
////            }
////    }
////}
//
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


//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import com.google.firebase.firestore.FirebaseFirestore
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val db = FirebaseFirestore.getInstance()
//
//        // Testing Firestore Connection: Writing Data
//        val testData = hashMapOf(
//            "name" to "Expense Tracker",
//            "status" to "Connected"
//        )
//
//        db.collection("test").add(testData)
//            .addOnSuccessListener { documentReference ->
//                Log.d("FirestoreTest", "Document added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.e("FirestoreTest", "Error adding document", e)
//            }
//    }
//}