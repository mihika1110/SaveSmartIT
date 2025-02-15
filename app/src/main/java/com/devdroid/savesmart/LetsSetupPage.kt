//////////////package com.devdroid.savesmart
//////////////
//////////////import android.os.Bundle
//////////////import androidx.activity.ComponentActivity
//////////////import androidx.activity.compose.setContent
//////////////import androidx.compose.foundation.background
//////////////import androidx.compose.foundation.layout.*
//////////////import androidx.compose.material3.*
//////////////import androidx.compose.ui.Alignment
//////////////import androidx.compose.ui.Modifier
//////////////import androidx.compose.ui.graphics.Color
//////////////import androidx.compose.ui.text.font.FontWeight
//////////////import androidx.compose.ui.unit.dp
//////////////import androidx.compose.ui.unit.sp
//////////////
//////////////class LetsSetupPage : ComponentActivity() {
//////////////    override fun onCreate(savedInstanceState: Bundle?) {
//////////////        super.onCreate(savedInstanceState)
//////////////        setContent {
//////////////            LetsSetupScreen()
//////////////        }
//////////////    }
//////////////}
//////////////
//////////////@Composable
//////////////fun LetsSetupScreen() {
//////////////    Box(
//////////////        modifier = Modifier
//////////////            .fillMaxSize()
//////////////            .background(Color.White)
//////////////            .padding(16.dp),
//////////////        contentAlignment = Alignment.TopStart
//////////////    ) {
//////////////        Column(
//////////////            modifier = Modifier.fillMaxSize(),
//////////////            verticalArrangement = Arrangement.SpaceBetween,
//////////////            horizontalAlignment = Alignment.Start
//////////////        ) {
//////////////            Column {
//////////////                Text(
//////////////                    text = "Let’s setup your account!",
//////////////                    fontSize = 28.sp,
//////////////                    fontWeight = FontWeight.Bold,
//////////////                    color = Color.Black
//////////////                )
//////////////                Spacer(modifier = Modifier.height(8.dp))
//////////////                Text(
//////////////                    text = "Account can be your bank, credit card or your wallet.",
//////////////                    fontSize = 16.sp,
//////////////                    color = Color.Gray
//////////////                )
//////////////            }
//////////////
//////////////            Button(
//////////////                onClick = { /* TODO: Implement navigation */ },
//////////////                modifier = Modifier
//////////////                    .fillMaxWidth()
//////////////                    .padding(bottom = 16.dp)
//////////////                    .height(56.dp),
//////////////                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF))
//////////////            ) {
//////////////                Text(
//////////////                    text = "Let's go",
//////////////                    fontSize = 16.sp,
//////////////                    fontWeight = FontWeight.Bold,
//////////////                    color = Color.White
//////////////                )
//////////////            }
//////////////        }
//////////////    }
//////////////}
////////////package com.savesmart.ui
////////////
////////////import android.os.Bundle
////////////import androidx.activity.ComponentActivity
////////////import androidx.activity.compose.setContent
////////////import androidx.compose.foundation.layout.*
////////////import androidx.compose.foundation.shape.RoundedCornerShape
////////////import androidx.compose.material3.Button
////////////import androidx.compose.material3.ButtonDefaults
////////////import androidx.compose.material3.Text
////////////import androidx.compose.runtime.Composable
////////////import androidx.compose.ui.Alignment
////////////import androidx.compose.ui.Modifier
////////////import androidx.compose.ui.graphics.Color
////////////import androidx.compose.ui.text.font.FontWeight
////////////import androidx.compose.ui.tooling.preview.Preview
////////////import androidx.compose.ui.unit.dp
////////////import androidx.compose.ui.unit.sp
////////////
////////////class LetsSetupPage : ComponentActivity() {
////////////    override fun onCreate(savedInstanceState: Bundle?) {
////////////        super.onCreate(savedInstanceState)
////////////        setContent {
////////////            LetsSetupScreen()
////////////        }
////////////    }
////////////}
////////////
////////////@Composable
////////////fun LetsSetupScreen() {
////////////    Column(
////////////        modifier = Modifier
////////////            .fillMaxSize()
////////////            .padding(24.dp),
////////////        verticalArrangement = Arrangement.SpaceBetween,
////////////        horizontalAlignment = Alignment.Start
////////////    ) {
////////////        Column {
////////////            Text(
////////////                text = "Let’s setup your account!",
////////////                fontSize = 28.sp,
////////////                fontWeight = FontWeight.Bold,
////////////                color = Color.Black
////////////            )
////////////            Spacer(modifier = Modifier.height(12.dp))
////////////            Text(
////////////                text = "Account can be your bank, credit card or your wallet.",
////////////                fontSize = 16.sp,
////////////                color = Color.DarkGray
////////////            )
////////////        }
////////////
////////////        Button(
////////////            onClick = { /* Handle click action */ },
////////////            modifier = Modifier
////////////                .fillMaxWidth()
////////////                .height(56.dp),
////////////            shape = RoundedCornerShape(12.dp),
////////////            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7A42F4))
////////////        ) {
////////////            Text(text = "Let’s go", fontSize = 18.sp, color = Color.White)
////////////        }
////////////    }
////////////}
////////////
////////////@Preview(showBackground = true)
////////////@Composable
////////////fun PreviewLetsSetupScreen() {
////////////    LetsSetupScreen()
////////////}
//////////
//////////package com.devdroid.savesmart // Replace with your package name
//////////
//////////import android.os.Bundle
//////////import androidx.activity.ComponentActivity
//////////import androidx.activity.compose.setContent
//////////import androidx.compose.foundation.layout.*
//////////import androidx.compose.material.*
//////////import androidx.compose.runtime.Composable
//////////import androidx.compose.ui.Alignment
//////////import androidx.compose.ui.Modifier
//////////import androidx.compose.ui.graphics.Color
//////////import androidx.compose.ui.text.font.FontWeight
//////////import androidx.compose.ui.tooling.preview.Preview
//////////import androidx.compose.ui.unit.dp
//////////import androidx.compose.ui.unit.sp
//////////import androidx.navigation.NavController
//////////import androidx.navigation.compose.rememberNavController
//////////
//////////class SetupAccountActivity : ComponentActivity() {
//////////    override fun onCreate(savedInstanceState: Bundle?) {
//////////        super.onCreate(savedInstanceState)
//////////        setContent {
//////////            SetupAccountScreen(navController = rememberNavController())
//////////        }
//////////    }
//////////}
//////////
//////////@Composable
//////////fun SetupAccountScreen(navController: NavController) {
//////////    Scaffold(
//////////        topBar = {
//////////            TopAppBar(
//////////                title = { /* No title needed */ },
//////////                backgroundColor = Color.Transparent,
//////////                elevation = 0.dp,
//////////                navigationIcon = {
//////////                    IconButton(onClick = { navController.popBackStack() }) {
//////////                        Icon(androidx.compose.material.icons.Icons.Filled.ArrowBack, "Back", tint = Color.Black)
//////////                    }
//////////                }
//////////            )
//////////        }
//////////    ) { paddingValues ->
//////////        Column(
//////////            modifier = Modifier
//////////                .fillMaxSize()
//////////                .padding(paddingValues)
//////////                .padding(20.dp),
//////////            horizontalAlignment = Alignment.Start,
//////////            verticalArrangement = Arrangement.SpaceBetween // Push button to bottom
//////////        ) {
//////////            Column {
//////////                Spacer(modifier = Modifier.height(20.dp))
//////////                Text(
//////////                    text = "Let's setup your account!",
//////////                    fontSize = 24.sp,
//////////                    fontWeight = FontWeight.Bold
//////////                )
//////////                Spacer(modifier = Modifier.height(16.dp))
//////////                Text(
//////////                    text = "Account can be your bank, credit card or your wallet.",
//////////                    fontSize = 16.sp
//////////                )
//////////            }
//////////            // Button at the bottom
//////////            Button(
//////////                onClick = {
//////////                    // Navigate to the next screen
//////////                    navController.navigate("account_selection") // Replace with your route
//////////                },
//////////                modifier = Modifier
//////////                    .fillMaxWidth(),
//////////                colors = ButtonDefaults.buttonColors(backgroundColor = androidx.compose.ui.graphics.Color(0xFF8E2DE2)),
//////////                contentPadding = PaddingValues(vertical = 16.dp)
//////////            ) {
//////////                Text(text = "Let's go", color = Color.White, fontSize = 18.sp)
//////////            }
//////////        }
//////////    }
//////////}
//////////
//////////
//////////@Preview(showBackground = true)
//////////@Composable
//////////fun DefaultPreview() {
//////////    SetupAccountScreen(navController = rememberNavController())
//////////}
////////
////////package com.devdroid.savesmart // Replace with your actual package name
////////
////////import android.os.Bundle
////////import androidx.activity.ComponentActivity
////////import androidx.activity.compose.setContent
////////import androidx.compose.foundation.layout.*
////////import androidx.compose.material.*
////////import androidx.compose.runtime.Composable
////////import androidx.compose.ui.Alignment
////////import androidx.compose.ui.Modifier
////////import androidx.compose.ui.graphics.Color
////////import androidx.compose.ui.unit.dp
////////import androidx.compose.ui.unit.sp
////////import androidx.navigation.NavController
////////
////////import android.content.Intent
////////import android.os.Bundle
////////import androidx.activity.ComponentActivity
////////import androidx.activity.compose.setContent
////////import androidx.compose.foundation.background
////////import androidx.compose.foundation.layout.*
////////import androidx.compose.foundation.shape.RoundedCornerShape
////////import androidx.compose.material.icons.Icons
////////import androidx.compose.material.icons.automirrored.filled.ArrowBack
////////import androidx.compose.material3.*
////////import androidx.compose.runtime.*
////////import androidx.compose.ui.Alignment
////////import androidx.compose.ui.Modifier
////////import androidx.compose.ui.draw.clip
////////import androidx.compose.ui.graphics.Brush
////////import androidx.compose.ui.graphics.Color
////////import androidx.compose.foundation.text.KeyboardOptions
////////import androidx.compose.ui.platform.LocalContext
////////import androidx.compose.ui.text.font.FontWeight
////////import androidx.compose.ui.text.input.KeyboardType
////////import androidx.compose.ui.text.input.PasswordVisualTransformation
////////import androidx.compose.ui.unit.dp
////////import androidx.compose.ui.unit.sp
////////
////////class SetupAccountActivity : ComponentActivity() {
////////    override fun onCreate(savedInstanceState: Bundle?) {
////////        super.onCreate(savedInstanceState)
////////        setContent {
////////            SetupAccountButton(onButtonClick = { /* Your navigation logic here */ })
////////        }
////////    }
////////}
////////
////////@Composable
////////fun SetupAccountButton(onButtonClick: () -> Unit) {
////////    Column(
////////        modifier = Modifier
////////            .fillMaxSize()
////////            .padding(20.dp),
////////        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
////////        verticalArrangement = Arrangement.Bottom // Align to the bottom
////////    ) {
////////        Button(
//////////            onClick = {
//////////                val intent = Intent(context, LetsSetupPage::class.java)
//////////                context.startActivity(intent)
//////////            },
////////            modifier = Modifier
////////                .fillMaxWidth()
////////                .height(56.dp),
////////            colors = ButtonDefaults.buttonColors(
////////                containerColor = Color(0xFF6C63FF)
////////            ),
////////            shape = RoundedCornerShape(8.dp)
////////        ) {
////////            Text(
////////                "Lets Proceed",
////////                fontSize = 16.sp,
////////                fontWeight = FontWeight.Bold
////////            )
////////        }
////////        Spacer(Modifier.height(40.dp)) // Add some bottom spacing if needed
////////    }
////////}
//////package com.devdroid.savesmart // Replace with your actual package name
//////
//////import android.os.Bundle
//////import androidx.activity.ComponentActivity
//////import androidx.activity.compose.setContent
//////import androidx.compose.foundation.layout.*
//////import androidx.compose.material3.*
//////import androidx.compose.runtime.Composable
//////import androidx.compose.ui.Alignment
//////import androidx.compose.ui.Modifier
//////import androidx.compose.ui.graphics.Color
//////import androidx.compose.ui.text.font.FontWeight
//////import androidx.compose.ui.unit.dp
//////import androidx.compose.ui.unit.sp
//////import androidx.navigation.NavController
//////import androidx.navigation.compose.rememberNavController
//////
//////class SetupAccountActivity : ComponentActivity() {
//////    override fun onCreate(savedInstanceState: Bundle?) {
//////        super.onCreate(savedInstanceState)
//////        setContent {
//////            SetupAccountScreen(navController = rememberNavController())
//////        }
//////    }
//////}
//////
//////@Composable
//////fun SetupAccountScreen(navController: NavController) {
//////    Scaffold(
//////        topBar = {
//////            TopAppBar(
//////                title = { /* No title needed */ },
//////                backgroundColor = Color.Transparent,
//////                elevation = 0.dp,
//////                navigationIcon = {
//////                    IconButton(onClick = { navController.popBackStack() }) {
//////                        Icon(
//////                            imageVector = androidx.compose.material.icons.Icons.Filled.ArrowBack,
//////                            contentDescription = "Back",
//////                            tint = Color.Black
//////                        )
//////                    }
//////                }
//////            )
//////        }
//////    ) { paddingValues ->
//////        Column(
//////            modifier = Modifier
//////                .fillMaxSize()
//////                .padding(paddingValues)
//////                .padding(20.dp),
//////            horizontalAlignment = Alignment.Start,
//////            verticalArrangement = Arrangement.SpaceBetween // Push button to bottom
//////        ) {
//////            Column {
//////                Spacer(modifier = Modifier.height(20.dp))
//////                Text(
//////                    text = "Let's setup your account!",
//////                    fontSize = 24.sp,
//////                    fontWeight = FontWeight.Bold
//////                )
//////                Spacer(modifier = Modifier.height(16.dp))
//////                Text(
//////                    text = "Account can be your bank, credit card or your wallet.",
//////                    fontSize = 16.sp
//////                )
//////            }
//////            // Button at the bottom
//////            Button(
//////                onClick = {
//////                    // Navigate to the next screen
//////                    navController.navigate("account_selection") // Replace with your route
//////                },
//////                modifier = Modifier
//////                    .fillMaxWidth(),
//////                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8E2DE2)),
//////                contentPadding = PaddingValues(vertical = 16.dp)
//////            ) {
//////                Text(text = "Let's go", color = Color.White, fontSize = 18.sp)
//////            }
//////        }
//////    }
//////}
//////
//////@Composable
//////fun SetupAccountButton(navController: NavController) {
//////    Column(
//////        modifier = Modifier
//////            .fillMaxSize()
//////            .padding(20.dp),
//////        horizontalAlignment = Alignment.CenterHorizontally, // Center horizontally
//////        verticalArrangement = Arrangement.Bottom // Align to the bottom
//////    ) {
//////        Button(
//////            onClick = {
//////                // Navigate to next screen or perform actions
//////                navController.navigate("next_screen") // Replace with the correct navigation route
//////            },
//////            modifier = Modifier
//////                .fillMaxWidth()
//////                .height(56.dp),
//////            colors = ButtonDefaults.buttonColors(
//////                containerColor = Color(0xFF6C63FF)
//////            ),
//////            shape = RoundedCornerShape(8.dp)
//////        ) {
//////            Text(
//////                "Let's Proceed",
//////                fontSize = 16.sp,
//////                fontWeight = FontWeight.Bold
//////            )
//////        }
//////        Spacer(Modifier.height(40.dp)) // Add some bottom spacing if needed
//////    }
//////}
////package com.example.setupaccount
////
////import android.os.Bundle
////import androidx.activity.ComponentActivity
////import androidx.activity.compose.setContent
////import androidx.compose.foundation.layout.*
////import androidx.compose.foundation.shape.RoundedCornerShape
////import androidx.compose.material3.*
////import androidx.compose.runtime.Composable
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.text.font.FontWeight
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.unit.sp
////
////class SetupAccountActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContent {
////            SetupAccountScreen()
////        }
////    }
////}
////
////@Composable
////fun SetupAccountScreen() {
////    Scaffold(
////        modifier = Modifier.fillMaxSize(),
////        contentColor = Color.Black
////    ) { paddingValues ->
////        Column(
////            modifier = Modifier
////                .fillMaxSize()
////                .padding(paddingValues)
////                .padding(20.dp),
////            horizontalAlignment = Alignment.CenterHorizontally,
////            verticalArrangement = Arrangement.Center
////        ) {
////            Text(
////                text = "Let's setup your account!",
////                fontSize = 24.sp,
////                fontWeight = FontWeight.Bold
////            )
////            Spacer(modifier = Modifier.height(16.dp))
////            Text(
////                text = "Account can be your bank, credit card or your wallet.",
////                fontSize = 16.sp
////            )
////            Spacer(modifier = Modifier.height(24.dp))
////            Button(
////                onClick = {
////                    // Navigate to the next screen
////                },
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(56.dp),
////                colors = ButtonDefaults.buttonColors(
////                    containerColor = Color(0xFF6C63FF)
////                ),
////                shape = RoundedCornerShape(8.dp)
////            ) {
////                Text(text = "Let's go", color = Color.White, fontSize = 18.sp)
////            }
////        }
////    }
////}
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LetsSetupPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Apply your app's theme if you have one, or use MaterialTheme
            MaterialTheme {
                LetsSetupAccountScreen()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Navigate back to SignUpActivity
        val intent = Intent(this, SignUpActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}

@Composable
fun LetsSetupAccountScreen() {
    val context = LocalContext.current

    Scaffold(
        containerColor = Color.White,
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Let's setup your account!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 32.dp),
                        textAlign = TextAlign.Center
                    )

                    Button(
                        onClick = {
                            // Navigate to the main homepage
                            val intent = Intent(context, CurrencyActivity::class.java)
                            context.startActivity(intent)
                            (context as? ComponentActivity)?.finish()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6C63FF),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "Proceed",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    )
}