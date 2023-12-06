package com.deaenita.posfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deaenita.posfinalproject.ui.theme.PosFinalProjectTheme

//data class ProdukDetail(
//    val nama: String,
//    val harga: String,
//    val deskripsi: String,
//    val gambarUri: String // Ganti dengan tipe data yang sesuai dengan URI gambar
//)

@Composable
fun DetailProduk(produkDetail: ProdukDetail) {
    POSDetailProduct()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Gambar produk
        Spacer(modifier = Modifier.height(70.dp))

        Image(
            painter = painterResource(R.drawable.sembako), // Ganti dengan gambar aktual
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = produkDetail.nama,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${produkDetail.harga}",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = produkDetail.deskripsi,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

class DetailProduct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val productId = intent.getIntExtra("productId", 0)
            val productName = intent.getStringExtra("productName")
            val productPrice = intent.getDoubleExtra("productPrice", 0.0)
            val productImageResId = intent.getIntExtra("productImageResId", 0)

            val produkDetail = ProdukDetail(
                nama = productName ?: "",
                harga = productPrice.toRupiahFormat(),
                deskripsi = "Stok produk masih tersedia. Lakukan penambahan jumlah produk apabila produk sudah habis.",
                gambarUri = "content://com.deaenita.posfinalproject/${productImageResId}",
            )
            PosFinalProjectTheme {
                DetailProduk(produkDetail = produkDetail)
            }
        }
    }
}

@Composable
fun POSDetailProduct(){
    Column {
        TopAppBar(
            title = { Text(text = "Detail Produk") },
            backgroundColor = Color(0xFFD8BFD8),
            contentColor = Color.White
        )
    }
}