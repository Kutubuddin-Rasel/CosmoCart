package com.example.ecommerceapp.data

data class cart(
    val id:String,
    val image:String,
    val productName:String,
    val category:String,
    val price:String,
    val size:String
){
    constructor():this("","","","","","")
}
