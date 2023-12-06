package com.deaenita.posfinalproject

import android.app.Activity
import android.content.res.AssetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deaenita.posfinalproject.R
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class TransaksiSukses : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                TransactionSuccessDialog(context = this)
            }
        }
    }
}


@Composable
fun TransactionSuccessDialog(context: Context = LocalContext.current) {
    var openDialog by remember { mutableStateOf(true) }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                // Handle dismiss request if needed
                openDialog = false
            },
            title = {
                Text(text = "Transaksi Berhasil", textAlign = TextAlign.Center)
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.transactionsukses),
                        contentDescription = "Transaction Success Image",
                        modifier = Modifier
                            .size(72.dp)
                            .padding(bottom = 16.dp)
                    )
                    Text(
                        text = "Terima kasih atas transaksinya!",
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val intentPrintStruk = Intent(context, CetakStruk::class.java)
                        context.startActivity(intentPrintStruk)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Cetak Struk")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Implement your logic for closing the dialog

                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Tutup")
                }
            }
        )
    }
}
