package com.deaenita.posfinalproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class ProductKeranjang(val id: Int, val name: String, val price: Double, val imageResId: Int, var quantity: Int = 0)

@Composable
fun POSKeranjang(totalAmount: Double) {
    val isAddingProduct by remember { mutableStateOf(false) }
    var totalAmount by remember { mutableStateOf(0.0) }
    //var quantity by remember { mutableStateOf(0) }


    val products = listOf(
        ProductKeranjang(1, "Beras", 15.000, R.drawable.beras),
        ProductKeranjang(2, "Telur", 27.000, R.drawable.telur),
        ProductKeranjang(3, "Cabai", 50.000, R.drawable.cabai),
        //ProductTransaction(4, "Tomat", 5.000, R.drawable.tomato),
        //ProductTransaction(5, "Bawang", 10.000, R.drawable.onion),
        // ...tambahkan produk lain jika diperlukan

    )

//    LazyColumn(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        items(products) { product ->
//            ProductListKeranjang(product = product) {
//                // Update totalAmount when the quantity changes
//                totalAmount = calculateTotalAmount(products)
//            }
//        }
//    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(end = 30.dp)
            .padding(bottom = 20.dp), // Ganti warna latar belakang Card sesuai keinginan Anda
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total: Rp${totalAmount}",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Magenta

            )

            // Button Proses Pesanan
            val context = LocalContext.current
            val intentCheckout = Intent(context, Checkout::class.java)

            Button(
                onClick = {
                    // Pindah ke activity baru dengan membawa data totalAmount
                    context.startActivity(intentCheckout)
                    intentCheckout.putExtra("totalAmount", totalAmount)

                },
                modifier = Modifier.height(32.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text("Proses Pesanan", style = MaterialTheme.typography.bodySmall, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun ProductListKeranjang(product: ProductKeranjang, onQuantityChange: (Int) -> Unit) {
    val quantity by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(shape = RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.labelLarge)
            Text(
                text = "Rp${product.price}",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Tombol (+) dan (-) serta jumlah item
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (product.quantity > 0) {
                        product.quantity--
                        onQuantityChange(quantity) // Panggil onQuantityChange setelah mengubah jumlah
                    }
                },
                modifier = Modifier.wrapContentSize(),
                contentPadding = PaddingValues(2.dp)
            ) {
                Text("-", style = MaterialTheme.typography.labelLarge)
            }

            Text(
                text = "${product.quantity}",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .width(20.dp)
                    .padding(horizontal = 4.dp),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {
                    product.quantity++
                    onQuantityChange(quantity) // Panggil onQuantityChange setelah mengubah jumlah
                },
                modifier = Modifier.wrapContentSize(),
                contentPadding = PaddingValues(2.dp)
            ) {
                Text("+", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}


// Fungsi untuk menghitung totalAmount dari seluruh produk
//fun calculateTotalAmount(products: List<ProductTransaction>): Double {
//    return products.sumByDouble { it.price * it.quantity }
//}

class Keranjang : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Retrieve totalAmount from the intent
            val totalAmount = intent.getDoubleExtra("totalAmount", 0.0)

            setContent {
                POSKeranjang(totalAmount = totalAmount)
            }
        }
    }
}
