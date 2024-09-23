package com.example.ecommerceapp.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.ecommerceapp.data.cart
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Integer.parseInt
import javax.inject.Inject

class CartRepository @Inject constructor(private val firebaseAuth: FirebaseAuth,private val firebaseFirestore: FirebaseFirestore) {
    private val _cartData=MutableStateFlow<List<cart>>(emptyList())
    val cartData: StateFlow<List<cart>> =_cartData
    private val _total =MutableStateFlow<Double>(0.0)
    val total:StateFlow<Double> =_total
    @SuppressLint("DefaultLocale")
    suspend fun getCartData(){
        val currenUser=firebaseAuth.currentUser
        currenUser?.let {
            val reference=firebaseFirestore.collection("Users").document(currenUser.uid).collection("Cart")
            val query:Query=reference.orderBy("price",Query.Direction.ASCENDING)
            query.addSnapshotListener { value, error ->
                if(error!=null){
                    Log.e("FireStore-Cart-FetchingData","Error to fetch data",error)
                    return@addSnapshotListener
                }
                value?.let {
                    val list= mutableListOf<cart>()
                    var total=0.0
                    for(document in it){
                        val newcart=document.toObject(cart::class.java)
                        val cart=cart(newcart.id,newcart.image,newcart.productName,newcart.category,newcart.price,newcart.size)
                        list.add(cart)
                        total += convertPriceToDouble(cart.price)
                    }
                    _cartData.value=list
                    _total.value=total
                }
            }
        }
    }
    suspend fun increase(cart: cart) {
        val newPrice = convertPriceToDouble(cart.price)
        _total.value += newPrice // Direct addition
        Log.d("valueIncrease", _total.value.toString())
    }

    suspend fun decrease(cart: cart) {
        val newPrice = convertPriceToDouble(cart.price)
        _total.value -= newPrice // Direct subtraction
        Log.d("valueDecrease", _total.value.toString())
    }

    private fun convertPriceToDouble(price: String): Double {
        return price.replace("[^\\d.]".toRegex(), "").toDoubleOrNull() ?: 0.0
    }
    suspend fun removedata(cartid:String){
        val currentUser=firebaseAuth.currentUser
        currentUser?.let {
            val reference=firebaseFirestore.collection("Users").document(currentUser.uid).collection("Cart")
            reference.document(cartid).delete()
        }
    }
}