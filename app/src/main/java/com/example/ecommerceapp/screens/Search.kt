package com.example.ecommerceapp.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.navigation.Routes
import com.example.ecommerceapp.viewModel.SearchViewModel
import kotlinx.coroutines.delay

@Composable
fun Search(navController:NavController) {
    val searchViewModel:SearchViewModel= hiltViewModel()
    val search=searchViewModel.search.collectAsState()
    val searchlist=searchViewModel.searchlist.collectAsState()
    LaunchedEffect(search.value) {
        delay(300)
        searchViewModel.searchData(search.value)
    }
    val keyboardController= LocalSoftwareKeyboardController.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            ) {
                OutlinedTextField(
                    value = search.value,
                    onValueChange = {
                        searchViewModel.setSearch(it)
                        searchViewModel.searchData(it)
                    },
                    placeholder = { Text("Search Products") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            searchViewModel.searchData(search.value)
                        }
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Box() {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    items(searchlist.value) { cloth ->
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

                                androidx.compose.material.Text(
                                    cloth.productName,
                                    maxLines = 2,
                                    modifier = Modifier.padding(start = 8.dp, end = 6.dp),
                                    fontSize = 17.sp
                                )
                                Spacer(Modifier.height(4.dp))
                                androidx.compose.material.Text(cloth.price, color = Color.Red, fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}