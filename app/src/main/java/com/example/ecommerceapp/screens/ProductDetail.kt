package com.example.ecommerceapp.screens

import android.net.Uri
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.cart
import com.example.ecommerceapp.navigation.Routes
import com.example.ecommerceapp.viewModel.AddtoCartViewModel

@Composable
fun product(
    navController: NavController,
    image: String,
    productName: String,
    price: String,
    description: String,
    category: String,
    sizes: List<String>? = null // Optional sizes parameter
) {
    val addtoCartViewModel: AddtoCartViewModel = hiltViewModel()
    val selectedSize = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.6f)
        ) {
            AsyncImage(
                model = Uri.parse(image.replace("-300x400", "").replace("-400x300", "").replace("-228x228", "-500x500")),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.4f)
        ) {
            Column(modifier = Modifier.padding(7.dp)) {
                Text(
                    productName,
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_variablefont_wght)),
                    fontWeight = FontWeight.ExtraBold,
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement =Arrangement.Center,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Price: ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.lora))
                    )
                    Text(
                        price,
                        fontSize = 20.sp,
                            color = Color.Red,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.lora))
                    )
                }
                Text(
                    "Description of the product: ",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.lora))
                )
                Text(
                    description,
                    fontFamily = FontFamily(Font(R.font.open_sans_light)),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(5.dp))

                // Show sizes if available
                sizes?.let {
                    if (it.isNotEmpty()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.horizontalScroll(rememberScrollState())
                        ) {
                            Text("Sizes: ", fontFamily = FontFamily(Font(R.font.lora)), fontSize = 20.sp)
                            it.forEach { size ->
                                Spacer(modifier = Modifier.padding(start = 15.dp))
                                Button (
                                    onClick = { selectedSize.value = size },
                                    colors =ButtonDefaults.buttonColors(
                                        backgroundColor = if (selectedSize.value == size) {
                                            Color(97, 118, 196, 255)
                                        } else {
                                            Color(16, 29, 77)
                                        }
                                    )
                                ) {
                                    Text(
                                        size,
                                        color = Color.White,
                                        fontFamily = FontFamily(Font(R.font.lora)),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Button(
                    onClick = {
                        val cartItem = cart("", image, productName, category, price, if (sizes != null) selectedSize.value else "")
                        addtoCartViewModel.addTOCart(cartItem)
                        navController.navigate(Routes.cart)
                    },
                    colors = ButtonDefaults.buttonColors(Color(16, 29, 77)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    Text("Add to Cart", color = Color.White, fontSize = 20.sp)
                }
            }
        }
    }
}
