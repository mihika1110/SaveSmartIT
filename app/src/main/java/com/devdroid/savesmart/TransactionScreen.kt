package com.devdroid.savesmart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier // ✅ Correct import
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devdroid.savesmart.model.Transaction
import com.devdroid.savesmart.viewmodel.TransactionViewModel

@Composable
fun TransactionScreen(navController: NavController, transactionViewModel: TransactionViewModel) {
    val transactionViewModel: TransactionViewModel = viewModel()
    val transactions by transactionViewModel.transactions.collectAsState()

    LaunchedEffect(Unit) {
        transactionViewModel.fetchTransactions()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Transactions", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = transaction.category, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Amount: ${transaction.amount}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}