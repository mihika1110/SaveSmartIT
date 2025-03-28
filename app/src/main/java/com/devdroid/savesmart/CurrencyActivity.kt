
package com.devdroid.savesmart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast


class CurrencyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CurrencySelectionScreen()
            }
        }
    }
}

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

@Composable
fun CurrencySelectionScreen() {
    val context = LocalContext.current
    var selectedCurrency by remember { mutableStateOf<Currency?>(null) }

    val currencies = listOf(
        Currency("USD", "United States Dollar", "$"),
        Currency("EUR", "Euro", "€"),
        Currency("JPY", "Japanese Yen", "¥"),
        Currency("GBP", "British Pound", "£"),
        Currency("AUD", "Australian Dollar", "A$"),
        Currency("CAD", "Canadian Dollar", "C$"),
        Currency("CHF", "Swiss Franc", "CHF"),
        Currency("CNY", "Chinese Yuan", "¥"),
        Currency("INR", "Indian Rupee", "₹"),
        Currency("BRL", "Brazilian Real", "R$"),
        Currency("RUB", "Russian Ruble", "₽"),
        Currency("KRW", "South Korean Won", "₩"),
        Currency("SGD", "Singapore Dollar", "S$"),
        Currency("MXN", "Mexican Peso", "Mex$"),
        Currency("SAR", "Saudi Riyal", "﷼"),
        Currency("AED", "UAE Dirham", "د.إ"),
        Currency("TRY", "Turkish Lira", "₺"),
        Currency("ZAR", "South African Rand", "R"),
        Currency("SEK", "Swedish Krona", "kr"),
        Currency("NOK", "Norwegian Krone", "kr"),
        Currency("DKK", "Danish Krone", "kr"),
        Currency("PLN", "Polish Złoty", "zł"),
        Currency("HKD", "Hong Kong Dollar", "HK$"),
        Currency("THB", "Thai Baht", "฿")
//        Currency("IDR", "Indonesian Rupiah", "Rp")


        )


    Scaffold(
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select Your Currency",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(currencies) { currency ->
                        CurrencyCapsuleButton(
                            currency = currency,
                            isSelected = selectedCurrency == currency,
                            onSelected = {
                                selectedCurrency = currency
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                if (selectedCurrency != null) {
                    Button(
                        onClick = {
                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            val firestore = FirebaseFirestore.getInstance()

                            if (userId != null && selectedCurrency != null) {
                                val userRef = firestore.collection("users").document(userId)

                                userRef.update("currency", selectedCurrency!!.code)
                                    .addOnSuccessListener {
                                        // ✅ Store selected currency in SharedPreferences
                                        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                                        sharedPreferences.edit()
                                            .putString("SELECTED_CURRENCY", selectedCurrency!!.code)
                                            .apply()

                                        Toast.makeText(
                                            context,
                                            "Currency saved!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        // Navigate to HomeScreen
                                        val intent = Intent(context, Homescreen::class.java)
                                        context.startActivity(intent)
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            context,
                                            "Error saving currency: ${it.message}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please select a currency first!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(bottom = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6C63FF),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Continue with ${selectedCurrency?.code}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CurrencyCapsuleButton(
    currency: Currency,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(32.dp))
            .background(
                color = if (isSelected) Color(0xFF6C63FF).copy(alpha = 0.2f)
                else Color.LightGray.copy(alpha = 0.3f)
            )
            .clickable { onSelected() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currency.code,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color(0xFF6C63FF) else Color.Black
            )
            Text(
                text = currency.symbol,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}
