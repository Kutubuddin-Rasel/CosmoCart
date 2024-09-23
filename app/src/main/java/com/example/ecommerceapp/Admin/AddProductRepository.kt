package com.example.ecommerceapp.Admin

import android.util.Log
import com.example.ecommerceapp.api.RichMan
import com.example.ecommerceapp.data.electronics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.jsoup.Jsoup
import javax.inject.Inject

class addProductRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val richMan: RichMan
) {
    suspend fun setDescription(collction:String,category:String,description:String){
        val currenUser=firebaseAuth.currentUser
        currenUser?.let {
            val reference=firebaseFirestore.collection("Products").document(collction).collection(category)
            val snapshot=reference.get().await()
            for(document in snapshot.documents){
                reference.document(document.id).update("description",description).await()
            }
        }
    }

//    suspend fun getdata() {
//        val currentUser = firebaseAuth.currentUser
//        currentUser?.let {
//            val response = richMan.getdata(4)
//            if (response.isSuccessful && response.body() != null) {
//                val element = Jsoup.parse(response.body())
//                val productItems = element.select("div.main-content.p-items-wrap div.p-item") // Select each product item
//
//                var id = 41
//                for (item in productItems) {
//                    //   Log.d("ProductDetails", "id: ")
//                    val imageElement = item.select("div.p-item-img img").first() ?: continue
//                    val priceElement = item.select("div.p-item-price span").first() ?: continue
//                    val titleElement = item.select("h4.p-item-name a").first() ?: continue
//                    val descriptionList = item.select("div.short-description ul li")
//
//                    // Constructing description from the list
//                    val description = descriptionList.joinToString("\n") { it.text() }
//
//                    // Extracting data
//                    val imageLink = imageElement.attr("src")
//                    val title = titleElement.text()
//                    val price = priceElement.text()
//                    val electronics = electronics(
//                        "Gadget",
//                        "Camera",
//                        id,
//                        title,
//                        price,
//                        description,
//                        imageLink
//                    )
//                   // Log.d("PRODUCT_DETAILS",keyboard.toString())
//                    setdata(electronics)
//                    id++
//                }
//            }
//        }
//    }
//
//    suspend fun setdata(electronics: electronics) {
//        val currentUser = firebaseAuth.currentUser
//        currentUser?.let {
//            val productRef = firebaseFirestore.collection("Products").document(electronics.collection)
//                .collection(electronics.category).document()
//            productRef.set(electronics)
//                .addOnSuccessListener {
//                    Log.d("Firestore", "Product added: ${electronics.productName}")
//                }
//                .addOnFailureListener {
//                    Log.e("Firestore", "Error adding product", it)
//                }
//        }
//    }
}