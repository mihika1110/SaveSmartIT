import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailedBudgetScreen() {
    // State to manage categories
    val categories = remember {
        mutableStateListOf(
            Category("Shopping", 1200.0, 1000.0, 0.0, true),
            Category("Transportation", 350.0, 700.0, 350.0, false)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display each category
        categories.forEachIndexed { index, category ->
            BudgetCategoryScreen(
                categoryName = category.name,
                spentAmount = category.spentAmount,
                totalAmount = category.totalAmount,
                remainingAmount = category.remainingAmount,
                isOverBudget = category.isOverBudget,
                onDelete = { categories.removeAt(index) } // Remove the category
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Create Budget Button
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create a budget")
        }
    }
}

@Composable
fun BudgetCategoryScreen(
    categoryName: String,
    spentAmount: Double,
    totalAmount: Double,
    remainingAmount: Double,
    isOverBudget: Boolean,
    onDelete: () -> Unit // Callback for delete action
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Row for Category Name and Delete Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Category Name
                Text(
                    text = categoryName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Delete Button
                IconButton(
                    onClick = onDelete // Trigger the delete callback
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Spent Amount
            Text(
                text = "Spent: $${"%.2f".format(spentAmount)}",
                fontSize = 16.sp,
                color = Color.Gray
            )

            // Total Amount
            Text(
                text = "Total: $${"%.2f".format(totalAmount)}",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Remaining Amount
            Text(
                text = "Remaining: $${"%.2f".format(remainingAmount)}",
                fontSize = 16.sp,
                color = if (isOverBudget) Color.Red else Color.Green
            )

            // Over Budget Warning
            if (isOverBudget) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "You are over budget!",
                    fontSize = 14.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Data class for Category
data class Category(
    val name: String,
    val spentAmount: Double,
    val totalAmount: Double,
    val remainingAmount: Double,
    val isOverBudget: Boolean
)

@Preview(showBackground = true)
@Composable
fun PreviewDetailedBudgetScreen() {
    DetailedBudgetScreen()
}
