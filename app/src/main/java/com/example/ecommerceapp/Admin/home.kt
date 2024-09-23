package com.example.ecommerceapp.Admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun home() {
    Box(modifier = Modifier.fillMaxSize()) {
        val addProductViewModel: AddProductViewModel = hiltViewModel()
        val description="Drape yourself in elegance with our exquisite women's saree, crafted from luxurious fabrics and adorned with beautiful patterns. Perfect for any special occasion."
        Column (modifier = Modifier.fillMaxWidth()){
            Button(onClick = {
                //addProductViewModel.setData()
                addProductViewModel.setData("Women","Saree",description)
            }) {
                Text("ADD Product")
            }
        }
    }

}
