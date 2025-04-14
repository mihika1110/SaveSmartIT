package com.devdroid.savesmart.model

import com.google.firebase.Timestamp

data class Transaction(
    val id: String = "",
    val amount: Int = 0,
    val category: String = "",
    val type: String = "",
    val date: Timestamp = Timestamp.now(),
    val description: String = "",
    val icon: String = "",
    val note: String = "", // ✅ Add this field
    val timestamp: Timestamp = Timestamp.now() // Will be overridden by the actual timestamp when creating the object
)
//mihika testing