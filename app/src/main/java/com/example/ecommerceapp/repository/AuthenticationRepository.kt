package com.example.ecommerceapp.repository

import com.example.ecommerceapp.util.Authentication
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    private val _authState = MutableStateFlow<Authentication?>(null)
    val authState: StateFlow<Authentication?> = _authState

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        if (firebaseAuth.currentUser != null) {
            _authState.value = Authentication.Authenticated
        } else {
            _authState.value = Authentication.UnAuthenticated
        }
    }

    suspend fun logIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _authState.value = Authentication.Authenticated
                }
        }
    }
    suspend fun signIn(email: String,password: String,onclick:(value:Boolean)->Unit){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    onclick(true)
                }
            }
    }
    suspend fun logOut(){
        firebaseAuth.signOut()
        _authState.value=Authentication.UnAuthenticated
    }
}