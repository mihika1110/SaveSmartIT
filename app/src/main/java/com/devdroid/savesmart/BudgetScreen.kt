import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BudgetScreen() {
    var budget by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("Your budget: $0.00") }
    var selectedMonth by remember { mutableStateOf("Select Month") }
    var selectedCategory by remember { mutableStateOf("Select Category") }
    var monthExpanded by remember { mutableStateOf(false) } // Separate state for month dropdown
    var categoryExpanded by remember { mutableStateOf(false) } // Separate state for category dropdown

    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    val categories = listOf(
        "Shopping", "Traveling", "Food", "Goods", "Gifting"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Month Dropdown
        Box {
            Button(onClick = { monthExpanded = true }) {
                Text(selectedMonth)
            }
            DropdownMenu(
                expanded = monthExpanded,
                onDismissRequest = { monthExpanded = false }
            ) {
                months.forEach { month ->
                    DropdownMenuItem(
                        text = { Text(month) },
                        onClick = {
                            selectedMonth = month
                            monthExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "You don't have a budget.",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Let's make one so you're in control.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Category Dropdown
        Box {
            Button(onClick = { categoryExpanded = true }) {
                Text(selectedCategory)
            }
            DropdownMenu(
                expanded = categoryExpanded,
                onDismissRequest = { categoryExpanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            categoryExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = budget,
            onValueChange = { budget = it },
            label = { Text("Enter your budget") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val budgetValue = budget.toDoubleOrNull()
                displayText = if (budgetValue != null && budgetValue >= 0) {
                    "Your budget for $selectedMonth: $${"%.2f".format(budgetValue)}"
                } else {
                    "Invalid input. Please enter a valid number."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create a budget")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = displayText)

        Spacer(modifier = Modifier.height(380.dp))

        BottomNavigation()
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
fun PreviewBudgetScreen() {
    BudgetScreen()
}
