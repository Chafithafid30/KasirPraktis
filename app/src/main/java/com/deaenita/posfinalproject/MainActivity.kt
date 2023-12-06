package com.deaenita.posfinalproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.*
import androidx.compose.material.Card
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun POSHomePage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        TopAppBar(
            title = { Text(text = "Kasir Praktis",
                letterSpacing = 5.sp,fontSize = 18.sp) },
            backgroundColor = Color(0xFFD8BFD8),
            contentColor = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        TotalIncomeView(totalIncome = 1500000.0)
        Spacer(modifier = Modifier.height(65.dp))
        val context = LocalContext.current
        val intentMasterData = Intent(context, MasterData::class.java)
        val intentQr = Intent(context, Qr::class.java)
        val intentTransaction = Intent(context, Transaction::class.java)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuIcon(
                icon = painterResource(id = R.drawable.masterdata),
                label = "Master Data",
                onClick = {
                    context.startActivity(intentMasterData)
                }
            )
            MenuIcon(
                icon = painterResource(id = R.drawable.transaction),
                label = "Transaksi",
                onClick = {
                    context.startActivity(intentTransaction)

                }
            )
            MenuIcon(
                icon = painterResource(id = R.drawable.qrscan),
                label = "QR",
                onClick = {
                    context.startActivity(intentQr)
                }
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "History Pesanan",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 10.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transaksi 1",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(start = 20.dp)
                    )
                    Text(
                        text = "Rp.100.000,00",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(end = 20.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transaksi 2",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(start = 20.dp)
                    )
                    Text(
                        text = "Rp.100.000,00",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(8.dp)
                            .padding(end = 20.dp)
                    )
                }
//                TextButton(
//                    onClick = { /* Handle button click */ },
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .align(Alignment.End),
//                    colors = ButtonDefaults.textButtonColors(Color.Blue)
//                ) {
//                    Text(
//                        text = "Lihat Detail",
//                        color = Color.White// Ubah warna teks
//                    )
//               }
            }
        }
    }
}

@Composable
fun MenuIcon(icon: Painter, label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color.LightGray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label)
        }
    }
}


@Composable
fun TotalIncomeView(totalIncome: Double) {
    // Container untuk total pendapatan
    val formattedTotalIncome = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        .format(totalIncome)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .height(80.dp)
            .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(100.dp)
                .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Total Income: $formattedTotalIncome",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun AppBar() {

}

@Composable
fun MenuButton(text: String) {
    Button(
        onClick = {
                  /* Action for menu item */
                  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(Color.Magenta),
        shape = MaterialTheme.shapes.medium,
        content = {
            Text(text = text)
        },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    POSHomePage()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POSHomePage()
        }
    }
}
