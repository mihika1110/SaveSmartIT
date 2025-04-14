//package com.devdroid.savesmart
//
//import android.app.Activity
//import android.content.Intent
//import androidx.activity.ComponentActivity
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.ui.tooling.preview.Preview
//import com.google.firebase.auth.FirebaseAuth
//
//
//
//@Composable
//fun ProfileScreen(
//    navController: NavController,
//    username: String = "Juhi Sahni",
//    onAccountClick: () -> Unit = {},
//    onSettingsClick: () -> Unit = { navController.navigate("settings") },
//    onExportDataClick: () -> Unit = {},
//    onLogoutClick: () -> Unit = {}
//) {
//    val context = LocalContext.current // Get context inside Composable
//
//    val onLogoutClick = remember {
//        {
//            // Sign out from Firebase
//            FirebaseAuth.getInstance().signOut()
//
//            // Navigate to LoginActivity and clear back stack
//            val intent = Intent(context, LoginActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//            context.startActivity(intent)
//
//            // Finish the current activity (only if context is an Activity)
//            if (context is Activity) {
//                context.finish()
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(30.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 3.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Profile",
//                style = MaterialTheme.typography.headlineSmall,
//                fontWeight = FontWeight.Medium
//            )
//            Text(text = "9:41")
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 24.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Surface(
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(CircleShape),
//                    color = MaterialTheme.colorScheme.surfaceVariant
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = null,
//                        modifier = Modifier.padding(16.dp),
//                        tint = MaterialTheme.colorScheme.onSurfaceVariant
//                    )
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = username,
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.Medium
//                )
//            }
//        }
//
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            colors = CardDefaults.cardColors(
//                containerColor = MaterialTheme.colorScheme.surface
//            ),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Column(modifier = Modifier.padding(vertical = 8.dp)) {
//                ProfileMenuItem(
//                    icon = Icons.Default.AccountCircle,
//                    title = "Account",
//                    onClick = onAccountClick,
//                    iconTint = MaterialTheme.colorScheme.primary
//                )
//                ProfileMenuItem(
//                    icon = Icons.Default.Settings,
//                    title = "Settings",
//                    onClick = onSettingsClick,
//                    iconTint = MaterialTheme.colorScheme.primary
//                )
////                ProfileMenuItem(
////                    icon = Icons.Default.Share,
////                    title = "Export Data",
////                    onClick = onExportDataClick,
////                    iconTint = MaterialTheme.colorScheme.primary
////                )
//                ProfileMenuItem(
//                    icon = Icons.Default.ExitToApp,
//                    title = "Logout",
//                    onClick = onLogoutClick,
//                    iconTint = MaterialTheme.colorScheme.error
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//    }
//}
//
//
//@Composable
//private fun ProfileMenuItem(
//    icon: ImageVector,
//    title: String,
//    onClick: () -> Unit,
//    iconTint: Color
//) {
//    Surface(
//        onClick = onClick,
//        modifier = Modifier.fillMaxWidth(),
//        color = Color.Transparent
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(horizontal = 16.dp, vertical = 12.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Surface(
//                color = iconTint.copy(alpha = 0.1f),
//                shape = CircleShape,
//                modifier = Modifier.size(40.dp)
//            ) {
//                Icon(
//                    imageVector = icon,
//                    contentDescription = null,
//                    tint = iconTint,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .size(24.dp)
//                )
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Text(
//                text = title,
//                style = MaterialTheme.typography.bodyLarge
//            )
//        }
//    }
//}
package com.devdroid.savesmart

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    username: String = "Juhi Sahni",
    onAccountClick: () -> Unit = {},
    onSettingsClick: () -> Unit = { navController.navigate("settings") },
    onExportDataClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val context = LocalContext.current // Get context inside Composable

    val onLogoutClick = remember {
        {
            // Sign out from Firebase
            FirebaseAuth.getInstance().signOut()

            // Navigate to LoginActivity and clear back stack
            val intent = Intent(context, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)

            // Finish the current activity (only if context is an Activity)
            if (context is Activity) {
                context.finish()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium
            )
            Text(text = "9:41")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                ProfileMenuItem(
                    icon = Icons.Default.AccountCircle,
                    title = "Account",
                    onClick = onAccountClick,
                    iconTint = MaterialTheme.colorScheme.primary
                )
                ProfileMenuItem(
                    icon = Icons.Default.Settings,
                    title = "Settings",
                    onClick = onSettingsClick,
                    iconTint = MaterialTheme.colorScheme.primary
                )
//                ProfileMenuItem(
//                    icon = Icons.Default.Share,
//                    title = "Export Data",
//                    onClick = onExportDataClick,
//                    iconTint = MaterialTheme.colorScheme.primary
//                )
                ProfileMenuItem(
                    icon = Icons.Default.ExitToApp,
                    title = "Logout",
                    onClick = onLogoutClick,
                    iconTint = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

//
@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    iconTint: Color
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = iconTint.copy(alpha = 0.1f),
                shape = CircleShape,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}