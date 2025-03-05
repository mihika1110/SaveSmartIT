
package com.devdroid.savesmart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.BasicText
//import androidx.compose.material3.*
import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Help & Support", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD1C4E9))
            )
        },
        containerColor = Color(0xFFD1C4E9) // Ensures the entire screen is Cool Purple
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD1C4E9)) // Full-screen Cool Purple
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            val teamMembers = listOf(
                "Parul Shrivastava" to "parul_2301cs74@iitp.ac.in",
                "Mihika" to "mihika_2301cs31@iitp.ac.in",
                "Ritu Kumari Singh" to "ritu_2301cs75@iitp.ac.in",
                "Juhi Sahni" to "juhi_2301cs88@iitp.ac.in",
                "Pragya Mahajan" to "pragya_2302cs05@iitp.ac.in"
            )

            teamMembers.forEach { (name, email) ->
                HelpItem(name, email)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun HelpItem(name: String, email: String) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable {
                clipboardManager.setText(AnnotatedString(email))
                showToast(context, "Email copied: $email")
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3A3A6E)), // Dark Blue
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFBBDEFB) // Light blue for contrast
            )
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun PreviewHelpScreen() {
    HelpScreen(navController = rememberNavController())
}
