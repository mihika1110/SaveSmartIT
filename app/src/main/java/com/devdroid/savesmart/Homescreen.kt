package com.devdroid.savesmart

//package com.example.home

import BudgetScreen
import android.os.Build
import android.os.Bundle
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devdroid.savesmart.IncomeScreen
import com.devdroid.savesmart.ExpenseScreen
//import com.example.expensescreen.ExpenseScreen
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.foundation.Canvas
import androidx.navigation.compose.currentBackStackEntryAsState


//import com.example.home.ui.theme.HOMETheme

class Homescreen : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun FinanceTrackerScreen() {
//    val navController = rememberNavController()
//    var totalIncome by remember { mutableStateOf(0) }
//    var totalExpenses by remember { mutableStateOf(0) }
//    Scaffold(
//        bottomBar = { BottomNavigation() }
//    ) { paddingValues ->
//        NavHost(
//            navController = navController,
//            startDestination = "home"
//        ) {
//            composable("home") {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Black)
//                        .padding(paddingValues)
//                        .padding(16.dp)
//                ) {
//                    TopBar()
//                    AccountBalance(balance = totalIncome - totalExpenses)
//                    IncomeExpenseRow(income = totalIncome, expenses = totalExpenses, navController)
//                    SpendFrequencySection()
//                    RecentTransactionsSection()
//                }
//            }
//
//            composable("income") {
//                // Pass the income updater function
//                IncomeScreen(
//                    navController = navController,
//                    onAmountAdded = { amount ->
//                        totalIncome += amount
//                    }
//                )
//            }
//            composable("expense") {
//                ExpenseScreen(
//                    navController = navController,
//                    onAmountAdded = { amount ->
//                        totalExpenses += amount
//                    }
//                )
//            }
//        }
//    }
//}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FinanceTrackerScreen() {
    val navController = rememberNavController()
    var totalIncome by remember { mutableStateOf(0) }
    var totalExpenses by remember { mutableStateOf(0) }
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route ?: "home" // Default to home if null

    Scaffold(
        bottomBar = { BottomNavigation(navController = navController, currentRoute = currentRoute) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF8F8F0)) // Light cream background
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    TopBar()
                    AccountBalance(balance = totalIncome - totalExpenses)
                    IncomeExpenseRow(income = totalIncome, expenses = totalExpenses, navController)
                    SpendFrequencySection()
                    RecentTransactionsSection()
                }
            }

            composable("income") {
                IncomeScreen(
                    navController = navController,
                    onAmountAdded = { amount -> totalIncome += amount }
                )
            }

            composable("expense") {
                ExpenseScreen(
                    navController = navController,
                    onAmountAdded = { amount -> totalExpenses += amount }
                )
            }

            composable("profile") {
                ProfileScreen(
                    navController = navController,  // Use the same navController
                    onAccountClick = {},
                    onSettingsClick = { navController.navigate("settings") }, // Fix navigation here
                    onExportDataClick = {},
                    onLogoutClick = {}
                )
            }

            composable("settings") { SettingsScreen(navController) } // Settings screen added correctly

            composable("budget") { // Budget screen navigation
                BudgetScreen(navController)
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController, currentRoute: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val selectedColor = Color(0xFF6949FF)
        val defaultColor = Color.Gray

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = if (currentRoute == "home") selectedColor else defaultColor) },
            label = { Text("Home", color = if (currentRoute == "home") selectedColor else defaultColor) },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") },
            modifier = Modifier.weight(1f)
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Transactions", tint = if (currentRoute == "transactions") selectedColor else defaultColor) },
            label = { Text("Transactions", color = if (currentRoute == "transactions") selectedColor else defaultColor) },
            selected = currentRoute == "transactions",
            onClick = { navController.navigate("transactions") },
            modifier = Modifier.weight(1f)
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Budget", tint = if (currentRoute == "budget") selectedColor else defaultColor) },
            label = { Text("Budget", color = if (currentRoute == "budget") selectedColor else defaultColor) },
            selected = currentRoute == "budget",
            onClick = { navController.navigate("budget") },
            modifier = Modifier.weight(1f)
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = if (currentRoute == "profile") selectedColor else defaultColor) },
            label = { Text("Profile", color = if (currentRoute == "profile") selectedColor else defaultColor) },
            selected = currentRoute == "profile",
            onClick = { navController.navigate("profile") },
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // User image on left - using Box instead of Image to avoid the unresolved reference
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(20.dp)) // Using RoundedCornerShape instead of CircleShape
            ) {
                // Avatar placeholder
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                )
            }

            // Notification bell on right
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color(0xFF6949FF), // Purple notification bell
                modifier = Modifier.size(28.dp)
            )
        }

        // Month selector dropdown
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            MonthDropdown()
        }
    }
}

