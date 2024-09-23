package com.example.ecommerceapp.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.cart
import com.example.ecommerceapp.viewModel.CartViewModel

@Preview(showSystemUi = true)
@Composable
fun Cart() {
    val cartViewModel: CartViewModel = hiltViewModel()
    val cartData=cartViewModel.cartData.collectAsState()
    val total=cartViewModel.total.collectAsState()
    val size=cartData.value.size
    Scaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth().background(Color(16,29,77)).size(100.dp,35.dp), contentAlignment = Alignment.Center) {
                Text(
                    "Cart", fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.lora)), fontWeight = FontWeight.Light, color = Color.White
                )            }
        },
        bottomBar = {
            Column {
                Card(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                        Text("Order Summary")
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        ) {
                            Text("Items:", fontSize = 20.sp, textAlign = TextAlign.Start)
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Text("$size", fontSize = 20.sp)
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        ) {
                            Text("Subtotal:", fontSize = 20.sp, textAlign = TextAlign.Start)
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Text("${String.format("%.2f", total.value)}", fontSize = 20.sp)
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        ) {
                            Text("Delivery Charge:", fontSize = 20.sp, textAlign = TextAlign.Start)
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Text("100", fontSize = 20.sp)
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth().height(1.dp)
                                .background(Color.DarkGray)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        ) {
                            Text("Total:", fontSize = 20.sp, textAlign = TextAlign.Start)
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                val newtotal = String.format("%.2f", total.value)
                                Text("${newtotal.toDouble() + 100}", fontSize = 20.sp)
                            }
                        }
                    }
                }
                Button(onClick = {},colors =ButtonDefaults.buttonColors(Color(16,29,77)), modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth().height(50.dp).clip(
                    CircleShape)) {
                    Text(
                        "Check Out",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(cartData.value){
                caritem(it,cartViewModel)
            }
        }
    }

}

@Composable
private fun caritem(
    cart: cart,
    cartViewModel: CartViewModel
) {
    var number by remember { mutableIntStateOf(1) }
    Card(
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(1.dp, color = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.size(110.dp, 130.dp)) {
                AsyncImage(
                    model = Uri.parse(cart.image.replace("-300x400", "").replace("-228x228", "-500x500")),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .weight(1f))
            {
                Text(
                    cart.productName, fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.lora)), fontWeight = FontWeight.Bold
                )
                Log.d("ALL DATA VALL","${cart.productName} ${cart.category}")
                Row {
                    Text(
                        cart.category, fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.lora)), fontWeight = FontWeight.Light
                    )
                    if(cart.size.isNotEmpty()){
                        Text(
                            " || Size= ${cart.size}", fontSize = 15.sp, color = Color(
                                5,
                                151,
                                133,
                                255
                            ),
                            fontFamily = FontFamily(Font(R.font.lora)), fontWeight = FontWeight.Light
                        )
                    }
                }
                Row() {
                    Text(
                        "Price: ", fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.lora)), fontWeight = FontWeight.Bold
                    )
                    Text(
                        cart.price, fontSize = 16.sp, color = Color.Red,
                        fontFamily = FontFamily(Font(R.font.lora)), fontWeight = FontWeight.Light
                    )
                }

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.Bottom).padding(bottom = 10.dp)
              //  modifier = Modifier.fillMaxHeight() // To center it vertically within the Row
            ) {
                IconButton(
                    onClick = {
                        cartViewModel.removedata(cart.id)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color(16, 29, 77),
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            if(number>1){
                                number--
                                cartViewModel.decrease(cart)
                            }
                        },
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                            .size(25.dp)
                            .border(2.dp,Color.Blue, CircleShape)
                            .clip(CircleShape)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.minus),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(Color.Blue)
                        )
                    }
                    Text(
                        "$number",
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        fontSize = 20.sp
                    )
                    IconButton(
                        onClick = {
                            number++
                            cartViewModel.increase(cart)
                        },
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                            .size(25.dp)
                            .border(2.dp,Color.Blue, CircleShape)
                            .clip(CircleShape)

                    ) {
                        Image(
                            painter = painterResource(R.drawable.plus),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(Color.Blue)
                        )
                    }
                }
            }
            Spacer(Modifier.width(10.dp))
        }
    }
}
