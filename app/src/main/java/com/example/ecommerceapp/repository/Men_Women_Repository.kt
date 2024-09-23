package com.example.ecommerceapp.repository

import android.util.Log
import com.example.ecommerceapp.data.cloth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class Men_Women_Repository @Inject constructor(private val firebaseAuth: FirebaseAuth, private val firebaseFirestore: FirebaseFirestore) {
    private val _datalist= MutableStateFlow<List<cloth>>(emptyList())
    val datalist: StateFlow<List<cloth>> =_datalist

    suspend fun getData(collection:String,category:String){
        val currentUser=firebaseAuth.currentUser
        currentUser?.let {
            val reference=firebaseFirestore.collection("Products").document(collection).collection(category)
            val query: Query =reference.orderBy("id", Query.Direction.ASCENDING)
            query.addSnapshotListener { value, error ->
                if(error!=null){
                    Log.e("GETSHIRTDATA","Error fetching $category data",error)
                    return@addSnapshotListener
                }
                value?.let {
                    val datalist= mutableListOf<cloth>()
                    for(document in it){
                        val newlist=document.toObject(cloth::class.java)
                        datalist.add(cloth(newlist.collection,newlist.category,newlist.id,newlist.productName,newlist.price,newlist.description,newlist.size,newlist.image))
                    }
                    _datalist.value=datalist
                }
            }
        }
    }
}