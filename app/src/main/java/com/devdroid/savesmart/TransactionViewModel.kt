package com.devdroid.savesmart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdroid.savesmart.model.Transaction
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class TransactionViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions
    private val _totalIncome = MutableStateFlow(0)
    val totalIncome: StateFlow<Int> = _totalIncome

    private val _totalExpenses = MutableStateFlow(0)
    val totalExpenses: StateFlow<Int> = _totalExpenses

    fun addIncome(amount: Int, category: String, onComplete: (Boolean) -> Unit) {
        val incomeData = hashMapOf(
            "amount" to amount,
            "category" to category,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("transactions")
            .add(incomeData)
            .addOnSuccessListener {
                fetchTotalIncome()  // Refresh total income
                onComplete(true)    // Success callback
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding income", e)
                onComplete(false)   // Failure callback
            }
    }


    fun addExpense(amount: Int, category: String, onComplete: (Boolean) -> Unit) {
        val expenseData = hashMapOf(
            "amount" to amount,
            "category" to category,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("transactions")
            .add(expenseData)
            .addOnSuccessListener {
                fetchTotalExpenses()  // Refresh total income
                onComplete(true)    // Success callback
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding expense", e)
                onComplete(false)   // Failure callback
            }
    }

    fun fetchTotalIncome() {
        db.collection("transactions")
            .whereGreaterThan("amount", 0)
            .get()
            .addOnSuccessListener { result ->
                val sum = result.documents.sumOf { it.getLong("amount")?.toInt() ?: 0 }
                _totalIncome.value = sum
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error fetching income", e)
            }
    }

    fun fetchTotalExpenses() {
        db.collection("transactions")
            .whereLessThan("amount", 0)
            .get()
            .addOnSuccessListener { result ->
                val sum = result.documents.sumOf { it.getLong("amount")?.toInt() ?: 0 }
                _totalExpenses.value = sum
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error fetching expenses", e)
            }
    }
    fun addTransaction(transaction: Transaction) {
        val transactionData = hashMapOf(
            "amount" to transaction.amount,
            "category" to transaction.category,
            "timestamp" to System.currentTimeMillis()
        )

//    val db
        db.collection("transactions")
            .add(transactionData)
            .addOnSuccessListener {
                if (transaction.amount > 0) {
                    fetchTotalIncome()  // Update income
                } else {
                    fetchTotalExpenses()  // Update expenses
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding transaction", e)
            }
    }
    fun fetchTransactions() {
        db.collection("transactions")
            .get()
            .addOnSuccessListener { result ->
                val transactionList = result.documents.mapNotNull { doc ->
                    val id = doc.id
                    val amount = doc.getLong("amount")?.toInt()
                    val category = doc.getString("category")
                    val type = if (amount != null && amount < 0) "Expense" else "Income"
                    val timestampLong = doc.getLong("timestamp")
                    val note = doc.getString("note") ?: ""

                    if (amount != null && category != null && timestampLong != null) {
                        val timestamp = Timestamp(Date(timestampLong))
                        Transaction(id, amount, category, type, timestamp, note)
                    } else null
                }
                _transactions.value = transactionList
            }
            .addOnFailureListener { e -> // ✅ Should work here
                Log.e("Firestore", "Error fetching transactions", e)
            }

    }

//    fun getCategoryIcon(category: String): String {
//        return when (category) {
//            "Shopping" -> "🛍️"
//            "Subscription" -> "📺"
//            "Food" -> "🍔"
//            "Salary" -> "💰"
//            "Transportation" -> "🚗"
//            else -> "💵"
//        }
//    }

}

