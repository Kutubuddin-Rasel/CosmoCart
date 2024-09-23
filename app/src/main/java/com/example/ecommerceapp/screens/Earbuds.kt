package com.example.ecommerceapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.viewModel.ElectronicsViewModel
import com.example.ecommerceapp.viewModel.Men_Women_ViewModel

@Composable
fun Earbuds(navController: NavController) {
    val electronicsViewModel: ElectronicsViewModel = hiltViewModel()
    val datalist=electronicsViewModel.datalist.collectAsState()
    val screenName="Earbuds"
    electronicsViewModel.getData("Gadget","Earbuds")
    item2(datalist,screenName,navController)
}