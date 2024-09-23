package com.example.ecommerceapp.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.button
import com.example.ecommerceapp.data.cloth
import com.example.ecommerceapp.data.homecategories
import com.example.ecommerceapp.navigation.Routes
import com.example.ecommerceapp.viewModel.EarbudsViewModel
import com.example.ecommerceapp.viewModel.HomeViewModel
import com.example.ecommerceapp.viewModel.SamrtWatchViewModel
import com.example.ecommerceapp.viewModel.SareeViewModel
import com.example.ecommerceapp.viewModel.shirtViewModel

@Composable
fun Home(navController:NavController) {
    val homeViewModel:HomeViewModel= hiltViewModel()
    val selectedImageUri=homeViewModel.selectedImageUri.collectAsState()
    val username=homeViewModel.username.collectAsState()
    val shirtViewModel:shirtViewModel= hiltViewModel()
    shirtViewModel.getData()
    val shirtdatalist=shirtViewModel.datalist.collectAsState()
    val SareeViewModel:SareeViewModel= hiltViewModel()
    SareeViewModel.getData()
    val sareeDataList=SareeViewModel.datalist.collectAsState()

    val earbudsViewModel:EarbudsViewModel= hiltViewModel()
    earbudsViewModel.getData()
    val earbudsDatalist=earbudsViewModel.datalist.collectAsState()
    val samrtWatchViewModel:SamrtWatchViewModel= hiltViewModel()
    samrtWatchViewModel.getData()
    val smartwatchdatalist=samrtWatchViewModel.datalist.collectAsState()
    val font= FontFamily(Font(R.font.lora))
    val category= listOf(
        button("Panjabi", R.drawable.panjabi, Routes.panjabi),
        button("Salwar", R.drawable.salwarkameez, Routes.salwarKameez),
       button("Camera", R.drawable.camera, Routes.camera),
       button("Headphone", R.drawable.headphone, Routes.headphone),
       button("All", R.drawable.all, Routes.allCategories)
    )
    val showlist= listOf(
        homecategories("Men Shirt",Routes.shirt,shirtdatalist),
        homecategories("Smart Watch",Routes.smartWatch,smartwatchdatalist),
        homecategories("Saree",Routes.saree,sareeDataList),
        homecategories("Earbuds",Routes.earbuds,earbudsDatalist),
    )
    Column(modifier = Modifier.padding(start = 5.dp).verticalScroll(rememberScrollState())) {
        Box(
            modifier = Modifier.padding(start = 8.dp, top = 5.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Routes.profile)
                        }
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = if(selectedImageUri.value==null){
                            R.drawable.name
                        } else {
                            selectedImageUri.value
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .width(60.dp) // Explicit width
                            .height(60.dp) // Explicit height
                            .clip(CircleShape), // Optional: Clip to a circle if needed
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = username.value,
                        fontFamily = font,
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
        Text("Category", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = font)
        Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
            category.forEach {
                IconButton(
                    onClick = {
                        navController.navigate(it.route)
                    }
                ) {
                    Column(modifier =Modifier.padding(6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(it.img), contentDescription = null, modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape), contentScale = ContentScale.FillBounds)
                        Text(it.text, fontSize = 14.sp, fontWeight = FontWeight.Medium, fontFamily = font)
                    }
                }
                Spacer(Modifier.padding(horizontal = 5.dp))
            }
        }
            showlist.forEach {homecategories->
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(homecategories.porductName, fontSize = 20.sp, textAlign = TextAlign.Start)
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            TextButton(onClick = {
                                navController.navigate(homecategories.sellAllRoute)
                            }) {
                                Text("See All", fontSize = 17.sp)
                            }
                        }
                    }
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(homecategories.datalist.value){
                            show(navController,it)
                        }
                    }
                    Spacer(Modifier.height(5.dp))
                }
        }
    }
}
@Composable
fun show(navController: NavController, cloth: cloth){
    Card(
        modifier = Modifier
            .padding(6.dp)
            .size(110.dp,140.dp)
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
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth().size(100.dp,100.dp)
            )

            Text(
                cloth.productName,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp, end = 6.dp),
                fontSize = 13.sp
            )
            Text(cloth.price, color = Color.Red, fontSize = 13.sp)
        }
    }
}