package com.example.ecommerceapp.data

data class UserAddress(
    val pincode:String,
    val city:String,
    val district:String,
    val houseAdress:String
){
    constructor():this("","","","")
}
