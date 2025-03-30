package com.devdroid.savesmart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devdroid.savesmart.R
import com.devdroid.savesmart.model.Transaction
import com.devdroid.savesmart.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionScreen(navController: NavController, transactionViewModel: TransactionViewModel = viewModel()) {
    val transactions by transactionViewModel.transactions.collectAsState()

    LaunchedEffect(Unit) {
        transactionViewModel.fetchTransactions()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F0)) // Light cream background
    ) {
        var selectedMonth by remember { mutableStateOf(getCurrentMonthYear()) }
        val filteredTransactions = transactions.filter {
            SimpleDateFormat(
                "MMMM yyyy",
                Locale.getDefault()
            ).format(Date(it.timestamp.seconds * 1000)) == selectedMonth
        }

        // Create a map to hold grouped transactions
        val transactionsByDate = filteredTransactions
            .sortedByDescending { it.timestamp.seconds }
            .groupBy { transaction ->
                val calendar = Calendar.getInstance()
                val transactionTimestamp = Date(transaction.timestamp.seconds * 1000)
                
                // Set to start of day for comparison
                calendar.time = Date()
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                val startOfToday = calendar.time
                
                // Check for yesterday
                calendar.add(Calendar.DATE, -1)
                val startOfYesterday = calendar.time

                // Check which group this transaction belongs to
                val transactionCalendar = Calendar.getInstance()
                transactionCalendar.time = transactionTimestamp
                transactionCalendar.set(Calendar.HOUR_OF_DAY, 0)
                transactionCalendar.set(Calendar.MINUTE, 0)
                transactionCalendar.set(Calendar.SECOND, 0)
                transactionCalendar.set(Calendar.MILLISECOND, 0)
                
                when {
                    transactionCalendar.time == startOfToday -> "Today"
                    transactionCalendar.time == startOfYesterday -> "Yesterday"
                    else -> SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(transactionTimestamp)
                }
            }
            
        // Create a sorted map to ensure correct display order (Today, Yesterday, then dates in descending order)
        val groupedTransactions = LinkedHashMap<String, List<Transaction>>()
        
        // First add Today if it exists
        if (transactionsByDate.containsKey("Today")) {
            groupedTransactions["Today"] = transactionsByDate["Today"]!!
        }
        
        // Then add Yesterday if it exists
        if (transactionsByDate.containsKey("Yesterday")) {
            groupedTransactions["Yesterday"] = transactionsByDate["Yesterday"]!!
        }
        
        // Then add all other dates in descending order
        transactionsByDate
            .filter { it.key != "Today" && it.key != "Yesterday" }
            .toSortedMap(compareByDescending<String> { 
                // Parse the date string back to Date for comparison
                SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).parse(it)?.time ?: 0
            })
            .forEach { (date, transactions) ->
                groupedTransactions[date] = transactions
            }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // Month Dropdown
            MonthDropdown(selectedMonth) { newMonth -> selectedMonth = newMonth }

            Spacer(modifier = Modifier.height(8.dp))

            // Financial Report Box
            FinancialReportBox()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Transactions",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                groupedTransactions.forEach { (date, transactions) ->
                    item {
                        Text(
                            text = date,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(transactions) { transaction ->
                        TransactionItem(transaction)
                    }
                }
            }
        }
    }
}

// Month Dropdown Component
@Composable
fun MonthDropdown(selectedMonth: String, onMonthSelected: (String) -> Unit) {
    val months = getLastSixMonths()
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Button(onClick = { expanded = true }) {
            Text(text = selectedMonth)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            months.forEach { month ->
                DropdownMenuItem(text = { Text(month) }, onClick = {
                    onMonthSelected(month)
                    expanded = false
                })
            }
        }
    }
}

// Financial Report Box
@Composable
fun FinancialReportBox() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)), // Light Blue
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "See Your Financial Report",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

// Function to get the current month in "MMMM yyyy" format
fun getCurrentMonthYear(): String {
    return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date())
}

// Function to get last six months in "MMMM yyyy" format
fun getLastSixMonths(): List<String> {
    val calendar = Calendar.getInstance()
    return List(6) {
        SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time).also {
            calendar.add(Calendar.MONTH, -1)
        }
    }
}

// Updated TransactionItem to display stored time and description
@Composable
fun TransactionItem(transaction: Transaction) {
    val isIncome = transaction.amount > 0
    val backgroundColor = if (isIncome) Color(0xFFE9FCE9) else Color(0xFFFFE9E9)
    val textColor = if (isIncome) Color(0xFF2E7D32) else Color(0xFFD32F2F)

    val icon = when (transaction.category.lowercase()) {
        "shopping" -> R.drawable.ic_shopping
        "subscription" -> R.drawable.ic_subscription
        "food" -> R.drawable.ic_food
        "salary" -> R.drawable.ic_salary
        "transportation" -> R.drawable.ic_transportation
        else -> R.drawable.ic_default
    }

    val storedTimeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val storedTime = storedTimeFormat.format(Date(transaction.timestamp.seconds * 1000))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(backgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = transaction.category,
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.category,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = transaction.note.ifEmpty { "No description" },
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = if (isIncome) "+ $${transaction.amount}" else " $${transaction.amount}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = storedTime,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
