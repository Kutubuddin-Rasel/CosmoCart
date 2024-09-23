package com.example.ecommerceapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ecommerceapp.viewModel.Men_Women_ViewModel

@Composable
fun Shirt(navController: NavController) {
    val Men_Women_ViewModel: Men_Women_ViewModel = hiltViewModel()
    val datalist=Men_Women_ViewModel.datalist.collectAsState()
    val screenName="Shirt"
    Men_Women_ViewModel.getData("Men","Shirt")
    item(datalist,screenName,navController)
}