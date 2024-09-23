package com.example.ecommerceapp.repository

import android.util.Log
import com.example.ecommerceapp.data.cart
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AddToCartRepository @Inject constructor(private val firebaseAuth: FirebaseAuth,private val firebaseFirestore: FirebaseFirestore) {
    suspend fun addToCart(cart: cart){
        val currentUser=firebaseAuth.currentUser
        currentUser?.let {
            val reference=firebaseFirestore.collection("Users").document(currentUser.uid).collection("Cart")
            val uniqueid=reference.document().id
            val newcart=cart.copy(id=uniqueid)
            reference.document(uniqueid).set(newcart)
                .addOnSuccessListener {
                    Log.d("Firestore-CART", "Product added to CART: ${cart.productName}")
                }
                .addOnFailureListener{
                    Log.e("Firestore-CART","Error add product on cart",it)
                }
        }
    }
}