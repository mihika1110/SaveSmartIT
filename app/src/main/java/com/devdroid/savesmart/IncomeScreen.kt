package com.devdroid.savesmart

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devdroid.savesmart.model.Transaction
import com.devdroid.savesmart.viewmodel.TransactionViewModel
import java.util.*
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    navController: NavController? = null,
    viewModel: TransactionViewModel
) {
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var isCategoryExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var dayInput by remember { mutableStateOf("") }
    var monthInput by remember { mutableStateOf("") }
    var yearInput by remember { mutableStateOf("") }

    val categories = listOf("Salary", "Investments", "Freelance", "Business Income", "Other")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF05A86B))
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Add Income",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController?.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF05A86B))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = amount,
                onValueChange = { if (it.all { char -> char.isDigit() }) amount = it },
                placeholder = { Text("Enter Amount") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            ExposedDropdownMenuBox(
                expanded = isCategoryExpanded,
                onExpandedChange = { isCategoryExpanded = it }
            ) {
                OutlinedTextField(
                    value = category,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text("Select Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCategoryExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    shape = RoundedCornerShape(12.dp)
                )
                ExposedDropdownMenu(
                    expanded = isCategoryExpanded,
                    onDismissRequest = { isCategoryExpanded = false }
                ) {
                    categories.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                category = option
                                isCategoryExpanded = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = dayInput,
                    onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) dayInput = it },
                    placeholder = { Text("DD") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = monthInput,
                    onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) monthInput = it },
                    placeholder = { Text("MM") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = yearInput,
                    onValueChange = { if (it.length <= 4 && it.all { char -> char.isDigit() }) yearInput = it },
                    placeholder = { Text("YYYY") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Button(
                onClick = {
                    if (amount.isEmpty() || category.isEmpty() || dayInput.isEmpty() || monthInput.isEmpty() || yearInput.isEmpty()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        val dateString = "$dayInput/$monthInput/$yearInput"
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val dateObject: Date? = sdf.parse(dateString)

                        val firebaseTimestamp: Timestamp = if (dateObject != null) {
                            Timestamp(dateObject)
                        } else {
                            Timestamp.now() // Use current time if parsing fails
                        }

                        val transaction = Transaction(
                            id = UUID.randomUUID().toString(),
                            amount = amount.toInt(),
                            category = category,
                            type = "Income",
                            date = firebaseTimestamp // ✅ Pass Timestamp instead of String
                        )

                        viewModel.addIncome(transaction.amount, transaction.category) { success ->
                            if (success) {
                                Toast.makeText(context, "Income added!", Toast.LENGTH_SHORT).show()
                                navController?.navigateUp()
                            } else {
                                Toast.makeText(context, "Failed to add income", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Add Income", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
