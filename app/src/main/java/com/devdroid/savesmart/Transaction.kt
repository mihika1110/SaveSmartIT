package com.devdroid.savesmart.model

import com.google.firebase.Timestamp

data class Transaction(
    val id: String = "",
    val amount: Int = 0,
    val category: String = "",
    val type: String = "",
    val date: Timestamp = Timestamp.now(),
    val description: String = "",
    val icon: String = ""
)



