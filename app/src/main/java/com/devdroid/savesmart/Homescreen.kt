package com.devdroid.savesmart

//package com.example.home

import android.os.Bundle
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devdroid.savesmart.ui.theme.SaveSmartTheme

//import com.example.home.ui.theme.HOMETheme

class Homescreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaveSmartTheme {
                FinanceTrackerScreen()
            }
        }
    }
}

@Composable
fun FinanceTrackerScreen() {
    Scaffold(
        bottomBar = { BottomNavigation() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .background(Color(0xFFF0FFF0))
                .background(Color.Black)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TopBar()
            AccountBalance(balance = 0)
            IncomeExpenseRow(income = 0, expenses = 0)
            SpendFrequencySection()
            RecentTransactionsSection()
        }
    }
}

@Composable
fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start // Align content to the start
    ) {
        // "Hello Parul" aligned to the left
        Text(
            text = "Hello Parul,",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Center-aligned dropdown below "Hello Parul"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            MonthDropdown()
        }
    }
}

@Composable
fun MonthDropdown() {
    var selectedMonth by remember { mutableStateOf("January") } // Initial selected month
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    var expanded by remember { mutableStateOf(false) } // Dropdown state

    Box(
        modifier = Modifier
            .width(200.dp)
//            .background(Color(0xFFFFFFFF
//            ), shape = RoundedCornerShape(8.dp)) // Light background
//            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Optional border
    ) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Display the selected month
                Text(
                    text = selectedMonth,
                    fontSize = 18.sp, // Larger font size
                    color = Color(0xFF5555FF), // Light blue color
                    fontWeight = FontWeight.Bold
                )
                // Down arrow icon
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    tint = Color.Gray
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            months.forEach { month ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = month,
                            fontSize = 16.sp,
                            color = if (month == selectedMonth) Color.Blue else Color.Black // Highlight selected
                        )
                    },
                    onClick = {
                        selectedMonth = month // Update selected month
                        expanded = false // Close dropdown
                    }
                )
            }
        }
    }
}


@Composable
fun AccountBalance(balance: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Account Balance",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = "$$balance",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun IncomeExpenseRow(income: Int, expenses: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Income",
                    color = Color.White
                )
                Text(
                    text = "$$income",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

        Card(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFF5252)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Expenses",
                    color = Color.White
                )
                Text(
                    text = "$$expenses",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun SpendFrequencySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "Spend Frequency",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterChip(
                selected = true,
                onClick = { /* TODO */ },
                label = { Text("Today") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = false,
                onClick = { /* TODO */ },
                label = { Text("Week") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = false,
                onClick = { /* TODO */ },
                label = { Text("Month") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = false,
                onClick = { /* TODO */ },
                label = { Text("Year") }
            )
        }
    }
}

@Composable
fun RecentTransactionsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Transaction",
                style = MaterialTheme.typography.titleMedium
            )
            TextButton(onClick = { /* TODO */ }) {
                Text("See All")
            }
        }

        TransactionItem(
            icon = Icons.Default.ShoppingCart,
            title = "Shopping",
            subtitle = "Buy some grocery",
            amount = -10,
            time = "10:00 AM"
        )

        TransactionItem(
            icon = Icons.Default.Menu,
            title = "Subscription",
            subtitle = "Disney+ Annual",
            amount = +10,
            time = "03:30 PM"
        )

    }
}

@Composable
fun TransactionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    amount: Int,
    time: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$$amount",
                style = MaterialTheme.typography.bodyLarge,
                color = if (amount < 0) Color.Red else Color.Green
            )
            Text(
                text = time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun BottomNavigation() {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFFFFFFFF)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Transaction") },
            label = { Text("Transaction") },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
            label = { Text("Add") },
            selected = false,
            onClick = { /* TODO */ }
        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.PieChart, contentDescription = "Budget") },
//            label = { Text("Budget") },
//            selected = false,
//            onClick = { /* TODO */ }
//        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { /* TODO */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceTrackerPreview() {
    SaveSmartTheme {
        FinanceTrackerScreen()
    }
}