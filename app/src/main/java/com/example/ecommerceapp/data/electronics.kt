package com.example.ecommerceapp.data

data class electronics(
    val collection:String,
    val category:String,
    val id:Int,
    val productName:String,
    val price:String,
    val description:String,
    val image:String
)
{
    constructor():this("","",1,"","","","")
}
