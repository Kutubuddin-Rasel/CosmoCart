package com.example.ecommerceapp.data

import androidx.compose.runtime.State

data class homecategories(
    val porductName:String,
    val sellAllRoute:String,
    val datalist: State<List<cloth>>
)
