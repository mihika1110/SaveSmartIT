package com.devdroid.savesmart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            listOf(
                "Currency" to "USD",
                "Language" to "English",
                "Theme" to "Dark",
                "Security" to "Pin",
                "About" to null,
                "Help" to null
            ).forEach { (title, value) ->
                SettingsItem(title, value) {
                    when (title) {
                        "About" -> navController.navigate("about") // Navigates to AboutScreen
                        "Help" -> navController.navigate("help")   // Navigates to HelpScreen
                    }
                }
                Divider()
            }
        }
    }
}

@Composable
fun SettingsItem(title: String, value: String?, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
        Row(verticalAlignment = Alignment.CenterVertically) {
            value?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium, color = Color.Gray) }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Navigate to $title settings", tint = Color.Gray)
        }
    }
}
