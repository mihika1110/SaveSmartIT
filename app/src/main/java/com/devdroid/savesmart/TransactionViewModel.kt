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

class TransactionViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    private val _totalIncome = MutableStateFlow(0)
    val totalIncome: StateFlow<Int> = _totalIncome

    private val _totalExpenses = MutableStateFlow(0)
    val totalExpenses: StateFlow<Int> = _totalExpenses

    fun addIncome(amount: Int, category: String, onComplete: (Boolean) -> Unit) {
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
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("transactions")
            .add(incomeData)
            .addOnSuccessListener {
                fetchTotalIncome()
                onComplete(true)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding income", e)
                onComplete(false)
            }
    }

    fun addExpense(amount: Int, category: String, onComplete: (Boolean) -> Unit) {
        val user = auth.currentUser
        if (user == null) {
            Log.e("Firestore", "User not logged in")
            onComplete(false)
            return
        }

        val expenseData = hashMapOf(
            "uid" to user.uid,
            "amount" to amount,
            "category" to category,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("transactions")
            .add(expenseData)
            .addOnSuccessListener {
                fetchTotalExpenses()
                onComplete(true)
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding expense", e)
                onComplete(false)
            }
    }

    fun fetchTotalIncome() {
        val user = auth.currentUser
        if (user == null) {
            Log.e("Firestore", "User not logged in")
            return
        }

        db.collection("transactions")
            .whereEqualTo("uid", user.uid)
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
        val user = auth.currentUser
        if (user == null) {
            Log.e("Firestore", "User not logged in")
            return
        }

        db.collection("transactions")
            .whereEqualTo("uid", user.uid)
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

    fun fetchTransactions() {
        val user = auth.currentUser
        if (user == null) {
            Log.e("Firestore", "User not logged in")
            return
        }

        db.collection("transactions")
            .whereEqualTo("uid", user.uid)
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
                _transactions.value = transactionList
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error fetching transactions", e)
            }
    }
}
