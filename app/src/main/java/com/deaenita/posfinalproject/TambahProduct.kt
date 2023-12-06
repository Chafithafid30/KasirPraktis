package com.deaenita.posfinalproject

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext

data class ProdukTambah(
    val nama: String,
    val harga: String,
    val stok: String,
    val deskripsi: String,
    val gambarUri: Uri?
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahProdukPos(onSave: (ProdukTambah) -> Unit, onCancel: () -> Unit) {

    var nama by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var stok by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var gambarUri by remember { mutableStateOf<Uri?>(null) }


    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(30.dp)
        )

        Image(
            painter = painterResource(id = gambarUri?.let { R.drawable.ic_launcher_foreground } ?: R.drawable.masterdata),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable {
                },
            contentScale = ContentScale.Crop
        )

        // Input nama
        TextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama Produk") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Input harga
        TextField(
            value = harga,
            onValueChange = { harga = it },
            label = { Text("Harga Jual") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Input stok
        TextField(
            value = stok,
            onValueChange = { stok = it },
            label = { Text("Jumlah Stok") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Input deskripsi
        TextField(
            value = deskripsi,
            onValueChange = { deskripsi = it },
            label = { Text("Tambah Deskripsi") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .heightIn(min = 120.dp)
        )

        // Button simpan
        Button(
            onClick = { onSave(ProdukTambah(nama, harga, stok, deskripsi, gambarUri)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Simpan")
        }

        // Button batal
        Button(
            onClick = onCancel,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Batal")
        }
        // Custom bottom sheet dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text("Pilih Gambar")
                },
                text = {
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Batal")
                    }
                }
            )
        }
    }
}

class TambahProduct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                TambahProdukPos(onSave = {}, onCancel = {})
            }
            TopAppBar(
                title = { Text(text = "Tambah Produk") },
                backgroundColor = Color(0xFFD8BFD8),
                contentColor = Color.White
            )
        }
    }
}
