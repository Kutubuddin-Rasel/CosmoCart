package com.example.ecommerceapp.data

data class profile(
    val username:String,
    val image:String,
    val address: UserAddress
)
{
    constructor():this("","",UserAddress("","","",""))
}