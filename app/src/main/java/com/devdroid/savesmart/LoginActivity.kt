////package com.devdroid.savesmart
////
////import android.content.Intent
////import android.os.Bundle
////import androidx.activity.ComponentActivity
////import androidx.activity.compose.setContent
////import androidx.compose.foundation.background
////import androidx.compose.foundation.layout.*
////import androidx.compose.foundation.shape.RoundedCornerShape
////import androidx.compose.material.icons.Icons
////import androidx.compose.material.icons.automirrored.filled.ArrowBack
////import androidx.compose.material3.*
////import androidx.compose.runtime.*
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.draw.clip
////import androidx.compose.ui.graphics.Brush
////import androidx.compose.ui.graphics.Color
////import androidx.compose.foundation.text.KeyboardOptions
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.text.font.FontWeight
////import androidx.compose.ui.text.input.KeyboardType
////import androidx.compose.ui.text.input.PasswordVisualTransformation
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.unit.sp
////
////class LoginActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContent {
////            LoginScreen()
////        }
////    }
////
////    @Deprecated("Deprecated in Java")
////    override fun onBackPressed() {
////        // Navigate back to OnboardingActivity
////        val intent = Intent(this, OnboardingActivity::class.java)
////        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
////        startActivity(intent)
////        finish()
////    }
////}
////
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun LoginScreen() {
////    val context = LocalContext.current
////    var email by remember { mutableStateOf("") }
////    var password by remember { mutableStateOf("") }
////
////    // Gradient background
////    val gradientBackground = Brush.verticalGradient(
////        colors = listOf(
////            Color(0xFF6C63FF).copy(alpha = 0.1f),
////            Color(0xFF6C63FF).copy(alpha = 0.05f)
////        )
////    )
////
////    Box(
////        modifier = Modifier
////            .fillMaxSize()
////            .background(gradientBackground)
////    ) {
////        Column(
////            modifier = Modifier
////                .fillMaxSize()
////                .padding(16.dp),
////            verticalArrangement = Arrangement.Center,
////            horizontalAlignment = Alignment.CenterHorizontally
////        ) {
////            // Top Bar with Back Navigation
////            TopAppBar(
////                title = {
////                    Text(
////                        "Login",
////                        style = MaterialTheme.typography.titleLarge.copy(
////                            fontWeight = FontWeight.Bold,
////                            color = Color(0xFF6C63FF)
////                        )
////                    )
////                },
////                navigationIcon = {
////                    IconButton(
////                        onClick = {
////                            // Navigate back to Onboarding
////                            val intent = Intent(context, OnboardingActivity::class.java)
////                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
////                            context.startActivity(intent)
////                            (context as? ComponentActivity)?.finish()
////                        }
////                    ) {
////                        Icon(
////                            Icons.AutoMirrored.Default.ArrowBack,
////                            contentDescription = "Back",
////                            tint = Color(0xFF6C63FF)
////                        )
////                    }
////                },
////                colors = TopAppBarDefaults.topAppBarColors(
////                    containerColor = Color.Transparent
////                )
////            )
////
////            // Card for Input Fields
////            Card(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .clip(RoundedCornerShape(16.dp)),
////                elevation = CardDefaults.cardElevation(
////                    defaultElevation = 4.dp
////                ),
////                colors = CardDefaults.cardColors(
////                    containerColor = Color.White
////                )
////            ) {
////                Column(
////                    modifier = Modifier
////                        .padding(16.dp)
////                        .fillMaxWidth()
////                ) {
////                    // Email Field
////                    OutlinedTextField(
////                        value = email,
////                        onValueChange = { email = it },
////                        label = { Text("Email Address") },
////                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
////                        modifier = Modifier.fillMaxWidth(),
////                        singleLine = true,
////                        colors = OutlinedTextFieldDefaults.colors(
////                            unfocusedBorderColor = Color.LightGray,
////                            focusedBorderColor = Color(0xFF6C63FF)
////                        )
////                    )
////
////                    Spacer(modifier = Modifier.height(16.dp))
////
////                    // Password Field
////                    OutlinedTextField(
////                        value = password,
////                        onValueChange = { password = it },
////                        label = { Text("Password") },
////                        visualTransformation = PasswordVisualTransformation(),
////                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
////                        modifier = Modifier.fillMaxWidth(),
////                        singleLine = true,
////                        colors = OutlinedTextFieldDefaults.colors(
////                            unfocusedBorderColor = Color.LightGray,
////                            focusedBorderColor = Color(0xFF6C63FF)
////                        )
////                    )
////                }
////            }
////
////            // Login Button
////            Button(
////                onClick = { /* TODO: Handle login */ },
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(56.dp),
////                colors = ButtonDefaults.buttonColors(
////                    containerColor = Color(0xFF6C63FF)
////                ),
////                shape = RoundedCornerShape(8.dp)
////            ) {
////                Text(
////                    "Login",
////                    fontSize = 16.sp,
////                    fontWeight = FontWeight.Bold
////                )
////            }
////
////            Spacer(modifier = Modifier.height(16.dp))
////
////            // Sign Up Link
////            Row(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(bottom = 16.dp),
////                horizontalArrangement = Arrangement.Center,
////                verticalAlignment = Alignment.CenterVertically
////            ) {
////                Text("Don't have an account? ", color = Color.Gray)
////                TextButton(
////                    onClick = {
////                        // Navigate to SignUpActivity
////                        val intent = Intent(context, SignUpActivity::class.java)
////                        context.startActivity(intent)
////                    },
////                    modifier = Modifier.padding(start = 4.dp)
////                ) {
////                    Text(
////                        "Sign Up",
////                        color = Color(0xFF6C63FF),
////                        fontWeight = FontWeight.Bold
////                    )
////                }
////            }
////        }
////    }
////}
//
//package com.devdroid.savesmart
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//class LoginActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            LoginScreen()
//        }
//    }
//
//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        val intent = Intent(this, OnboardingActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//        startActivity(intent)
//        finish()
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LoginScreen() {
//    val context = LocalContext.current
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    val gradientBackground = Brush.verticalGradient(
//        colors = listOf(
//            Color(0xFF6C63FF).copy(alpha = 0.1f),
//            Color(0xFF6C63FF).copy(alpha = 0.05f)
//        )
//    )
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(gradientBackground)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            TopAppBar(
//                title = {
//                    Text(
//                        "Login",
//                        style = MaterialTheme.typography.titleLarge.copy(
//                            fontWeight = FontWeight.Bold,
//                            color = Color(0xFF6C63FF)
//                        )
//                    )
//                },
//                navigationIcon = {
//                    IconButton(
//                        onClick = {
//                            val intent = Intent(context, OnboardingActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//                            context.startActivity(intent)
//                            (context as? ComponentActivity)?.finish()
//                        }
//                    ) {
//                        Icon(
//                            Icons.AutoMirrored.Default.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color(0xFF6C63FF)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.Transparent
//                )
//            )
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(16.dp)),
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 4.dp
//                ),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.White
//                )
//            ) {
//                Column(
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxWidth()
//                ) {
//                    OutlinedTextField(
//                        value = email,
//                        onValueChange = { email = it },
//                        label = { Text("Email Address") },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                        modifier = Modifier.fillMaxWidth(),
//                        singleLine = true,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedBorderColor = Color.LightGray,
//                            focusedBorderColor = Color(0xFF6C63FF)
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(16.dp))
//
//                    OutlinedTextField(
//                        value = password,
//                        onValueChange = { password = it },
//                        label = { Text("Password") },
//                        visualTransformation = PasswordVisualTransformation(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                        modifier = Modifier.fillMaxWidth(),
//                        singleLine = true,
//                        colors = OutlinedTextFieldDefaults.colors(
//                            unfocusedBorderColor = Color.LightGray,
//                            focusedBorderColor = Color(0xFF6C63FF)
//                        )
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Button(
//                onClick = { /* TODO: Handle login */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF6C63FF)
//                ),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(
//                    "Login",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text("Don't have an account? ", color = Color.Gray)
//                TextButton(
//                    onClick = {
//                        val intent = Intent(context, SignUpActivity::class.java)
//                        context.startActivity(intent)
//                    },
//                    modifier = Modifier.padding(start = 4.dp)
//                ) {
//                    Text(
//                        "Sign Up",
//                        color = Color(0xFF6C63FF),
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//            }
//        }
//    }
//}
package com.devdroid.savesmart

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        setContent {

            LoginScreen()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, OnboardingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF6C63FF).copy(alpha = 0.1f),
            Color(0xFF6C63FF).copy(alpha = 0.05f)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = {
                    Text(
                        "Login",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6C63FF)
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            val intent = Intent(context, OnboardingActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                            context.startActivity(intent)
                            (context as? ComponentActivity)?.finish()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF6C63FF)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = Color(0xFF6C63FF)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = Color(0xFF6C63FF)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
//                    navigate to main homepage
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C63FF)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Don't have an account? ", color = Color.Gray)
                TextButton(
                    onClick = {
                        val intent = Intent(context, SignUpActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Text(
                        "Sign Up",
                        color = Color(0xFF6C63FF),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