@Composable
fun MonthDropdown() {
    var selectedMonth by remember { mutableStateOf("October") } // Initial selected month
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.width(200.dp)
    ) {
        TextButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFF6949FF))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = selectedMonth,
                    fontSize = 18.sp,
                    color = Color(0xFF6949FF), // Purple text for month
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    tint = Color(0xFF6949FF) // Purple dropdown icon
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
                            color = if (month == selectedMonth) Color(0xFF6949FF) else Color.Black
                        )
                    },
                    onClick = {
                        selectedMonth = month
                        expanded = false
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
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun IncomeExpenseRow(income: Int, expenses: Int, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .clickable { navController.navigate("income") },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF4CAF50) // Green card for income
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp)), // Using RoundedCornerShape instead of CircleShape
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Income",
                        tint = Color.White
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
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
        }

        Card(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .clickable { navController.navigate("expense") },
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFF5252) // Red card for expenses
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(20.dp)), // Using RoundedCornerShape instead of CircleShape
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = "Expenses",
                        tint = Color.White
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
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
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.Black
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            // Purple line chart for spend frequency
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path()
                val width = size.width
                val height = size.height

                // Create a wave pattern similar to screenshot
                path.moveTo(0f, height * 0.7f)
                path.cubicTo(
                    width * 0.2f, height * 0.9f,
                    width * 0.4f, height * 0.3f,
                    width * 0.6f, height * 0.5f
                )
                path.cubicTo(
                    width * 0.8f, height * 0.7f,
                    width * 0.9f, height * 0.1f,
                    width, height * 0.4f
                )

                // Draw the path with purple color and stroke
                drawPath(
                    path = path,
                    color = Color(0xFF6949FF),
                    style = Stroke(width = 3.dp.toPx())
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Time filter chips with the first one selected
            FilterChip(
                selected = true,
                onClick = { /* TODO */ },
                label = { Text("Today") },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFFFFF4D9), // Light yellow background
                    selectedLabelColor = Color.Black
                )
            )
            FilterChip(
                selected = false,
                onClick = { /* TODO */ },
                label = { Text("Week") },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFF0F0F0),
                    labelColor = Color.Gray
                )
            )
            FilterChip(
                selected = false,
                onClick = { /* TODO */ },
                label = { Text("Month") },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFF0F0F0),
                    labelColor = Color.Gray
                )
            )
            FilterChip(
                selected = false,
                onClick = { /* TODO */ },
                label = { Text("Year") },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFFF0F0F0),
                    labelColor = Color.Gray
                )
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
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            TextButton(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFF6949FF))
            ) {
                Text(
                    "See All",
                    modifier = Modifier
                        .background(
                            color = Color(0xFFEFE9FF),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    color = Color(0xFF6949FF)
                )
            }
        }

        // Shopping transaction
        TransactionItem(
            iconBackgroundColor = Color(0xFFFFF4D9), // Light yellow
            icon = Icons.Default.ShoppingCart,
            iconTint = Color(0xFFFF9800), // Orange
            title = "Shopping",
            subtitle = "Buy some grocery",
            amount = -120,
            time = "10:00 AM"
        )

        // Subscription transaction
        TransactionItem(
            iconBackgroundColor = Color(0xFFE8E5FF), // Light purple
            icon = Icons.Default.Subscriptions,
            iconTint = Color(0xFF6949FF), // Purple
            title = "Subscription",
            subtitle = "Disney+ Annual",
            amount = -80,
            time = "03:30 PM"
        )

        // Food transaction
        TransactionItem(
            iconBackgroundColor = Color(0xFFFFE8E8), // Light red
            icon = Icons.Default.Restaurant,
            iconTint = Color(0xFFFF5252), // Red
            title = "Food",
            subtitle = "Buy a ramen",
            amount = -32,
            time = "07:30 PM"
        )
    }
}

@Composable
fun TransactionItem(
    iconBackgroundColor: Color,
    icon: ImageVector,
    iconTint: Color,
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
                .size(48.dp)
                .background(
                    color = iconBackgroundColor,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
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
                text = if (amount < 0) "-$${-amount}" else "+$${amount}",
                style = MaterialTheme.typography.bodyLarge,
                color = if (amount < 0) Color.Red else Color(0xFF4CAF50) // Red for negative, green for positive
            )
            Text(
                text = time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

//@Composable
//fun BottomNavigation() {
//    NavigationBar(
//        modifier = Modifier.fillMaxWidth(),
//        containerColor = Color(0xFFFFFFFF)
//    ) {
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//            label = { Text("Home") },
//            selected = true,
//            onClick = { /* TODO */ }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.List, contentDescription = "Transaction") },
//            label = { Text("Transaction") },
//            selected = false,
//            onClick = { /* TODO */ }
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
//            label = { Text("Add") },
//            selected = false,
//            onClick = { /* TODO */ }
//        )
////        NavigationBarItem(
////            icon = { Icon(Icons.Default.PieChart, contentDescription = "Budget") },
////            label = { Text("Budget") },
////            selected = false,
////            onClick = { /* TODO */ }
////        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
//            label = { Text("Profile") },
//            selected = false,
//            onClick = { NavController.navigate("profile") }
//        )
//    }
//}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun FinanceTrackerPreview() {
    SaveSmartTheme {
        FinanceTrackerScreen()
    }
}