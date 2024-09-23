package com.example.ecommerceapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RichMan {
    @GET("/camera")
    suspend fun getdata(@Query("page")page:Int):Response<String>
}