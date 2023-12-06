package com.deaenita.posfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
//import androidx.compose.material3.icons.Icons
//import androidx.compose.material3.icons.filled.Download
//import androidx.compose.material3.icons.filled.Print
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

class CetakStruk : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ReceiptScreen()
            }
        }
    }
}

@Composable
fun ReceiptScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Struk Pembelian",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        DashedDivider()

        Text("Nama Toko: NamaToko", fontWeight = FontWeight.Bold)
        Text("Alamat Toko: AlamatToko")

        DashedDivider()

        Text("Tanggal: ${getCurrentDateTime()}")
        Text("Nomor Struk: 123456")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nama Customer: John Doe", fontWeight = FontWeight.Bold)

        DashedDivider()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Daftar Produk:")

        Spacer(modifier = Modifier.height(8.dp))

        ProductItem("Product 1", 2, 10.0)
        ProductItem("Product 2", 1, 20.0)

        DashedDivider()

        Spacer(modifier = Modifier.height(8.dp))

        Text("Total Item: 3")
        Text("Total Harga: \$30.00", fontWeight = FontWeight.Bold)

        DashedDivider()

        Text("Bayar: 50")
        Text("Kembalian: 20")

        DashedDivider()

        Text("Terima Kasih Atas Kunjungannya")

        Spacer(modifier = Modifier.height(16.dp))

        DashedDivider()

        Text("Poweredby KasirPraktis", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Icon(
            //imageVector = Icons.Default.Print,
            //contentDescription = "Print",
            //modifier = Modifier.size(32.dp)
            //)
            Spacer(modifier = Modifier.width(16.dp))
            //Icon(
            //imageVector = Icons.Default.Download,
            //contentDescription = "Download",
            //modifier = Modifier.size(32.dp)
            //)
        }
    }
}

@Composable
fun ProductItem(name: String, quantity: Int, price: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(text = name)
            Text(text = "Qty: $quantity")
            Text(text = "Harga: \$${price * quantity}")
        }
    }
}

fun getCurrentDateTime(): String {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    return sdf.format(Date())
}

@Composable
fun DashedDivider() {
    Spacer(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .height(2.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewReceiptScreen() {
    ReceiptScreen()
}