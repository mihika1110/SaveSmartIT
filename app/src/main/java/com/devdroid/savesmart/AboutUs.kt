package com.devdroid.savesmart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFD6C7FF), // Lavender background for the whole page
        topBar = {
            TopAppBar(
                title = { Text("About Us") },
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Meet Our Team",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                color = Color.White
            )

            val teamMembers = listOf(
                TeamMember("Parul Shrivastava", R.drawable.parul1),
                TeamMember("Ritu Kumari Singh", R.drawable.ritu1),
                TeamMember("Mihika", R.drawable.mihika1),
                TeamMember("Juhi Sahni", R.drawable.juhi1),
                TeamMember("Pragya Mahajan", R.drawable.pragya1)
            )

            teamMembers.forEach { member ->
                TeamMemberCard(member)
            }
        }
    }
}


@Composable
fun TeamMemberCard(member: TeamMember) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // Light Blue Background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .height(120.dp)
        ) {
            Image(
                painter = painterResource(id = member.imageRes),
                contentDescription = member.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(3.dp, Color.Black, RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = member.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

data class TeamMember(val name: String, val imageRes: Int)

@Preview(showBackground = true)
@Composable
fun PreviewAboutScreen() {
    val navController = rememberNavController()
    AboutScreen(navController)
}
