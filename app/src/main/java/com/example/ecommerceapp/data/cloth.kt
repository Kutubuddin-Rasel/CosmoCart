package com.example.ecommerceapp.data

data class cloth(
    val collection:String,
    val category:String,
    val id:Int,
    val productName:String,
    val price:String,
    val description:String,
    val size:List<String>,
    val image:String
)
{
    constructor():this("","",1,"","","", emptyList(),"")
}
