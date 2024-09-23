package com.example.ecommerceapp.repository

import android.net.Uri
import android.util.Log
import com.example.ecommerceapp.data.UserAddress
import com.example.ecommerceapp.data.profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ProfileRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
) {
    suspend fun updateProfile(username: String, image: Uri?, address: UserAddress) {
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            if (image != null && image.scheme != "https") {
                setImage(image)
                val imageUrl = getImageUrl().toString()
                imageUrl?.let {
                    firebaseFirestore.collection("Users").document(currentUser.uid)
                        .collection("Profile").document(currentUser.uid)
                        .update("username", username, "image", imageUrl, "address", address).await()
                }
            } else {
                firebaseFirestore.collection("Users").document(currentUser.uid)
                    .collection("Profile").document(currentUser.uid)
                    .update("username", username, "address", address).await()
            }
        }
    }

    suspend fun setImage(image: Uri) = suspendCancellableCoroutine<Unit> { continuation ->
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            val sotrageRef = firebaseStorage.reference.child("Profile_pic/${currentUser.uid}")
            val uploadtask = sotrageRef.putFile(image)
            uploadtask.addOnSuccessListener {
                continuation.resume(Unit)
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }.addOnCanceledListener {
                continuation.cancel()
            }
        }
    }

    suspend fun getImageUrl(): Uri? = suspendCancellableCoroutine { continuation ->
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            val sotrageRef = firebaseStorage.reference.child("Profile_pic/${currentUser.uid}")
            sotrageRef.downloadUrl.addOnSuccessListener {
                continuation.resume(it)
            }.addOnFailureListener {
                if (it is StorageException && it.errorCode == StorageException.ERROR_OBJECT_NOT_FOUND) {
                    continuation.resume(null)
                } else {
                    continuation.resumeWithException(it)
                }
            }.addOnCanceledListener {
                continuation.cancel()
            }
        }
    }

    suspend fun getUserdetails(): profile = suspendCancellableCoroutine { continuation ->
        val currentUser = firebaseAuth.currentUser
        currentUser?.let {
            val reference = firebaseFirestore.collection("Users")
                .document(currentUser.uid)
                .collection("Profile")
                .document(currentUser.uid)

            reference.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        // Data exists, fetch it
                        val fetchedProfile = documentSnapshot.toObject(profile::class.java)
                        fetchedProfile?.let { profile ->
                            continuation.resume(profile)
                        }
                            ?: continuation.resume(profile()) // Default empty profile if conversion fails
                    } else {
                        // No data, create a new profile
                        val newProfile = profile() // Create a default profile
                        reference.set(newProfile)
                            .addOnSuccessListener {
                                continuation.resume(newProfile)
                            }
                            .addOnFailureListener { exception ->
                                continuation.resumeWithException(exception)
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        } ?: continuation.resume(profile()) // Default empty profile if user is null
    }

}