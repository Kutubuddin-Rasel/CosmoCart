package com.example.ecommerceapp.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.data.cloth
import com.example.ecommerceapp.navigation.Routes
import com.example.ecommerceapp.util.toast

@Composable
fun item(List: State<List<cloth>>, screenName: String,navController: NavController) {
    Log.d("GETSHIRTDATA", List.value.toString())
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(screenName, fontSize = 20.sp, color = Color.White)
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                items(List.value) { cloth ->
                    Card(
                        modifier = Modifier
                            .padding(6.dp)
                            .clickable {
                                val sizesString = cloth.size.joinToString(",")
                                navController.navigate("${Routes.newcode}/${Uri.encode(cloth.image)}/${cloth.productName}/${cloth.price}/$sizesString/${cloth.description}/${cloth.category}")
                            }, elevation = 8.dp
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            AsyncImage(
                                model = Uri.parse(cloth.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(
                                cloth.productName,
                                maxLines = 2,
                                modifier = Modifier.padding(start = 8.dp, end = 6.dp),
                                fontSize = 17.sp
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(cloth.price, color = Color.Red, fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}