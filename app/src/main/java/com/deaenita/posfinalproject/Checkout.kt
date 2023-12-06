package com.deaenita.posfinalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity


data class ProductCheckout(val id: Int, val name: String, val price: Double, val imageResId: Int)
data class CartItems(val product: Product, var quantity: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun POSCheckout(context: Context) {
    var totalItems by remember { mutableStateOf(0) }
    var totalPrice by remember { mutableStateOf(0.0) }



    Column {
        TopAppBar(title = { Text(text = "Transaksi") },
            contentColor = MaterialTheme.colorScheme.primary)
    }
    val productTrans = listOf(
        ProductTransaction(1, "Beras", 35000.00, R.drawable.beras),
        ProductTransaction(2, "Telur", 27000.00, R.drawable.telur),
        ProductTransaction(3, "Cabai", 75000.00, R.drawable.cabai),
        ProductTransaction(4, "Tomat", 15000.00, R.drawable.tomato),
        ProductTransaction(5, "Bawang Merah", 25000.00, R.drawable.onion),
        ProductTransaction(6, "Bawang", 27000.00, R.drawable.bawang),
        ProductTransaction(7, "Daun Bawang", 17000.00, R.drawable.daunbawang),
        ProductTransaction(8, "Labu", 35000.00, R.drawable.labu),
        ProductTransaction(9, "Jahe", 30000.00, R.drawable.jahe),
        ProductTransaction(10, "Pala", 50000.00, R.drawable.pala),
        ProductTransaction(11, "Vanili", 1000000.00, R.drawable.vanilla),
    )


    Column {
        TopAppBar(
            title = { Text(text = "Transaksi") },
            backgroundColor = Color(0xFFD8BFD8),
            contentColor = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)

        ) {


//                ProductListItem(product = product) { quantity ->
//                    // Update totalItems dan totalPrice ketika kuantitas berubah
//                    addToCart(product.price * quantity, quantity)
//                }


            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { /* Action when clicked */ }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Jumlah Item: ${totalItems}",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "${totalPrice.toRupiahFormatCheckout()}",
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, Checkout::class.java)
                            intent.putExtra("TOTAL_ITEMS", totalItems)
                            intent.putExtra("TOTAL_PRICE", totalPrice)
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(text = "Proses Pesanan")
                    }
                }
            }


    }
}

@Composable
fun ProductListCheckout(product: ProductTransaction, onQuantityChange: (Int) -> Unit) {
    var jumlahProduk by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.clickable {
                //onItemClick(product)
            }
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.price.toRupiahFormats(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (jumlahProduk > 0) {
                        jumlahProduk--
                        onQuantityChange(jumlahProduk)

                    }
                },
                modifier = Modifier.wrapContentSize(),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
            ) {
                Text("-")
            }
            Text(
                text = jumlahProduk.toString(),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Button(
                onClick = { jumlahProduk++
                    onQuantityChange(jumlahProduk)
                },
                modifier = Modifier.wrapContentSize(),
            ) {
                Text("+")
            }
        }
    }
}



fun Double.toRupiahFormatCheckout(): String {
    val formattedValue = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("id", "ID"))
        .format(this)
    return formattedValue.replace("IDR", "Rp. ")
}

class Checkout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POSCheckout(context = this)
        }
    }
}