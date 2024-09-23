package com.example.ecommerceapp.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.button
import com.example.ecommerceapp.navigation.Routes
import com.example.ecommerceapp.viewModel.SearchViewModel


@Composable
fun AllCategories(navController: NavController) {
    val Menbuttonlist= listOf(
        button("Men Shirt", R.drawable.shirt3,Routes.shirt),
        button("Men Pant", R.drawable.pant2,Routes.pant),
        button("Polo", R.drawable.polo1,Routes.polo),
        button("Panjabi", R.drawable.panjabi,Routes.panjabi),
        button("T-Shirt", R.drawable.tshirt,Routes.t_shirt)
    )
    val Womenbuttonlist= listOf(
        button("Salwar\n Kameez",R.drawable.salwarkameez,Routes.salwarKameez),
        button("Kameez", R.drawable.kameez,Routes.kameez),
        button("Saree", R.drawable.saree,Routes.saree),
    )
    val gadetbuttonlist= listOf(
        button("Camera",R.drawable.camera,Routes.camera),
        button("Earbuds", R.drawable.earbuds,Routes.earbuds,),
        button("TV", R.drawable.tv2,Routes.tv),
        button("Smart\n Watch", R.drawable.watch2,Routes.smartWatch),
    )
    val accessoriesbuttonlist= listOf(
        button("Headphone",R.drawable.headphone,Routes.headphone),
        button("Speaker", R.drawable.speaker,Routes.speaker),
        button("Keyboard", R.drawable.keyboard,Routes.keyboard),
        button("Mouse", R.drawable.mouse,Routes.mouse),
    )
    val font= FontFamily(Font(R.font.lora))
    Scaffold{
        Box(modifier = Modifier.padding(it).fillMaxSize().paint(painter = painterResource(R.drawable.ba), contentScale = ContentScale.Crop)) {
            Column(modifier = Modifier.fillMaxSize().padding(start = 8.dp, top = 45.dp)) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text("All Categories", fontSize = 25.sp, fontWeight = FontWeight.Bold, fontFamily = font)
                }
                Text("Men", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = font)
                Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
                    Menbuttonlist.forEach {
                        IconButton(
                            onClick = {
                                navController.navigate(it.route)
                            }
                        ) {
                            Column(modifier =Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(painter = painterResource(it.img), contentDescription = null, modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape), contentScale = ContentScale.FillBounds)
                                Text(it.text, fontSize = 17.sp, fontWeight = FontWeight.Medium, fontFamily = font)
                            }
                        }
                        Spacer(Modifier.padding(horizontal = 5.dp))
                    }
                }
                Spacer(Modifier.height(6.dp))
                Text("Women", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = font)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Womenbuttonlist.forEach {
                        IconButton(
                            onClick = {
                                navController.navigate(it.route)
                            }
                        ) {
                            Column(modifier =Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(painter = painterResource(it.img), contentDescription = null, modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape), contentScale = ContentScale.Crop)
                                Text(it.text, fontSize = 17.sp, fontWeight = FontWeight.Medium, fontFamily = font)
                            }
                        }
                        Spacer(Modifier.padding(horizontal = 5.dp))
                    }
                }
                Spacer(Modifier.height(6.dp))
                Text("Gadget", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = font)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    gadetbuttonlist.forEach {
                        IconButton(
                            onClick = {
                                navController.navigate(it.route)
                            }
                        ) {
                            Column(modifier =Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(painter = painterResource(it.img), contentDescription = null, modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape), contentScale = ContentScale.Crop)
                                Text(it.text, fontSize = 17.sp, fontWeight = FontWeight.Medium, fontFamily = font)
                            }
                        }
                        Spacer(Modifier.padding(horizontal = 5.dp))
                    }
                }
                Text("Accessories", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = font)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    accessoriesbuttonlist.forEach {
                        IconButton(
                            onClick = {
                                navController.navigate(it.route)
                            }
                        ) {
                            Column(modifier =Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(painter = painterResource(it.img), contentDescription = null, modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape), contentScale = ContentScale.Crop)
                                Text(it.text, fontSize = 17.sp, fontWeight = FontWeight.Medium, fontFamily = font)
                            }
                        }
                        Spacer(Modifier.padding(horizontal = 5.dp))
                    }
                }
            }
        }
    }
}
