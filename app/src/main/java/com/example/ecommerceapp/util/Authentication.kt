package com.example.ecommerceapp.util

import android.content.Context
import android.widget.Toast

sealed class Authentication{
    data object Authenticated:Authentication()
    data object UnAuthenticated:Authentication()
    data class Error(val message:String):Authentication()
}
fun toast(context: Context, message:String){
    Toast.makeText(context,"$message", Toast.LENGTH_SHORT).show()
}
fun Authentication(email:String,password:String,context: Context): Boolean {
    val value: Boolean
    if(email.isEmpty()){ toast(context,"Email can't be empty") }
    if(password.isEmpty()){ toast(context,"Password can't be empty") }
    if(email.isEmpty()||password.isEmpty()){
        toast(context,"Enter Email and Password")
        value=false
    }
    else{
        value=true
    }
    return value
}