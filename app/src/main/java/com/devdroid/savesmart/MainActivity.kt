package com.devdroid.savesmart

//package com.example.expensescreen


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen(onTimeout = {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            })
        }
    }
}
//
//import android.os.Build
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.annotation.RequiresApi
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.devdroid.savesmart.ui.theme.SaveSmartTheme
//import kotlinx.coroutines.delay
//
//class MainActivity : ComponentActivity() {
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            SaveSmartTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
//
//                    NavHost(navController = navController, startDestination = "splash") {
//                        composable("splash") {
//                            SplashScreen(onTimeout = {
//                                navController.navigate("onboarding") {
//                                    // Clear the back stack so users can't go back to splash
//                                    popUpTo("splash") { inclusive = true }
//                                }
//                            })
//                        }
//
//                        composable("onboarding") {
//                            OnboardingScreen(onComplete = {
//                                navController.navigate("home") {
//                                    // Clear the back stack so users can't go back to onboarding
//                                    popUpTo("onboarding") { inclusive = true }
//                                }
//                            })
//                        }
//
//                        composable("home") {
//                            FinanceTrackerScreen(navController = navController)
//                        }
//
//                        composable("income") {
//                            IncomeScreen(
//                                navController = navController,
//                                onAmountAdded = { /* Handle income amount */ }
//                            )
//                        }
//
//                        composable("expense") {
//                            // Your expense screen here
//                            // For now can be a placeholder
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun SplashScreen(onTimeout: () -> Unit) {
//    var startAnimation by remember { mutableStateOf(false) }
//    val alphaAnim = animateFloatAsState(
//        targetValue = if (startAnimation) 1f else 0f,
//        animationSpec = tween(
//            durationMillis = 1000
//        ), label = ""
//    )
//
//    LaunchedEffect(key1 = true) {
//        startAnimation = true
//        delay(2000)
//        onTimeout()
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF6200EE)),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "SaveSmart",
//            color = Color.White,
//            fontSize = 40.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.alpha(alphaAnim.value)
//        )
//    }
//}
//
//@Composable
//fun OnboardingScreen(onComplete: () -> Unit) {
//    // Your onboarding screen implementation
//    // For simplicity, I'll just show a button to continue
//    OnboardingScreen()
////    Box(
////        modifier = Modifier
////            .fillMaxSize()
////            .background(Color.White),
////        contentAlignment = Alignment.Center
////    ) {
////        androidx.compose.material3.Button(
////            onClick = onComplete
////        ) {
////            Text("Get Started")
////        }
////    }
//}