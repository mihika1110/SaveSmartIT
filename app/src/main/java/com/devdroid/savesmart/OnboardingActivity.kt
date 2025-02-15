package com.devdroid.savesmart

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class OnboardingPage(
    val image: Int,
    val title: String,
    val description: String
)

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen() {
    val context = LocalContext.current
    val pages = listOf(
        OnboardingPage(
            image = R.drawable.onboarding_1,
            title = "Gain total control\nof your money",
            description = "Become your own money manager\nand make every cent count"
        ),
        OnboardingPage(
            image = R.drawable.onboarding_2,
            title = "Know where your\nmoney goes",
            description = "Track your transaction easily,\nwith categories and financial report"
        ),
        OnboardingPage(
            image = R.drawable.onboarding_3,
            title = "Planning ahead",
            description = "Setup your budget for each category\nso you in control"
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { position ->
            OnboardingPageContent(pages[position])
        }

        // Page indicator
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pages.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    Color(0xFF6200EE)
                } else {
                    Color(0xFFE0E0E0)
                }
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color, shape = CircleShape)
                        .size(8.dp)
                )
            }
        }

        // Buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Button(
                onClick = {
                    // Navigate to SignUp Activity
                    val intent = Intent(context, SignUpActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE)
                )
            ) {
                Text("Sign Up", fontSize = 16.sp)
            }

            TextButton(
                onClick = {
                    // Navigate to Login Activity
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Login",
                    fontSize = 16.sp,
                    color = Color(0xFF6200EE)
                )
            }
        }
    }
}

// Rest of the code remains the same as in the original file
@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier
                .size(280.dp)
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = page.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = page.description,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}