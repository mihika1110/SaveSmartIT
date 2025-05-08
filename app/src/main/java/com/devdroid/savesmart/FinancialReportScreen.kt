package com.devdroid.savesmart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devdroid.savesmart.utils.DateUtils
import com.devdroid.savesmart.viewmodel.TransactionViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FinancialReportScreen(
    navController: NavController,
    viewModel: TransactionViewModel,
    selectedMonth: String = DateUtils.getCurrentMonthYear()
) {
    // Calculate financial report data for the selected month
    LaunchedEffect(selectedMonth) {
        viewModel.calculateFinancialReportData(selectedMonth)
    }

    // Collect state flows
    val totalExpenses by viewModel.totalExpenses.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()
    val maxExpenseCategory by viewModel.maxExpenseCategory.collectAsState()
    val maxIncomeCategory by viewModel.maxIncomeCategory.collectAsState()

    // Pager state
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    // Quotes for the third page
    val financialQuotes = listOf(
        Pair("Warren Buffett", "Do not save what is left after spending, but spend what is left after saving."),
        Pair("Dave Ramsey", "A budget is telling your money where to go instead of wondering where it went."),
        Pair("Robert Kiyosaki", "It's not how much money you make, but how much money you keep."),
        Pair("Suze Orman", "When you understand that your self-worth is not determined by your net-worth, then you'll have financial freedom."),
        Pair("Benjamin Franklin", "Beware of little expenses; a small leak will sink a great ship.")
    )

    // Randomly select a quote
    val randomQuote = remember { financialQuotes.random() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F0))
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text("Financial Report") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF6200EE),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        // Month display
        Text(
            text = selectedMonth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF6200EE)
        )

        // Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> ExpensePage(totalExpenses, maxExpenseCategory)
                1 -> IncomePage(totalIncome, maxIncomeCategory)
                2 -> QuotePage(randomQuote.first, randomQuote.second)
            }
        }

        // Page indicator
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(3) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color(0xFF6200EE) else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(iteration)
                            }
                        }
                )
            }
        }

        // Navigation buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                enabled = pagerState.currentPage > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE),
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < 2) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            navController.navigateUp()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE)
                )
            ) {
                Text(if (pagerState.currentPage < 2) "Next" else "Done")
            }
        }
    }
}

@Composable
fun ExpensePage(totalExpenses: Int, maxExpenseCategory: Pair<String, Int>?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Monthly Expenses",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD32F2F)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "$${abs(totalExpenses)}",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD32F2F)
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (maxExpenseCategory != null) {
                Text(
                    text = "Highest Expense Category",
                    fontSize = 18.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = maxExpenseCategory.first,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$${maxExpenseCategory.second}",
                    fontSize = 20.sp,
                    color = Color(0xFFD32F2F)
                )
            } else {
                Text(
                    text = "No expense data available for this month",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun IncomePage(totalIncome: Int, maxIncomeCategory: Pair<String, Int>?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Monthly Income",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "$${totalIncome}",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (maxIncomeCategory != null) {
                Text(
                    text = "Highest Income Category",
                    fontSize = 18.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = maxIncomeCategory.first,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$${maxIncomeCategory.second}",
                    fontSize = 20.sp,
                    color = Color(0xFF2E7D32)
                )
            } else {
                Text(
                    text = "No income data available for this month",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun QuotePage(author: String, quote: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF0E6FF)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_default), // Use an appropriate icon
                contentDescription = "Quote",
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF6200EE)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "\"$quote\"",
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = Color(0xFF6200EE)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "- $author",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

// Remove this function
// fun getCurrentMonthYear(): String {
//     return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date())
// }