package com.devdroid.savesmart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdroid.savesmart.model.Transaction
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

class TransactionViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    private val _totalIncome = MutableStateFlow(0)
    val totalIncome: StateFlow<Int> = _totalIncome

    private val _totalExpenses = MutableStateFlow(0)
    val totalExpenses: StateFlow<Int> = _totalExpenses

    // New StateFlows for financial report data
    private val _maxExpenseCategory = MutableStateFlow<Pair<String, Int>?>(null)
    val maxExpenseCategory: StateFlow<Pair<String, Int>?> = _maxExpenseCategory

    private val _maxIncomeCategory = MutableStateFlow<Pair<String, Int>?>(null)
    val maxIncomeCategory: StateFlow<Pair<String, Int>?> = _maxIncomeCategory

    fun addIncome(amount: Int, category: String, note: String = "", onComplete: (Boolean) -> Unit) {
        val user = auth.currentUser
        if (user == null) {
            Log.e("Firestore", "User not logged in")
            onComplete(false)
            return
        }

        val incomeData = hashMapOf(
            "uid" to user.uid,
            "amount" to amount,
            "category" to category,
            "note" to note,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("transactions")
            .add(incomeData)
            .addOnSuccessListener {
                // Refresh all data
                fetchAllTransactions()
                onComplete(true)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding income", e)
                onComplete(false)
            }
    }

    fun addExpense(amount: Int, category: String, note: String = "", onComplete: (Boolean) -> Unit) {
        val user = auth.currentUser
        if (user == null) {
            Log.e("Firestore", "User not logged in")
            onComplete(false)
            return
        }

        // Ensure expense amount is negative
        val negativeAmount = if (amount > 0) -amount else amount

        val expenseData = hashMapOf(
            "uid" to user.uid,
            "amount" to negativeAmount,
            "category" to category,
            "note" to note,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("transactions")
            .add(expenseData)
            .addOnSuccessListener {
                // Refresh all data
                fetchAllTransactions()
                onComplete(true)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding expense", e)
                onComplete(false)
            }
    }

    // This single method fetches all transactions and calculates income and expenses
    fun fetchAllTransactions() {
        val user = auth.currentUser
        if (user == null) {
            Log.e("Firestore", "User not logged in")
            return
        }

        db.collection("transactions")
            .whereEqualTo("uid", user.uid)
            .get()
            .addOnSuccessListener { result ->
                var incomeSum = 0
                var expenseSum = 0
                val transactionList = result.documents.mapNotNull { doc ->
                    val id = doc.id
                    val amount = doc.getLong("amount")?.toInt()
                    val category = doc.getString("category")
                    val timestampLong = doc.getLong("timestamp")
                    val note = doc.getString("note") ?: ""

                    if (amount != null && category != null && timestampLong != null) {
                        // Calculate income and expense totals
                        if (amount > 0) {
                            incomeSum += amount
                        } else {
                            expenseSum += amount // This will add a negative number
                        }

                        val type = if (amount < 0) "Expense" else "Income"
                        val timestamp = Timestamp(Date(timestampLong))

                        Transaction(
                            id = id,
                            amount = amount,
                            category = category,
                            type = type,
                            note = note,
                            timestamp = timestamp
                        )
                    } else null
                }

                // Update all state flows
                _transactions.value = transactionList
                _totalIncome.value = incomeSum
                _totalExpenses.value = expenseSum

                Log.d("SaveSmart", "Fetched transactions: ${transactionList.size}, Income: $incomeSum, Expenses: $expenseSum")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error fetching transactions", e)
            }
    }

    // Get transactions for a specific month
    fun getTransactionsForMonth(month: String): List<Transaction> {
        return _transactions.value.filter {
            SimpleDateFormat("MMMM yyyy", Locale.getDefault())
                .format(Date(it.timestamp.seconds * 1000)) == month
        }
    }

    // Calculate max expense category for a given month
    fun calculateMaxExpenseCategory(month: String) {
        val monthlyTransactions = getTransactionsForMonth(month)

        // Group expenses by category and sum amounts
        val expensesByCategory = monthlyTransactions
            .filter { it.amount < 0 } // Only expenses (negative amounts)
            .groupBy { it.category }
            .mapValues { (_, transactions) ->
                transactions.sumOf { Math.abs(it.amount) }
            }

        // Find category with max expense
        val maxCategory = expensesByCategory.maxByOrNull { it.value }

        _maxExpenseCategory.value = maxCategory?.let { Pair(it.key, it.value) }
    }

    // Calculate max income category for a given month
    fun calculateMaxIncomeCategory(month: String) {
        val monthlyTransactions = getTransactionsForMonth(month)

        // Group incomes by category and sum amounts
        val incomesByCategory = monthlyTransactions
            .filter { it.amount > 0 } // Only incomes (positive amounts)
            .groupBy { it.category }
            .mapValues { (_, transactions) ->
                transactions.sumOf { it.amount }
            }

        // Find category with max income
        val maxCategory = incomesByCategory.maxByOrNull { it.value }

        _maxIncomeCategory.value = maxCategory?.let { Pair(it.key, it.value) }
    }

    // Calculate financial report data for a specific month
    fun calculateFinancialReportData(month: String) {
        calculateMaxExpenseCategory(month)
        calculateMaxIncomeCategory(month)
    }

    // The following methods are kept for compatibility but now just call fetchAllTransactions
    fun fetchTransactions() {
        fetchAllTransactions()
    }

    fun fetchTotalIncome() {
        fetchAllTransactions()
    }

    fun fetchTotalExpenses() {
        fetchAllTransactions()
    }
}
