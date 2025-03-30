package com.devdroid.savesmart

fun getCategoryIcon(category: String): String {
    return when (category) {
        "Shopping" -> "🛍️"
        "Subscription" -> "📺"
        "Food" -> "🍔"
        "Salary" -> "💰"
        "Transportation" -> "🚗"
        else -> "💵"
    }
}
