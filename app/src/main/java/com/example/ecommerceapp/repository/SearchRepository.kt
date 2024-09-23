package com.example.ecommerceapp.repository

import android.util.Log
import com.example.ecommerceapp.data.cloth
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) {
    private val _searchlist=MutableStateFlow<List<cloth>>(emptyList())
    val searchlist: StateFlow<List<cloth>> =_searchlist
    suspend fun getSearchData(value: String) {
        if(value.isEmpty()){
            _searchlist.value= emptyList()
        }
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            val categoryInput = value.lowercase().trim() // Trim to remove any whitespace
            val referenceMap = mapOf(
                "Shirt" to firebaseFirestore.collection("Products").document("Men").collection("Shirt"),
                "Pant" to firebaseFirestore.collection("Products").document("Men").collection("Pant"),
                "Panjabi" to firebaseFirestore.collection("Products").document("Men").collection("Panjabi"),
                "T-shirt" to firebaseFirestore.collection("Products").document("Men").collection("T-shirt"),
                "Polo" to firebaseFirestore.collection("Products").document("Men").collection("Polo"),
                "Salwar kameez" to firebaseFirestore.collection("Products").document("Women").collection("Salwar kameez"),
                "Kameez" to firebaseFirestore.collection("Products").document("Women").collection("Kameez"),
                "Saree" to firebaseFirestore.collection("Products").document("Women").collection("Saree"),
                "Camera" to firebaseFirestore.collection("Products").document("Gadget").collection("Camera"),
                "Earbuds" to firebaseFirestore.collection("Products").document("Gadget").collection("Earbuds"),
                "Smart watch" to firebaseFirestore.collection("Products").document("Gadget").collection("Smart watch"),
                "Tv" to firebaseFirestore.collection("Products").document("Gadget").collection("Tv"),
                "Headphone" to firebaseFirestore.collection("Products").document("Accessories").collection("Headphone"),
                "Keyboard" to firebaseFirestore.collection("Products").document("Accessories").collection("Keyboard"),
                "Mouse" to firebaseFirestore.collection("Products").document("Accessories").collection("Mouse"),
                "Speaker" to firebaseFirestore.collection("Products").document("Accessories").collection("Speaker")
            )

            val keywordMap = mapOf(
                "Camera" to listOf("cam", "camera", "came"),
                "Shirt" to listOf("shirt", "men shirt", "SHIRT","shi","shir"),
                "Panjabi" to listOf("pan","panjabi", "men panjabi"),
                "T-shirt" to listOf("tshirt", "t shirt", "t Shirt"),
                "Salwar kameez" to listOf("sal","salwar kameez", "salwar Kameez", "salwar"),
                "Kameez" to listOf("kam","kameez"),
                "Saree" to listOf("sar","saree"),
                "Pant" to listOf("pant", "men pant"),
                "Polo" to listOf("po","pol","polo", "polo tshirt"),
                "Earbuds" to listOf("ear","ea","earb","buds", "wireless earphone"),
                "Smart watch" to listOf("sm","smar","smartw","watch smart"),
                "Tv" to listOf("smart tv", "4k tv", "4ktv","smart t","smart ","smartt", "smarttv", "hdtv", "Smart tv", "Hdtv", "Hd tv"),
                "Headphone" to listOf("hea","head","headp","wireless headphone", "Wireless headphone"),
                "Keyboard" to listOf("key","keyb","wireless keyboard", "bluetooth keyboard"),
                "Mouse" to listOf("mo","mou","mous","wireless mouse", "bluetooth mouse"),
                "Speaker" to listOf("sp","spea","wireless speaker", "bluetooth speaker")
            )

            // Determine the category based on input
            val category = keywordMap.entries.find { (_, keywords) ->
                keywords.any { keyword -> categoryInput.startsWith(keyword.lowercase()) } // Ensure case insensitivity
            }?.key

            // Look up the Firestore reference based on the determined category
            val reference = category?.let {
                referenceMap[it]
            }
            _searchlist.value= emptyList()
            reference?.let { collectionRef ->
                val query = collectionRef.whereGreaterThanOrEqualTo("category", category)
                Log.d("KITAPIASOSBETA", "Querying for category: $category")
                query.addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.e("SEARCH-DATA", "Error fetching data", error)
                        return@addSnapshotListener
                    }
                    snapshot?.let {
                        val list = mutableListOf<cloth>()
                        for (document in it) {
                            val newValue = document.toObject(cloth::class.java)
                            list.add(
                                cloth(
                                    newValue.collection,
                                    newValue.category,
                                    newValue.id,
                                    newValue.productName,
                                    newValue.price,
                                    newValue.description,
                                    newValue.size,
                                    newValue.image
                                )
                            )
                        }
                        _searchlist.value=list
                    }
                }
            } ?: Log.d("SEARCH-DATA", "No matching category found for $value")
        }
    }
}


