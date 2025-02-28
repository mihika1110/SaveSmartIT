package com.devdroid.savesmart

import android.os.Build
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    navController: NavController? = null,
    onAmountAdded: (Int) -> Unit = {}
) {
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var isCategoryExpanded by remember { mutableStateOf(false) }
    var showUpdatedAmount by remember { mutableStateOf(false) }

    // Date input fields
    var dayInput by remember { mutableStateOf("") }
    var monthInput by remember { mutableStateOf("") }
    var yearInput by remember { mutableStateOf("") }

    val categories = listOf(
        "Salary",
        "Investments",
        "Rental Income",
        "Refunds",
        "Bonuses",
        "Pension",
        "Freelance",
        "Business Income",
        "Donations",
        "Other"
    )

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
                IconButton(onClick = {
                    // Navigate back to home screen
                    navController?.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF05A86B)
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = if (showUpdatedAmount) "Updated Amount" else "Enter Amount",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = if (amount.isEmpty()) "$0.00" else "$$amount",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 52.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { if (it.all { char -> char.isDigit() }) amount = it },
                        placeholder = { Text("Enter Amount") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF6C63FF),
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
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
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFF5F5F5),
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color(0xFF6C63FF),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
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
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFF5F5F5),
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color(0xFF6C63FF),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )

                        OutlinedTextField(
                            value = monthInput,
                            onValueChange = { if (it.length <= 2 && it.all { char -> char.isDigit() }) monthInput = it },
                            placeholder = { Text("MM") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFF5F5F5),
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color(0xFF6C63FF),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )

                        OutlinedTextField(
                            value = yearInput,
                            onValueChange = { if (it.length <= 4 && it.all { char -> char.isDigit() }) yearInput = it },
                            placeholder = { Text("YYYY") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color(0xFFF5F5F5),
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color(0xFF6C63FF),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            )
                        )
                    }

                    Button(
                        onClick = {
                            showUpdatedAmount = true

                            // Add the amount to income and navigate back
                            if (amount.isNotEmpty()) {
                                val amountValue = amount.toInt()
                                onAmountAdded(amountValue)

                                // After a brief delay, navigate back to the home screen
                                // In a real app, you might want to add some animation here
                                navController?.navigateUp()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6C63FF)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Continue",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}